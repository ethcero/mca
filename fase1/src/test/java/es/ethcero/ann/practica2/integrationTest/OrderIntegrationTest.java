
package es.ethcero.ann.practica2.integrationTest;

import org.hamcrest.Matchers;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertThat;

import es.ethcero.ann.practica2.domain.common.Money;
import es.ethcero.ann.practica2.domain.customer.InsufficientCreditException;
import es.ethcero.ann.practica2.domain.product.InsufficientStockException;
import es.ethcero.ann.practica2.web.customer.CreateCustomerRequest;
import es.ethcero.ann.practica2.web.customer.CreateCustomerResponse;
import es.ethcero.ann.practica2.web.customer.CustomerController;
import es.ethcero.ann.practica2.web.customer.GetCustomerResponse;
import es.ethcero.ann.practica2.web.order.CreateOrderRequest;
import es.ethcero.ann.practica2.web.order.CreateOrderResponse;
import es.ethcero.ann.practica2.web.order.GetOrderResponse;
import es.ethcero.ann.practica2.web.order.OrderController;
import es.ethcero.ann.practica2.web.product.CreateProductRequest;
import es.ethcero.ann.practica2.web.product.CreateProductResponse;
import es.ethcero.ann.practica2.web.product.GetProductResponse;
import es.ethcero.ann.practica2.web.product.ProductController;

/**
 * @author fran
 */
@RunWith(SpringJUnit4ClassRunner.class)
@SpringBootTest
public class OrderIntegrationTest {

    @Autowired
    OrderController orderController;
    @Autowired
    ProductController productController;
    @Autowired
    CustomerController customerController;

    @Test
    public void shouldAcceptOrder(){

        CreateCustomerResponse createCustomerResponse = customerController.createCustomer(
                new CreateCustomerRequest("fulanico",new Money(5)));

        CreateProductResponse createProductResponse = productController.createProduct(
                new CreateProductRequest("portatil",5));

        CreateOrderResponse createOrderResponse = orderController.createOrder(
                new CreateOrderRequest(createProductResponse.getId(),createCustomerResponse.getId(), 1, new Money(1)));

        assertOrderState(createOrderResponse.getId());
    }

    @Test
    public void shouldSubtractCreditAfterOrder(){

        CreateCustomerResponse createCustomerResponse = customerController.createCustomer(
                new CreateCustomerRequest("fulanico",new Money(5)));

        CreateProductResponse createProductResponse = productController.createProduct(
                new CreateProductRequest("portatil",5));

        CreateOrderResponse createOrderResponse = orderController.createOrder(
                new CreateOrderRequest(createProductResponse.getId(),createCustomerResponse.getId(), 1, new Money(1)));

        ResponseEntity<GetCustomerResponse> getCustomerResponseEntity = customerController.getCustomer(createCustomerResponse.getId());
        assertThat(new Money(4).getAmount(), Matchers.comparesEqualTo(getCustomerResponseEntity.getBody().getCredit().getAmount()));
    }

    @Test
    public void shouldSubtractStockAfterOrder(){

        CreateCustomerResponse createCustomerResponse = customerController.createCustomer(
                new CreateCustomerRequest("fulanico",new Money(5)));

        CreateProductResponse createProductResponse = productController.createProduct(
                new CreateProductRequest("portatil",5));

        CreateOrderResponse createOrderResponse = orderController.createOrder(
                new CreateOrderRequest(createProductResponse.getId(),createCustomerResponse.getId(), 1, new Money(1)));

        ResponseEntity<GetProductResponse> getProductResponseEntity = productController.getProduct(createProductResponse.getId());
        assertEquals(4, getProductResponseEntity.getBody().getStock());
    }

    @Test(expected = InsufficientCreditException.class)
    public void shouldRejectOrderByInsufficientCredit(){

        CreateCustomerResponse createCustomerResponse = customerController.createCustomer(
                new CreateCustomerRequest("fulanico",new Money(5)));

        CreateProductResponse createProductResponse = productController.createProduct(
                new CreateProductRequest("portatil",5));

        CreateOrderResponse createOrderResponse = orderController.createOrder(
                new CreateOrderRequest(createProductResponse.getId(),createCustomerResponse.getId(), 1, new Money(10)));

    }

    @Test(expected = InsufficientStockException.class)
    public void shouldRejectOrderByInsufficientStock(){

        CreateCustomerResponse createCustomerResponse = customerController.createCustomer(
                new CreateCustomerRequest("fulanico",new Money(5)));

        CreateProductResponse createProductResponse = productController.createProduct(
                new CreateProductRequest("portatil",5));

        CreateOrderResponse createOrderResponse = orderController.createOrder(
                new CreateOrderRequest(createProductResponse.getId(),createCustomerResponse.getId(), 10, new Money(1)));

    }

    private void assertOrderState(Long expectedOrderId) {
        ResponseEntity<GetOrderResponse> getOrderResponseEntity = orderController.getOrder(expectedOrderId);
        assertEquals(HttpStatus.OK, getOrderResponseEntity.getStatusCode());
    }
}
