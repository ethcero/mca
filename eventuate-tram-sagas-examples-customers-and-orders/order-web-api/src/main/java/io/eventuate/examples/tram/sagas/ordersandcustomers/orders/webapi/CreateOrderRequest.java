package io.eventuate.examples.tram.sagas.ordersandcustomers.orders.webapi;


import io.eventuate.examples.tram.sagas.ordersandcustomers.commondomain.Money;

public class CreateOrderRequest {
  private Money orderTotal;
  private Long customerId;
  private Long productId;
  private Integer quantity;

  public CreateOrderRequest() {
  }

  public CreateOrderRequest( Long customerId, Long productId, Integer quantity, Money orderTotal) {
    this.orderTotal = orderTotal;
    this.customerId = customerId;
    this.productId = productId;
    this.quantity = quantity;
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

  public Long getCustomerId() {
    return customerId;
  }
}
