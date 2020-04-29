
package es.ethcero.ann.practica2.orderservice.proxy.product;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Component;
import org.springframework.web.client.RestTemplate;

import es.ethcero.ann.practica2.orderservice.common.Operation;
import es.ethcero.ann.practica2.orderservice.proxy.ServiceDestination;

/**
 * @author fran
 */
@Component
public class ProductServiceProxy {

    @Autowired
    RestTemplate restTemplate;

    private String baseUrlProducts(String path) {
        return ServiceDestination.PRODUCTS_URI + "/" + path;
    }

    public void reserveStock(long productId, int quantity) {
        restTemplate.patchForObject(baseUrlProducts("products/"+productId),
                new StockOperationRequest(Operation.SUBTRACT, quantity), ResponseEntity.class);
    }

    public void addStock(long productId, int quantity) {
        restTemplate.patchForObject(baseUrlProducts("products/"+productId),
                new StockOperationRequest(Operation.ADD, quantity), ResponseEntity.class);
    }
}
