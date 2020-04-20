package io.eventuate.examples.tram.sagas.ordersandcustomers.product.webapi;

public class CreateProductRequest {
  private String name;
  private Integer stock;

  public CreateProductRequest() {
  }

  public CreateProductRequest(String name, Integer stock) {

    this.name = name;
    this.stock = stock;
  }


  public String getName() {
    return name;
  }

  public Integer getStock() {
    return stock;
  }
}
