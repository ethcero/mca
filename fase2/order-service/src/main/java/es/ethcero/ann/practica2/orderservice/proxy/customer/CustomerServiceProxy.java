
package es.ethcero.ann.practica2.orderservice.proxy.customer;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Component;
import org.springframework.web.client.RestTemplate;

import es.ethcero.ann.practica2.orderservice.common.Money;
import es.ethcero.ann.practica2.orderservice.common.Operation;
import es.ethcero.ann.practica2.orderservice.proxy.ServiceDestination;

/**
 * @author fran
 */
@Component
public class CustomerServiceProxy {

    @Autowired
    RestTemplate restTemplate;

    private String baseUrlProducts(String path) {
        return ServiceDestination.CUSTOMERS_URI + "/" + path;
    }

    public void reserveCredit(long customerId, Money amount) {
        restTemplate.patchForObject(baseUrlProducts("customers/"+customerId),
                new CreditOperationRequest(Operation.SUBTRACT, amount), ResponseEntity.class);
    }

    public void addCredit(long customerId, Money amount) {
        restTemplate.patchForObject(baseUrlProducts("customers/"+customerId),
                new CreditOperationRequest(Operation.ADD, amount), ResponseEntity.class);
    }
}
