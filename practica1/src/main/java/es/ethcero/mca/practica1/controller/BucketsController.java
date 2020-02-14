
package es.ethcero.mca.practica1.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import java.io.File;
import java.io.IOException;
import java.util.List;

import es.ethcero.mca.practica1.command.CopyObjectCommand;
import es.ethcero.mca.practica1.command.CreateBucketCommand;
import es.ethcero.mca.practica1.command.DeleteCommand;
import es.ethcero.mca.practica1.command.DeleteObjectCommand;
import es.ethcero.mca.practica1.command.UploadObjectCommand;
import es.ethcero.mca.practica1.service.StorageService;

/**
 * @author fran
 */
@RestController
@RequestMapping("/api/buckets")
public class BucketsController {

    @Autowired
    private StorageService storageService;

    @GetMapping
    public ResponseEntity<List<String>> getAll() {
        return ResponseEntity.ok().body(storageService.getAll());
    }

    @GetMapping("/{bucketName}/objects")
    public ResponseEntity<List<String>> getBucket(@PathVariable String bucketName) {
        return ResponseEntity.ok().body(storageService.getByName(bucketName));
    }

    @PostMapping("/{bucketName}")
    public ResponseEntity createBucket(@PathVariable String bucketName) {

        storageService.create(CreateBucketCommand.builder().bucket(bucketName).build());

        return ResponseEntity.noContent().build();
    }
    @PostMapping("/{bucketName}/uploadObject")
    public ResponseEntity uploadObjectToBucket(@PathVariable String bucketName, @RequestParam("isPublic") boolean isPublic, @RequestParam("file") MultipartFile multiPartFile) throws IOException {
        String fileName = multiPartFile.getOriginalFilename();
        File file = new File(System.getProperty("java.io.tmpdir")+"/"+fileName);
        multiPartFile.transferTo(file);
        // SAVE TO S3 AND RETURN HTTP RESPONSE

        storageService.uploadObject(
                UploadObjectCommand.builder()
                .bucket(bucketName)
                .isPublic(isPublic)
                .filename(fileName)
                .file(file)
                .build());
        return ResponseEntity.ok().build();
    }

    @DeleteMapping("/{bucketName}")
    public ResponseEntity deleteBucket(@PathVariable String bucketName) {

        storageService.delete(DeleteCommand
                .builder()
                .bucket(bucketName)
                .build()
        );
        return ResponseEntity.noContent().build();
    }

    @DeleteMapping("/{bucketName}/{objectKey}")
    public ResponseEntity deleteObjectFromBucket(@PathVariable String bucketName, @PathVariable String objectKey) {

        storageService.deleteObject(DeleteObjectCommand
                .builder()
                .bucket(bucketName)
                .key(objectKey)
                .build()
        );
        return ResponseEntity.noContent().build();
    }

    @PostMapping("/{bucketName}/{objectKey}/copy")
    public ResponseEntity copyObjectToBucket(@PathVariable String bucketName, @PathVariable String objectKey, @RequestParam("destinationBucketName") String destBucket) {

        storageService.copyObject(CopyObjectCommand
                .builder()
                .srcBucket(bucketName)
                .destBucket(destBucket)
                .objectKey(objectKey)
                .build()
        );
        return ResponseEntity.noContent().build();
    }



}
