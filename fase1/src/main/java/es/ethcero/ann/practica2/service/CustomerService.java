
package es.ethcero.ann.practica2.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import es.ethcero.ann.practica2.domain.Money;
import es.ethcero.ann.practica2.domain.customer.Customer;
import es.ethcero.ann.practica2.domain.customer.CustomerNotFoundException;
import es.ethcero.ann.practica2.domain.customer.CustomerRepository;

/**
 * @author fran
 */
@Service
public class CustomerService {

    @Autowired
    private CustomerRepository customerRepository;

    @Autowired NotificationService notificationService;

    public Customer createCustomer(String name, Money credit) {
        return customerRepository.save(new Customer(name, credit));
    }

    public Customer reserveCredit(long customerId, Money orderTotal) {
        Customer customer = customerRepository.findById(customerId).orElseThrow(CustomerNotFoundException::new);
        customer.reserveCredit(orderTotal);
        return customer;
    }

    public void addCredit(long customerId, Money ammount) {
        Customer customer = customerRepository.findById(customerId).orElseThrow(CustomerNotFoundException::new);
        customer.addCredit(ammount);
        this.save(customer);
        notificationService.notify(customer.getName(), ammount, customer.getCredit());
    }

    public void save(Customer customer) {
        customerRepository.save(customer);
    }
}
