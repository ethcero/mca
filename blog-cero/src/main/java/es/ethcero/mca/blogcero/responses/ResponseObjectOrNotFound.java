package es.ethcero.mca.blogcero.responses;

import org.springframework.http.ResponseEntity;

import java.util.Optional;

public class ResponseObjectOrNotFound {

    private Optional<?> object;

    public ResponseObjectOrNotFound(Optional<?> object) {
        this.object = object;
    }

    public ResponseEntity response() {
        return this.object.isPresent() ? ResponseEntity.ok(object.get()) :  ResponseEntity.notFound().build();
    }
}
