package io.eventuate.examples.tram.sagas.ordersandcustomers.orders.domain.common;

import javax.persistence.Embeddable;
import javax.persistence.Embedded;

import io.eventuate.examples.tram.sagas.ordersandcustomers.commondomain.Money;

@Embeddable
public class OrderDetails {

  private Long customerId;

  private Long productId;

  private Integer quantity;

  @Embedded
  private Money orderTotal;

  public OrderDetails() {
  }

  public OrderDetails(Long customerId, Long productId, Integer quantity, Money orderTotal) {
    this.customerId = customerId;
    this.productId = productId;
    this.quantity = quantity;
    this.orderTotal = orderTotal;
  }

  public Long getProductId() {
    return productId;
  }

  public Integer getQuantity() {
    return quantity;
  }

  public Long getCustomerId() {
    return customerId;
  }

  public Money getOrderTotal() {
    return orderTotal;
  }
}
