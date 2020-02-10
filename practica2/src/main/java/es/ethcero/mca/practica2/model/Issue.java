
package es.ethcero.mca.practica2.model;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;
import java.util.List;

/**
 * @author fran
 */
@Entity
@NoArgsConstructor
@Getter
@Setter
public class Issue  {

    private static final int FRAUD_MAX_ISSUES = 2;
    private static final double FRAUD_MAX_AMOUNT = 1000;

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private long id;

    private Double amount;
    private Coverage coverage;
    private boolean fraud = false;
    private boolean isCovered = false;

    public Issue(Double amount, Coverage coverage) {
        this.amount = amount;
        this.coverage = coverage;
    }

    public void checkCovered(List<Coverage> coverages) {
        if(coverages.contains(this.coverage)) {
            this.isCovered = true;
        }
    }

    public void checkFraud(int totalIssues, double totalAmount) {
         if(totalIssues >= FRAUD_MAX_ISSUES && totalAmount > FRAUD_MAX_AMOUNT) {
             this.fraud = true;
         }
    }

}
