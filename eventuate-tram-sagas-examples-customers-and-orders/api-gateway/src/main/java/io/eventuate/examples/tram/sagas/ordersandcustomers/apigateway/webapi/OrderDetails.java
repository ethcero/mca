
package io.eventuate.examples.tram.sagas.ordersandcustomers.apigateway.webapi;

import io.eventuate.examples.tram.sagas.ordersandcustomers.orders.domain.OrderState;
import io.eventuate.examples.tram.sagas.ordersandcustomers.orders.domain.RejectionReason;
import io.eventuate.examples.tram.sagas.ordersandcustomers.orders.webapi.GetOrderResponse;
import io.eventuate.examples.tram.sagas.ordersandcustomers.product.webapi.GetProductResponse;
import reactor.util.function.Tuple2;

/**
 * @author fran
 */
public class OrderDetails {
    private Long orderId;
    private OrderState orderState;
    private RejectionReason rejectionReason;
    private String productName;

    private OrderDetails(Long orderId, OrderState orderState, RejectionReason rejectionReason, String productName) {
        this.orderId = orderId;
        this.orderState = orderState;
        this.rejectionReason = rejectionReason;
        this.productName = productName;
    }

    public static OrderDetails makeOrderDetails(Tuple2<GetOrderResponse, GetProductResponse> tuple){
        return new OrderDetails(tuple.getT1().getOrderId(), tuple.getT1().getOrderState(), tuple.getT1().getRejectionReason(),tuple.getT2().getName());
    }

    public Long getOrderId() {
        return orderId;
    }

    public OrderState getOrderState() {
        return orderState;
    }

    public RejectionReason getRejectionReason() {
        return rejectionReason;
    }

    public String getProductName() {
        return productName;
    }
}
