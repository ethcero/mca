package io.eventuate.examples.tram.sagas.ordersandcustomers.product.webapi.backend;

import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Import;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;

import io.eventuate.examples.tram.sagas.ordersandcustomers.product.webapi.backend.domain.ProductRepository;
import io.eventuate.examples.tram.sagas.ordersandcustomers.product.webapi.backend.service.ProductCommandHandler;
import io.eventuate.examples.tram.sagas.ordersandcustomers.product.webapi.backend.service.ProductService;
import io.eventuate.tram.commands.consumer.CommandDispatcher;
import io.eventuate.tram.sagas.participant.SagaCommandDispatcherFactory;
import io.eventuate.tram.sagas.spring.participant.SagaParticipantConfiguration;

@Configuration
@Import(SagaParticipantConfiguration.class)
@EnableJpaRepositories
@EnableAutoConfiguration
public class ProductConfiguration {

  @Bean
  public ProductService productService(ProductRepository productRepository) {
    return new ProductService(productRepository);
  }

  @Bean
  public ProductCommandHandler productCommandHandler(ProductService productService) {
    return new ProductCommandHandler(productService);
  }

  @Bean
  public CommandDispatcher consumerCommandDispatcher(ProductCommandHandler target,
                                                     SagaCommandDispatcherFactory sagaCommandDispatcherFactory) {

    return sagaCommandDispatcherFactory.make("productCommandDispatcher", target.commandHandlerDefinitions());
  }

}
