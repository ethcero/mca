
package es.ethcero.ann.practica2.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import es.ethcero.ann.practica2.domain.Money;
import es.ethcero.ann.practica2.domain.customer.Customer;
import es.ethcero.ann.practica2.domain.order.Order;
import es.ethcero.ann.practica2.domain.order.OrderDetails;
import es.ethcero.ann.practica2.domain.order.OrderRepository;
import es.ethcero.ann.practica2.domain.product.Product;

/**
 * @author fran
 */
@Service
public class OrderService {

    @Autowired
    private OrderRepository orderRepository;
    @Autowired
    private CustomerService customerService;
    @Autowired
    private ProductService productService;

    public Order createOrder(long productId, long customerId, int quantity, Money orderTotal) {

        Product product = productService.reserveStock(productId,quantity);
        Customer customer = customerService.reserveCredit(customerId, orderTotal);
        productService.save(product);
        customerService.save(customer);
        return orderRepository.save(new Order(new OrderDetails(customerId,productId, quantity, orderTotal)));

    }


}
