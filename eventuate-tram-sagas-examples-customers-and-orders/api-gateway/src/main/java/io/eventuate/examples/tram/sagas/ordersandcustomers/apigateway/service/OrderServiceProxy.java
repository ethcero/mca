
package io.eventuate.examples.tram.sagas.ordersandcustomers.apigateway.service;

import org.springframework.stereotype.Service;
import org.springframework.web.reactive.function.client.ClientResponse;
import org.springframework.web.reactive.function.client.WebClient;

import io.eventuate.examples.tram.sagas.ordersandcustomers.apigateway.common.ServiceDestinations;
import io.eventuate.examples.tram.sagas.ordersandcustomers.orders.webapi.GetOrderDetailsResponse;
import io.eventuate.examples.tram.sagas.ordersandcustomers.orders.webapi.GetOrderResponse;
import reactor.core.publisher.Mono;

/**
 * @author fran
 */
@Service
public class OrderServiceProxy {

    private WebClient client;


    public OrderServiceProxy(WebClient.Builder webClientBuilder) {

        this.client =  webClientBuilder.baseUrl(ServiceDestinations.ORDERS_URI).build();
    }

    public Mono<GetOrderResponse> findOrderById(String orderId) {
        Mono<ClientResponse> response =
                client
                    .get()
                    .uri("/orders/{orderId}", orderId)
                    .exchange();

        return response.flatMap(resp -> resp.bodyToMono(GetOrderResponse.class));
    }
    public Mono<GetOrderDetailsResponse> findOrderDetailsById(String orderId) {
        Mono<ClientResponse> response =
                client
                        .get()
                        .uri("/orders/{orderId}/details", orderId)
                        .exchange();

        return response.flatMap(resp -> resp.bodyToMono(GetOrderDetailsResponse.class));
    }
}
