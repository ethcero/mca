package es.ethcero.mca.blogcero.responses;

import org.springframework.http.ResponseEntity;

public class ResponseObjectOrNotFound {

    private Object object;

    public ResponseObjectOrNotFound(Object object) {
        this.object = object;
    }

    public ResponseEntity response() {
        return object!=null ? ResponseEntity.ok(object) :  ResponseEntity.notFound().build();
    }
}
