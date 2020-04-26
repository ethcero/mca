
package es.ethcero.ann.practica2.web.customer;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import es.ethcero.ann.practica2.domain.customer.Customer;
import es.ethcero.ann.practica2.domain.customer.CustomerRepository;
import es.ethcero.ann.practica2.service.CustomerService;

/**
 * @author fran
 */
@RestController
public class CustomerController {

    @Autowired
    private CustomerService customerService;
    @Autowired
    private CustomerRepository customerRepository;

    @PostMapping("/customers")
    public CreateCustomerResponse createCustomer(@RequestBody CreateCustomerRequest createCustomerRequest) {
        Customer customer = customerService.createCustomer(createCustomerRequest.getName(), createCustomerRequest.getCredit());
        return new CreateCustomerResponse(customer.getId());
    }

    @GetMapping("/customers/{customerId}")
    public ResponseEntity<GetCustomerResponse> getCustomer(@PathVariable long customerId) {
        return customerRepository
                .findById(customerId)
                    .map(c -> new ResponseEntity<>(new GetCustomerResponse(c.getId(),c.getName(),c.getCredit()), HttpStatus.OK))
                .orElse(new ResponseEntity<>(HttpStatus.NOT_FOUND));
    }

    @PutMapping("customers/{customerId}")
    public ResponseEntity addCredit(@PathVariable long customerId, @RequestBody AddCreditRequest addCreditRequest) {
        customerService.addCredit(customerId, addCreditRequest.getAmmount());
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }

}
