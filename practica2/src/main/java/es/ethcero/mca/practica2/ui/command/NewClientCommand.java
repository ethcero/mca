
package es.ethcero.mca.practica2.ui.command;

import es.ethcero.mca.practica2.model.Address;
import lombok.Getter;
import lombok.Setter;

/**
 * @author fran
 */
@Getter
@Setter
public class NewClientCommand {

    private String name;
    private Address address;

}
