
package es.ethcero.ann.practica2.domain.customer;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

/**
 * @author fran
 */
@ResponseStatus(value = HttpStatus.PRECONDITION_FAILED, reason = "Insufficient credit")
public class InsufficientCreditException extends RuntimeException {

}
