
package es.ethcero.ann.practica2.web.product;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PatchMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import es.ethcero.ann.practica2.domain.product.Product;
import es.ethcero.ann.practica2.domain.product.ProductRepository;
import es.ethcero.ann.practica2.service.ProductService;

/**
 * @author fran
 */
@RestController
public class ProductController {

    @Autowired
    private ProductService productService;
    @Autowired
    private ProductRepository productRepository;

    @PostMapping("/products")
    public CreateProductResponse createProduct(@RequestBody CreateProductRequest createProductRequest) {
        Product product = productService.createProduct(createProductRequest.getName(), createProductRequest.getStock());
        return new CreateProductResponse(product.getId());
    }

    @GetMapping("/products/{productId}")
    public ResponseEntity<GetProductResponse> getProduct(@PathVariable long productId) {
        return productRepository
                .findById(productId)
                .map(p -> new ResponseEntity<>(new GetProductResponse(p.getId(),p.getName(),p.getStock()), HttpStatus.OK))
                .orElse(new ResponseEntity<>(HttpStatus.NOT_FOUND));
    }

    @PatchMapping("/products/{productId}")
    public ResponseEntity<GetProductResponse> changeStock(@PathVariable long productId, @RequestBody StockOperationRequest stockOperationRequest) {
        productService.changeStock(productId, stockOperationRequest.getOperation(), stockOperationRequest.getQuantity());

        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }

}
