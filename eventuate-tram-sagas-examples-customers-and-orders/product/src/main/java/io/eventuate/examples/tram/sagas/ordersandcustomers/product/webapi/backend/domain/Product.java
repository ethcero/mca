package io.eventuate.examples.tram.sagas.ordersandcustomers.product.webapi.backend.domain;

import java.util.Collections;
import java.util.Map;

import javax.persistence.Access;
import javax.persistence.AccessType;
import javax.persistence.ElementCollection;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name="Product")
@Access(AccessType.FIELD)
public class Product {

  @Id
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  private Long id;
  private String name;

  private Integer stock;

  @ElementCollection
  private Map<Long, Integer> stockReservations;

  Integer availableStock() {
    return  stock - stockReservations.values().stream().reduce(0, Integer::sum);
  }

  public Product() {
  }

  public Product(String name, Integer stock) {
    this.name = name;
    this.stock = stock;
    this.stockReservations = Collections.emptyMap();
  }

  public Long getId() {
    return id;
  }

  public String getName() {
    return name;
  }

  public Integer getStock() {
    return availableStock();
  }

  public void reserveStock(Long orderId, Integer orderTotal) {
    if (availableStock() >= orderTotal) {
      stockReservations.put(orderId, orderTotal);
    } else
      throw new ProductStockLimitExceededException();
  }
}
