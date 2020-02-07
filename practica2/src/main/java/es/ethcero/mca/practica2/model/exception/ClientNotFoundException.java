
package es.ethcero.mca.practica2.model.exception;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

/**
 * @author fran
 */
@ResponseStatus(value= HttpStatus.NOT_FOUND, reason = "Client not found")
public class ClientNotFoundException extends RuntimeException {}
