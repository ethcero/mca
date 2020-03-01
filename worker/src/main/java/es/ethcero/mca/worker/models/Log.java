
package es.ethcero.mca.worker.models;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

/**
 * @author fran
 */
@Entity
public class Log {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private long id;
    private String message;

    public Log(String message) {
        this.message = message;
    }

}
