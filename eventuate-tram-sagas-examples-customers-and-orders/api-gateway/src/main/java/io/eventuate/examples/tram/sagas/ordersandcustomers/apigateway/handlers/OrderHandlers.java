
package io.eventuate.examples.tram.sagas.ordersandcustomers.apigateway.handlers;

import org.springframework.http.MediaType;
import org.springframework.web.reactive.function.server.ServerRequest;
import org.springframework.web.reactive.function.server.ServerResponse;

import static org.springframework.web.reactive.function.BodyInserters.fromObject;

import io.eventuate.examples.tram.sagas.ordersandcustomers.apigateway.service.OrderServiceProxy;
import io.eventuate.examples.tram.sagas.ordersandcustomers.apigateway.service.ProductServiceProxy;
import io.eventuate.examples.tram.sagas.ordersandcustomers.apigateway.webapi.OrderDetails;
import io.eventuate.examples.tram.sagas.ordersandcustomers.orders.webapi.GetOrderDetailsResponse;
import io.eventuate.examples.tram.sagas.ordersandcustomers.orders.webapi.GetOrderResponse;
import io.eventuate.examples.tram.sagas.ordersandcustomers.product.webapi.GetProductResponse;
import reactor.core.publisher.Mono;
import reactor.util.function.Tuple2;

/**
 * @author fran
 */
public class OrderHandlers {

    private OrderServiceProxy orderServiceProxy;
    private ProductServiceProxy productServiceProxy;

    public OrderHandlers(OrderServiceProxy orderServiceProxy, ProductServiceProxy productServiceProxy) {
        this.orderServiceProxy = orderServiceProxy;
        this.productServiceProxy = productServiceProxy;
    }

    public Mono<ServerResponse> getOrderDetails(ServerRequest serverRequest) {
        String orderId = serverRequest.pathVariable("orderId");

        Mono<GetOrderResponse> order = orderServiceProxy.findOrderById(orderId);

        Mono<GetOrderDetailsResponse> responseDetails = Mono.first(orderServiceProxy.findOrderDetailsById(orderId));

        Mono<GetProductResponse> product = responseDetails.flatMap(o -> productServiceProxy.findProductById(o.getProductId()));

        Mono<Tuple2<GetOrderResponse, GetProductResponse>> combined = Mono.zip(order,product);

        Mono<OrderDetails> orderDet = combined.map(OrderDetails::makeOrderDetails);

        return orderDet.flatMap(o -> ServerResponse.ok()
            .contentType(MediaType.APPLICATION_JSON)
            .body(fromObject(o)));
    }
}
