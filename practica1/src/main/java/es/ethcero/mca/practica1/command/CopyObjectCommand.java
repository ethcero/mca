
package es.ethcero.mca.practica1.command;

import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

/**
 * @author fran
 */
@Setter
@Getter
@Builder
public class CopyObjectCommand {

    private String srcBucket;
    private String destBucket;
    private String objectKey;
}
