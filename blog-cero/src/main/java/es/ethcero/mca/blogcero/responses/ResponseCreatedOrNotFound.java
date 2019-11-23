package es.ethcero.mca.blogcero.responses;

import org.springframework.http.ResponseEntity;

public class ResponseCreatedOrNotFound {

    private Object object;

    public ResponseCreatedOrNotFound(Object object) {
        this.object = object;
    }

    public ResponseEntity response() {
        return object!=null ? ResponseEntity.created(null).body(object) :  ResponseEntity.notFound().build();
    }
}
