
package es.ethcero.mca.practica1.service;

import java.util.List;

import es.ethcero.mca.practica1.command.UploadObjectCommand;
import es.ethcero.mca.practica1.command.CopyObjectCommand;
import es.ethcero.mca.practica1.command.CreateBucketCommand;
import es.ethcero.mca.practica1.command.DeleteCommand;
import es.ethcero.mca.practica1.command.DeleteObjectCommand;

/**
 * @author fran
 */
public interface StorageService {

    public List<String> getAll();
    public List<String> getByName(String name);
    public void create(CreateBucketCommand command);
    public void uploadObject(UploadObjectCommand command);
    public void deleteObject(DeleteObjectCommand command);
    public void delete(DeleteCommand command);
    public void copyObject(CopyObjectCommand command);
}
