
package es.ethcero.ann.practica2.integrationTest;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import static org.junit.Assert.assertEquals;

import es.ethcero.ann.practica2.domain.common.Money;
import es.ethcero.ann.practica2.domain.common.Operation;
import es.ethcero.ann.practica2.domain.customer.InsufficientCreditException;
import es.ethcero.ann.practica2.web.customer.CreditOperationRequest;
import es.ethcero.ann.practica2.web.customer.CreateCustomerRequest;
import es.ethcero.ann.practica2.web.customer.CreateCustomerResponse;
import es.ethcero.ann.practica2.web.customer.CustomerController;
import es.ethcero.ann.practica2.web.customer.GetCustomerResponse;

/**
 * @author fran
 */
@RunWith(SpringJUnit4ClassRunner.class)
@SpringBootTest
public class CustomerIntegrationTest {

    @Autowired
    CustomerController customerController;

    @Test
    public void shouldCreateCustomer(){

        CreateCustomerResponse createCustomerResponse = customerController.createCustomer(
                new CreateCustomerRequest("fulanico",new Money(5)));

        assertCustomerState(createCustomerResponse.getId(),new Money(5));
    }

    @Test
    public void shouldAddCreditToCustomer(){

        CreateCustomerResponse createCustomerResponse = customerController.createCustomer(
                new CreateCustomerRequest("fulanico",new Money(5)));

        customerController.changeCredit(
                createCustomerResponse.getId(),
                new CreditOperationRequest(Operation.ADD, new Money(5)));

        assertCustomerState(createCustomerResponse.getId(), new Money(10));
    }

    @Test
    public void shouldSubtractCreditToCustomer(){

        CreateCustomerResponse createCustomerResponse = customerController.createCustomer(
                new CreateCustomerRequest("fulanico",new Money(5)));

        customerController.changeCredit(
                createCustomerResponse.getId(),
                new CreditOperationRequest(Operation.SUBTRACT, new Money(5)));

        assertCustomerState(createCustomerResponse.getId(), new Money(0));
    }

    @Test(expected = InsufficientCreditException.class)
    public void shouldFailWhenSubtractAndInsufficientCredit(){

        CreateCustomerResponse createCustomerResponse = customerController.createCustomer(
                new CreateCustomerRequest("fulanico",new Money(5)));

        customerController.changeCredit(
                createCustomerResponse.getId(),
                new CreditOperationRequest(Operation.SUBTRACT, new Money(15)));

    }

    private void assertCustomerState(Long expectedCustomerId, Money expectedCredit) {

        ResponseEntity<GetCustomerResponse> getCustomerResponseEntity = customerController.getCustomer(expectedCustomerId);
        assertEquals(HttpStatus.OK, getCustomerResponseEntity.getStatusCode());
        assertEquals(expectedCredit, getCustomerResponseEntity.getBody().getCredit() );
    }
}
