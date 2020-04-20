
package io.eventuate.examples.tram.sagas.ordersandcustomers.apigateway;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.jdbc.DataSourceAutoConfiguration;
import org.springframework.boot.autoconfigure.jdbc.DataSourceTransactionManagerAutoConfiguration;
import org.springframework.boot.autoconfigure.orm.jpa.HibernateJpaAutoConfiguration;
import org.springframework.cloud.gateway.route.RouteLocator;
import org.springframework.cloud.gateway.route.builder.RouteLocatorBuilder;
import org.springframework.context.annotation.Bean;
import org.springframework.web.reactive.function.server.RouterFunction;
import org.springframework.web.reactive.function.server.RouterFunctions;
import org.springframework.web.reactive.function.server.ServerResponse;

import static org.springframework.web.reactive.function.server.RequestPredicates.GET;

import io.eventuate.examples.tram.sagas.ordersandcustomers.apigateway.common.ServiceDestinations;
import io.eventuate.examples.tram.sagas.ordersandcustomers.apigateway.handlers.OrderHandlers;
import io.eventuate.examples.tram.sagas.ordersandcustomers.apigateway.service.OrderServiceProxy;
import io.eventuate.examples.tram.sagas.ordersandcustomers.apigateway.service.ProductServiceProxy;

/**
 * @author fran
 */
@SpringBootApplication(exclude = {
        DataSourceAutoConfiguration.class,
        DataSourceTransactionManagerAutoConfiguration.class,
        HibernateJpaAutoConfiguration.class
})
public class GatewayApplication {



    public static void main(String[] args) {
        SpringApplication.run(GatewayApplication.class, args);
    }

    @Bean
    public RouteLocator customRouteLocator(RouteLocatorBuilder builder) {
        return builder.routes()

                .route("orders", r -> r.path("/orders/**").uri(ServiceDestinations.ORDERS_URI))
                .route("customers", r -> r.path("/customers/**").uri(ServiceDestinations.CUSTOMERS_URI))
                .route("products", r -> r.path("/products/**").uri(ServiceDestinations.PRODUCTS_URI))
                .build();
    }

    @Bean
    public RouterFunction<ServerResponse> orderHandlerRouting(OrderHandlers orderHandlers) {
        return RouterFunctions.route(GET("/orders/{orderId}/details"), orderHandlers::getOrderDetails);
    }

    @Bean
    public OrderHandlers orderHandlers(OrderServiceProxy orderService, ProductServiceProxy productServiceProxy) {

        return new OrderHandlers(orderService, productServiceProxy);
    }

}
