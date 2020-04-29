
package es.ethcero.ann.practica2.web.order;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import es.ethcero.ann.practica2.domain.order.Order;
import es.ethcero.ann.practica2.domain.order.OrderRepository;
import es.ethcero.ann.practica2.service.OrderService;

/**
 * @author fran
 */
@RestController
public class OrderController {

    @Autowired
    private OrderService orderService;
    @Autowired
    private OrderRepository orderRepository;

    @PostMapping("/orders")
    public CreateOrderResponse createOrder(@RequestBody CreateOrderRequest createOrderRequest) {
        Order order = orderService.createOrder(createOrderRequest.getProductId(),createOrderRequest.getCustomerId(),createOrderRequest.getQuantity(),createOrderRequest.getOrderTotal());
        return new CreateOrderResponse(order.getId());
    }

    @GetMapping("/orders/{orderId}")
    public ResponseEntity<GetOrderResponse> getOrder(@PathVariable long orderId) {
        return orderRepository
                .findById(orderId)
                .map(o -> new ResponseEntity<>(new GetOrderResponse(
                        o.getId(),
                        o.getOrderDetails().getProductId(),
                        o.getOrderDetails().getCustomerId(),
                        o.getOrderDetails().getQuantity(),
                        o.getOrderDetails().getOrderTotal()
                ), HttpStatus.OK))
                .orElse(new ResponseEntity<>(HttpStatus.NOT_FOUND));
    }

}
