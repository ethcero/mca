package io.eventuate.examples.tram.sagas.ordersandcustomers.orders.sagas.createorder;

import org.junit.Before;
import org.junit.Test;
import org.mockito.stubbing.Answer;

import static org.junit.Assert.assertEquals;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

import java.util.Optional;

import io.eventuate.examples.tram.sagas.ordersandcustomers.commondomain.Money;
import io.eventuate.examples.tram.sagas.ordersandcustomers.customers.api.commands.ReserveCreditCommand;
import io.eventuate.examples.tram.sagas.ordersandcustomers.customers.api.replies.CustomerCreditLimitExceeded;
import io.eventuate.examples.tram.sagas.ordersandcustomers.orders.domain.common.OrderDetails;
import io.eventuate.examples.tram.sagas.ordersandcustomers.orders.domain.Order;
import io.eventuate.examples.tram.sagas.ordersandcustomers.orders.domain.OrderRepository;
import io.eventuate.examples.tram.sagas.ordersandcustomers.orders.domain.OrderState;
import io.eventuate.examples.tram.sagas.ordersandcustomers.orders.domain.RejectionReason;
import io.eventuate.examples.tram.sagas.ordersandcustomers.product.webapi.api.commands.ReserveProductCommand;
import io.eventuate.examples.tram.sagas.ordersandcustomers.product.webapi.api.replies.ProductStockLimitExceeded;

import static io.eventuate.tram.sagas.testing.SagaUnitTestSupport.given;

public class CreateOrderSagaTest {

  private OrderRepository orderRepository;
  private Long customerId = 102L;
  private Money orderTotal = new Money("12.34");
  private Long productId = 1002L;
  private Integer productQuantity = 5;

  private OrderDetails orderDetails = new OrderDetails(customerId, productId, productQuantity, orderTotal);
  private Long orderId = 103L;

  private CreateOrderSaga makeCreateOrderSaga() {
    return new CreateOrderSaga(orderRepository);
  }


  @Before
  public void setUp() {
    orderRepository = mock(OrderRepository.class);
  }

  private Order order;

  @Test
  public void shouldCreateOrder() {
    when(orderRepository.save(any(Order.class))).then((Answer<Order>) invocation -> {
      order = invocation.getArgument(0);
      order.setId(orderId);
      return order;
    });

    when(orderRepository.findById(orderId)).then(invocation -> Optional.of(order));

    given()
            .saga(makeCreateOrderSaga(),
                    new CreateOrderSagaData(orderDetails)).
            expect().
            command(new ReserveCreditCommand(customerId, orderId, orderTotal))
            .to("customerService")
            .andGiven()
            .successReply()
            .expect()
            .command(new ReserveProductCommand(orderId, productId, productQuantity))
            .to("productService")
            .andGiven()
            .successReply()
            .expectCompletedSuccessfully();

    assertEquals(OrderState.APPROVED, order.getState());
  }

  @Test
  public void shouldRejectCreateOrder() {
    when(orderRepository.save(any(Order.class))).then((Answer<Order>) invocation -> {
      order = invocation.getArgument(0);
      order.setId(orderId);
      return order;
    });

    when(orderRepository.findById(orderId)).then(invocation -> Optional.of(order));

    CreateOrderSagaData data = new CreateOrderSagaData(orderDetails);

    given()
            .saga(makeCreateOrderSaga(),
                    data).
            expect().
            command(new ReserveCreditCommand(customerId, orderId, orderTotal))
            .to("customerService")
            .andGiven()
            .failureReply(new CustomerCreditLimitExceeded())
            .expectRolledBack()
            .assertSagaData(sd -> {
              assertEquals(RejectionReason.INSUFFICIENT_CREDIT, sd.getRejectionReason());
            });

    assertEquals(OrderState.REJECTED, order.getState());
    assertEquals(RejectionReason.INSUFFICIENT_CREDIT, order.getRejectionReason());

  }

  @Test
  public void shouldRejectCreateOrderByOutOfStock() {
    when(orderRepository.save(any(Order.class))).then((Answer<Order>) invocation -> {
      order = invocation.getArgument(0);
      order.setId(orderId);
      return order;
    });

    when(orderRepository.findById(orderId)).then(invocation -> Optional.of(order));

    CreateOrderSagaData data = new CreateOrderSagaData(orderDetails);

    given()
            .saga(makeCreateOrderSaga(),
                    data).
            expect()
            .command(new ReserveCreditCommand(customerId, orderId, orderTotal))
            .to("customerService")
            .andGiven()
            .successReply()
            .command(new ReserveProductCommand(orderId, productId, productQuantity))
            .to("productService")
            .andGiven()
            .failureReply(new ProductStockLimitExceeded())
            .expectRolledBack()
            .assertSagaData(sd -> {
              assertEquals(RejectionReason.INSUFFICIENT_STOCK, sd.getRejectionReason());
            });

    assertEquals(OrderState.REJECTED, order.getState());
    assertEquals(RejectionReason.INSUFFICIENT_STOCK, order.getRejectionReason());

  }
}