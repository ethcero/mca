
package es.ethcero.mca.practica2.model;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

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

    private Double amount;
    private Coverage coverage;
    private boolean fraud = false;
    private boolean isCovered = true;

    public Issue(Double amount, Coverage coverage) {
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
