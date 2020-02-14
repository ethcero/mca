
package es.ethcero.mca.practica1.command;

import java.io.File;

import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

/**
 * @author fran
 */
@Builder
@Setter
@Getter
public class UploadObjectCommand {

    private String bucket;
    private String filename;
    private File file;
    private boolean isPublic = false;

    public boolean isPublic() {
        return isPublic;
    }
}
