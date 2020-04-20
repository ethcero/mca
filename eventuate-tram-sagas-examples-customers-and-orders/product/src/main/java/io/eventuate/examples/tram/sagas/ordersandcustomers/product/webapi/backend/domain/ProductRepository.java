package io.eventuate.examples.tram.sagas.ordersandcustomers.product.webapi.backend.domain;

import org.springframework.data.repository.CrudRepository;

public interface ProductRepository extends CrudRepository<Product, Long> {
}
