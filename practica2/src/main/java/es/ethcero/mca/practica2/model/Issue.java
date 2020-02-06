
package es.ethcero.mca.practica2.model;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

/**
 * @author fran
 */
@Entity
@NoArgsConstructor
@Getter
@Setter
public class Issue  {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private long id;
    private long clientId;

    private Double amount;
    private Coverage coverage;
    private boolean fraud = false;
    private boolean isCovered = true;

    public Issue(long clientId, Double amount, Coverage coverage) {
        this.clientId = clientId;
        this.amount = amount;
        this.coverage = coverage;
    }

    public Double getAmount() {
        return amount;
    }

    public void setFraud() {
        this.fraud = true;
    }

    public void notCovered() {
        isCovered = false;
    }
}
