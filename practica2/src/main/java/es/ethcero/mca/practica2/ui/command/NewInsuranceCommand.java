
package es.ethcero.mca.practica2.ui.command;

import es.ethcero.mca.practica2.model.Address;
import es.ethcero.mca.practica2.model.Coverage;
import lombok.Getter;
import lombok.Setter;

import java.util.List;

/**
 * @author fran
 */
@Getter
@Setter
public class NewInsuranceCommand {

    Address address;
    List<Coverage> coverages;

}
