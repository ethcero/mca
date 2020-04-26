
package es.ethcero.ann.practica2.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import es.ethcero.ann.practica2.domain.common.Money;
import es.ethcero.ann.practica2.domain.common.Operation;
import es.ethcero.ann.practica2.domain.customer.Customer;
import es.ethcero.ann.practica2.domain.customer.CustomerNotFoundException;
import es.ethcero.ann.practica2.domain.product.Product;
import es.ethcero.ann.practica2.domain.product.ProductNotFoundException;
import es.ethcero.ann.practica2.domain.product.ProductRepository;

/**
 * @author fran
 */
@Service
public class ProductService {

    @Autowired
    private ProductRepository productRepository;


    public Product createProduct(String name, int stock) {
        return productRepository.save(new Product(name,stock));
    }

    public Product reserveStock(long productId, int quantity) {
        Product product = productRepository.findById(productId).orElseThrow(ProductNotFoundException::new);
        product.reserveStock(quantity);
        return product;
    }

    public void save(Product product) {
        productRepository.save(product);
    }

    public void changeStock(long productId, Operation operation, int quantity) {

        Product product = productRepository.findById(productId).orElseThrow(ProductNotFoundException::new);
        switch (operation) {
        case ADD:
            product.addStock(quantity);
            break;
        case SUBTRACT:
            product.reserveStock(quantity);
            break;
        }
        this.save(product);
    }
}
