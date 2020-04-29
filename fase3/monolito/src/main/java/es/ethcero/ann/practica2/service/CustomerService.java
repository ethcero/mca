
package es.ethcero.ann.practica2.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import es.ethcero.ann.practica2.domain.common.Money;
import es.ethcero.ann.practica2.domain.common.Operation;
import es.ethcero.ann.practica2.domain.customer.Customer;
import es.ethcero.ann.practica2.domain.customer.CustomerNotFoundException;
import es.ethcero.ann.practica2.domain.customer.CustomerRepository;
import es.ethcero.ann.practica2.service.notification.NotificationService;

/**
 * @author fran
 */
@Service
public class CustomerService {

    @Autowired
    private CustomerRepository customerRepository;

    @Autowired
    NotificationService notificationService;



    public Customer createCustomer(String name, Money credit) {
        return customerRepository.save(new Customer(name, credit));
    }

    public Customer reserveCredit(long customerId, Money orderTotal) {
        Customer customer = customerRepository.findById(customerId).orElseThrow(CustomerNotFoundException::new);
        customer.reserveCredit(orderTotal);
        return customer;
    }

    public void changeCredit(long customerId, Operation operation, Money ammount) {
        Customer customer = customerRepository.findById(customerId).orElseThrow(CustomerNotFoundException::new);
        switch (operation) {
        case ADD:
            customer.addCredit(ammount);
            break;
        case SUBTRACT:
            customer.reserveCredit(ammount);
            break;
        }

        this.save(customer);
        notificationService.notify(customer.getName(), ammount, customer.getCredit());
    }

    public void save(Customer customer) {
        customerRepository.save(customer);
    }
}
