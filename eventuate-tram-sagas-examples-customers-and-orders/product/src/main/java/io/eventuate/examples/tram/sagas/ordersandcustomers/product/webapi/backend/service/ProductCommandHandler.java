package io.eventuate.examples.tram.sagas.ordersandcustomers.product.webapi.backend.service;

import io.eventuate.examples.tram.sagas.ordersandcustomers.product.webapi.api.commands.ReserveProductCommand;
import io.eventuate.examples.tram.sagas.ordersandcustomers.product.webapi.api.replies.ProductNotFound;
import io.eventuate.examples.tram.sagas.ordersandcustomers.product.webapi.api.replies.ProductStockLimitExceeded;
import io.eventuate.examples.tram.sagas.ordersandcustomers.product.webapi.api.replies.ProductStockReserved;
import io.eventuate.examples.tram.sagas.ordersandcustomers.product.webapi.backend.domain.ProductNotFoundException;
import io.eventuate.examples.tram.sagas.ordersandcustomers.product.webapi.backend.domain.ProductStockLimitExceededException;
import io.eventuate.tram.commands.consumer.CommandHandlers;
import io.eventuate.tram.commands.consumer.CommandMessage;
import io.eventuate.tram.messaging.common.Message;
import io.eventuate.tram.sagas.participant.SagaCommandHandlersBuilder;

import static io.eventuate.tram.commands.consumer.CommandHandlerReplyBuilder.withFailure;
import static io.eventuate.tram.commands.consumer.CommandHandlerReplyBuilder.withSuccess;

public class ProductCommandHandler {

  private ProductService productService;

  public ProductCommandHandler(ProductService productService) {
    this.productService = productService;
  }

  public CommandHandlers commandHandlerDefinitions() {
    return SagaCommandHandlersBuilder
            .fromChannel("productService")
            .onMessage(ReserveProductCommand.class, this::reserveStock)
            .build();
  }

  public Message reserveStock(CommandMessage<ReserveProductCommand> cm) {
    ReserveProductCommand cmd = cm.getCommand();
    try {
      productService.reserveStock(cmd.getProductId(), cmd.getOrderId(), cmd.getQuantity());
      return withSuccess(new ProductStockReserved());
    } catch (ProductStockLimitExceededException e) {
      return withFailure(new ProductStockLimitExceeded());
    } catch (ProductNotFoundException e) {
      return withFailure(new ProductNotFound());
    }
  }

}
