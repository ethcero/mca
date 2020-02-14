
package es.ethcero.mca.practica1.command;

import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

/**
 * @author fran
 */
@Getter
@Setter
@Builder
public class CreateBucketCommand {

    private String bucket;

}
