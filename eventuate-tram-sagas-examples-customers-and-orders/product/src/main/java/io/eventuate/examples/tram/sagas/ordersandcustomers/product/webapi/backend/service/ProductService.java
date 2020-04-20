package io.eventuate.examples.tram.sagas.ordersandcustomers.product.webapi.backend.service;

import io.eventuate.examples.tram.sagas.ordersandcustomers.product.webapi.backend.domain.Product;
import io.eventuate.examples.tram.sagas.ordersandcustomers.product.webapi.backend.domain.ProductNotFoundException;
import io.eventuate.examples.tram.sagas.ordersandcustomers.product.webapi.backend.domain.ProductRepository;
import io.eventuate.examples.tram.sagas.ordersandcustomers.product.webapi.backend.domain.ProductStockLimitExceededException;

public class ProductService {

  private ProductRepository productRepository;

  public ProductService(ProductRepository productRepository) {
    this.productRepository = productRepository;
  }

  public Product createProduct(String name, Integer stock) {
    Product product  = new Product(name, stock);
    return productRepository.save(product);
  }

  public void reserveStock(long productId, long orderId, Integer quantity) throws ProductStockLimitExceededException {
    Product product = productRepository.findById(productId).orElseThrow(ProductNotFoundException::new);
    product.reserveStock(orderId, quantity);
  }
}
