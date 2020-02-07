
package es.ethcero.mca.practica2.ui.command;

import es.ethcero.mca.practica2.model.Coverage;
import lombok.Getter;
import lombok.Setter;

/**
 * @author fran
 */
@Getter
@Setter
public class NewIssueCommand {

    private long insuranceId;
    private Double amount;
    private Coverage coverage;

}
