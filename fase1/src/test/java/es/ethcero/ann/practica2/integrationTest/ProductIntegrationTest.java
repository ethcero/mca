
package es.ethcero.ann.practica2.integrationTest;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import static org.junit.Assert.assertEquals;

import es.ethcero.ann.practica2.web.product.CreateProductRequest;
import es.ethcero.ann.practica2.web.product.CreateProductResponse;
import es.ethcero.ann.practica2.web.product.GetProductResponse;
import es.ethcero.ann.practica2.web.product.ProductController;

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

        assertProductState(createProductResponse.getId());
    }

    private void assertProductState(Long expectedProductId) {

        ResponseEntity<GetProductResponse> getProductResponseEntity = productController.getProduct(expectedProductId);
        assertEquals(HttpStatus.OK, getProductResponseEntity.getStatusCode());
    }
}
