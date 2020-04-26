
package es.ethcero.ann.practica2.web.product;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

/**
 * @author fran
 */
@Getter
@NoArgsConstructor
@AllArgsConstructor
public class CreateProductRequest {

    private String name;
    private int stock;
}
