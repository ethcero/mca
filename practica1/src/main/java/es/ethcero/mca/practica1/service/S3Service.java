
package es.ethcero.mca.practica1.service;

import com.amazonaws.regions.Regions;
import com.amazonaws.services.s3.AmazonS3;
import com.amazonaws.services.s3.AmazonS3ClientBuilder;
import com.amazonaws.services.s3.model.Bucket;
import com.amazonaws.services.s3.model.CannedAccessControlList;
import com.amazonaws.services.s3.model.CopyObjectRequest;
import com.amazonaws.services.s3.model.PutObjectRequest;
import com.amazonaws.services.s3.model.PutObjectResult;
import com.amazonaws.services.s3.model.S3ObjectSummary;

import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

import es.ethcero.mca.practica1.command.CopyObjectCommand;
import es.ethcero.mca.practica1.command.CreateBucketCommand;
import es.ethcero.mca.practica1.command.DeleteCommand;
import es.ethcero.mca.practica1.command.DeleteObjectCommand;
import es.ethcero.mca.practica1.command.UploadObjectCommand;

/**
 * @author fran
 */
@Service
public class S3Service implements StorageService{



    private final AmazonS3 s3 =  AmazonS3ClientBuilder
            . standard()
            . withRegion(Regions.US_EAST_1)
            . build();

    @Override
    public List<String> getAll() {
        // List buckets
        return
                s3.listBuckets().stream()
                        .map(Bucket::getName)
                        .collect(Collectors.toList());
    }

    @Override
    public List<String> getByName(String bucket) {
        return s3.listObjectsV2(bucket).getObjectSummaries()
                .stream()
                .map(S3ObjectSummary::getKey).collect(Collectors.toList());
    }

    @Override
    public void create(CreateBucketCommand command) {
        if(s3.doesBucketExistV2(command.getBucket())) {
            System.out.println("Error: bucket name: "+command.getBucket()+" is not available");
            return;
        }
        s3.createBucket(command.getBucket());
    }

    @Override
    public void uploadObject(UploadObjectCommand command) {

        PutObjectRequest request = new PutObjectRequest(
                command.getBucket(),
                command.getFilename(),
                command.getFile())
                .withCannedAcl(getAcl(command.isPublic()));


        PutObjectResult res = s3.putObject(request);

    }

    @Override
    public void deleteObject(DeleteObjectCommand command) {

        s3.deleteObject(command.getBucket(), command.getKey());
    }

    @Override
    public void delete(DeleteCommand command) {

        deleteObjects(command.getBucket());
        s3.deleteBucket(command.getBucket());

    }

    @Override
    public void copyObject(CopyObjectCommand command) {

        CopyObjectRequest request = new CopyObjectRequest(
                command.getSrcBucket(),
                command.getObjectKey(),
                command.getDestBucket(),
                command.getObjectKey()
        );
        s3.copyObject(request);
    }

    private void deleteObjects(String bucket) {
        s3.listObjectsV2(bucket).getObjectSummaries()
        .forEach(s3ObjectSummary -> s3.deleteObject(bucket, s3ObjectSummary.getKey()));
    }

    private CannedAccessControlList getAcl(boolean isPublic) {
        return isPublic ? CannedAccessControlList.PublicRead : CannedAccessControlList.Private;
    }
}
