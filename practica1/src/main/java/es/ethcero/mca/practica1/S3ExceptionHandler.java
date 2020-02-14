
package es.ethcero.mca.practica1;

import com.amazonaws.AmazonServiceException;

import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import org.springframework.web.context.request.WebRequest;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

/**
 * @author fran
 */
@RestControllerAdvice
public class S3ExceptionHandler extends ResponseEntityExceptionHandler {

    @ExceptionHandler(value
            = { AmazonServiceException.class })
    protected ResponseEntity<Object> handleAWSException(RuntimeException ex, WebRequest request) {
        AmazonServiceException awsEx = (AmazonServiceException)ex;
        return handleExceptionInternal(ex,awsEx.getErrorMessage(),
                new HttpHeaders(), HttpStatus.valueOf(awsEx.getStatusCode()), request);
    }

}
