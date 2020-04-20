package io.eventuate.examples.tram.sagas.ordersandcustomers.web;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import io.eventuate.examples.tram.sagas.ordersandcustomers.product.webapi.backend.domain.Product;
import io.eventuate.examples.tram.sagas.ordersandcustomers.product.webapi.backend.domain.ProductRepository;
import io.eventuate.examples.tram.sagas.ordersandcustomers.product.webapi.backend.service.ProductService;
import io.eventuate.examples.tram.sagas.ordersandcustomers.product.webapi.CreateProductRequest;
import io.eventuate.examples.tram.sagas.ordersandcustomers.product.webapi.CreateProductResponse;
import io.eventuate.examples.tram.sagas.ordersandcustomers.product.webapi.GetProductResponse;

@RestController
public class ProductController {

  private ProductService productService;
  private ProductRepository productRepository;

  @Autowired
  public ProductController(ProductService productService, ProductRepository productRepository)
  {
    this.productService = productService;
    this.productRepository = productRepository;
  }

  @RequestMapping(value = "/products", method = RequestMethod.POST)
  public CreateProductResponse createProduct(@RequestBody CreateProductRequest createProductRequest) {
    Product product = productService.createProduct(createProductRequest.getName(), createProductRequest.getStock());
    return new CreateProductResponse(product.getId());
  }

  @RequestMapping(value="/products/{productId}", method= RequestMethod.GET)
  public ResponseEntity<GetProductResponse> getProduct(@PathVariable Long productId) {
    return productRepository
            .findById(productId)
            .map(p -> new ResponseEntity<>(new GetProductResponse(p.getId(),p.getName(), p.getStock()), HttpStatus.OK))
            .orElse(new ResponseEntity<>(HttpStatus.NOT_FOUND));
  }

}
