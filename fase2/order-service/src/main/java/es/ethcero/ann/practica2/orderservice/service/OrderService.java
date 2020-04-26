
package es.ethcero.ann.practica2.orderservice.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.client.HttpClientErrorException;
import org.springframework.web.server.ResponseStatusException;

import es.ethcero.ann.practica2.orderservice.common.Money;
import es.ethcero.ann.practica2.orderservice.domain.Order;
import es.ethcero.ann.practica2.orderservice.domain.OrderDetails;
import es.ethcero.ann.practica2.orderservice.domain.OrderRepository;
import es.ethcero.ann.practica2.orderservice.proxy.customer.CustomerServiceProxy;
import es.ethcero.ann.practica2.orderservice.proxy.product.ProductServiceProxy;

/**
 * @author fran
 */
@Service
public class OrderService {

    @Autowired
    private OrderRepository orderRepository;
    @Autowired
    private ProductServiceProxy productServiceProxy;
    @Autowired
    private CustomerServiceProxy customerServiceProxy;

    public Order createOrder(long productId, long customerId, int quantity, Money orderTotal) {

        try {
            productServiceProxy.reserveStock(productId, quantity);
        }catch (HttpClientErrorException e) {
            throw new ResponseStatusException(
                    HttpStatus.resolve(e.getStatusCode().value()), e.getMessage(), e);
        }
        try {
            customerServiceProxy.reserveCredit(customerId, orderTotal);
        }catch (HttpClientErrorException e) {
            productServiceProxy.addStock(productId, quantity);
            throw new ResponseStatusException(
                    HttpStatus.resolve(e.getStatusCode().value()), e.getMessage(), e);
        }
        return orderRepository.save(new Order(new OrderDetails(customerId,productId, quantity, orderTotal)));
    }


}
