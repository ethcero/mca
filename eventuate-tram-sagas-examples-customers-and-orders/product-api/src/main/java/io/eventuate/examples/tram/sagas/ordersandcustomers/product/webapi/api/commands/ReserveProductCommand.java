
package io.eventuate.examples.tram.sagas.ordersandcustomers.product.webapi.api.commands;

import io.eventuate.tram.commands.common.Command;

/**
 * @author fran
 */
public class ReserveProductCommand implements Command {
    private Long orderId;
    private Integer quantity;
    private long productId;

    public ReserveProductCommand() {
    }

    public ReserveProductCommand(Long productId, Long orderId, Integer quantity) {
        this.productId = productId;
        this.orderId = orderId;
        this.quantity = quantity;
    }

    public Integer getQuantity() {
        return quantity;
    }

    public void setQuantity(Integer quantity) {
        this.quantity = quantity;
    }

    public Long getOrderId() {

        return orderId;
    }

    public void setOrderId(Long orderId) {

        this.orderId = orderId;
    }

    public long getProductId() {
        return productId;
    }

    public void setProductId(long productId) {
        this.productId = productId;
    }
}
