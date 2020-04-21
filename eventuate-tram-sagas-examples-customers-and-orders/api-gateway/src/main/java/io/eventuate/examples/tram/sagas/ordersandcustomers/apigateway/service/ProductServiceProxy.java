
package io.eventuate.examples.tram.sagas.ordersandcustomers.apigateway.service;

import org.springframework.stereotype.Service;
import org.springframework.web.reactive.function.client.ClientResponse;
import org.springframework.web.reactive.function.client.WebClient;

import io.eventuate.examples.tram.sagas.ordersandcustomers.apigateway.common.ServiceDestinations;
import io.eventuate.examples.tram.sagas.ordersandcustomers.product.webapi.GetProductResponse;
import reactor.core.publisher.Mono;

/**
 * @author fran
 */
@Service
public class ProductServiceProxy {

    private WebClient client;

    public ProductServiceProxy(WebClient.Builder webClientBuilder) {

        this.client =  webClientBuilder.baseUrl(ServiceDestinations.PRODUCTS_URI).build();
    }
    public Mono<GetProductResponse> findProductById(Long productId) {
        Mono<ClientResponse> response =
                client
                    .get()
                    .uri("/products/{productId}", productId)
                    .exchange();

        return response.flatMap(resp -> resp.bodyToMono(GetProductResponse.class));
    }
}
