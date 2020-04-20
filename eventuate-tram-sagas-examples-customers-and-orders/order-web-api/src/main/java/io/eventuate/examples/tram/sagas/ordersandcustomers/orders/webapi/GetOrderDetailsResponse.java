package io.eventuate.examples.tram.sagas.ordersandcustomers.orders.webapi;

import io.eventuate.examples.tram.sagas.ordersandcustomers.commondomain.Money;

public class GetOrderDetailsResponse {
  private Long customerId;

  private Long productId;

  private Integer quantity;

  private Money orderTotal;

  public GetOrderDetailsResponse() {
  }

  public GetOrderDetailsResponse(Long customerId, Long productId, Integer quantity, Money orderTotal) {
    this.customerId = customerId;
    this.productId = productId;
    this.quantity = quantity;
    this.orderTotal = orderTotal;
  }

  public Long getCustomerId() {
    return customerId;
  }

  public Long getProductId() {
    return productId;
  }

  public Integer getQuantity() {
    return quantity;
  }

  public Money getOrderTotal() {
    return orderTotal;
  }
}
