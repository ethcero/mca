
package es.ethcero.ann.practica2.integrationTest;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import static org.junit.Assert.assertEquals;

import es.ethcero.ann.practica2.domain.common.Operation;
import es.ethcero.ann.practica2.domain.product.InsufficientStockException;
import es.ethcero.ann.practica2.web.product.CreateProductRequest;
import es.ethcero.ann.practica2.web.product.CreateProductResponse;
import es.ethcero.ann.practica2.web.product.GetProductResponse;
import es.ethcero.ann.practica2.web.product.ProductController;
import es.ethcero.ann.practica2.web.product.StockOperationRequest;

/**
 * @author fran
 */
@RunWith(SpringJUnit4ClassRunner.class)
@SpringBootTest
public class ProductIntegrationTest {

    @Autowired
    ProductController productController;

    @Test
    public void shouldCreateProduct(){

        CreateProductResponse createProductResponse = productController.createProduct(
                new CreateProductRequest("portatil",5));

        assertProductState(createProductResponse.getId(), 5);
    }

    @Test
    public void shouldAddStock(){
        CreateProductResponse createProductResponse = productController.createProduct(
                new CreateProductRequest("portatil",5));

        productController.changeStock(createProductResponse.getId(), new StockOperationRequest(Operation.ADD, 5));

        assertProductState(createProductResponse.getId(), 10);

    }

    @Test
    public void shouldSubtractStock(){
        CreateProductResponse createProductResponse = productController.createProduct(
                new CreateProductRequest("portatil",5));

        productController.changeStock(createProductResponse.getId(), new StockOperationRequest(Operation.SUBTRACT, 5));

        assertProductState(createProductResponse.getId(), 0);

    }

    @Test(expected = InsufficientStockException.class)
    public void shouldFailWhenInsufficientStock(){
        CreateProductResponse createProductResponse = productController.createProduct(
                new CreateProductRequest("portatil",5));

        productController.changeStock(createProductResponse.getId(), new StockOperationRequest(Operation.SUBTRACT, 15));
    }

    private void assertProductState(Long expectedProductId, int expectedStock) {

        ResponseEntity<GetProductResponse> getProductResponseEntity = productController.getProduct(expectedProductId);
        assertEquals(HttpStatus.OK, getProductResponseEntity.getStatusCode());
        assertEquals(expectedStock, getProductResponseEntity.getBody().getStock());
    }
}
