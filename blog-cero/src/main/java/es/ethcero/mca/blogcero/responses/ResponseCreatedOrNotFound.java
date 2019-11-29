package es.ethcero.mca.blogcero.responses;

import org.springframework.http.ResponseEntity;

import java.util.Optional;

public class ResponseCreatedOrNotFound {

    private Optional<?> object;

    public ResponseCreatedOrNotFound(Optional<?> object) {
        this.object = object;
    }

    public ResponseEntity response() {
        return this.object.isPresent() ? ResponseEntity.created(null).body(object.get()) :  ResponseEntity.notFound().build();
    }
}
