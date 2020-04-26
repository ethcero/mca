/**
 * Copyright (C) 2020 Deveryware S.A. All Rights Reserved.
 */
package es.ethcero.ann.practica2.apigateway;

/**
 * @author fran
 */

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.jdbc.DataSourceAutoConfiguration;
import org.springframework.boot.autoconfigure.jdbc.DataSourceTransactionManagerAutoConfiguration;
import org.springframework.boot.autoconfigure.orm.jpa.HibernateJpaAutoConfiguration;
import org.springframework.cloud.gateway.route.RouteLocator;
import org.springframework.cloud.gateway.route.builder.RouteLocatorBuilder;
import org.springframework.context.annotation.Bean;

import es.ethcero.ann.practica2.apigateway.common.ServiceDestination;

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

                .route("orders", r -> r.path("/orders/**").uri(ServiceDestination.ORDERS_URI))
                .route("customers", r -> r.path("/customers/**").uri(ServiceDestination.CUSTOMERS_URI))
                .route("products", r -> r.path("/products/**").uri(ServiceDestination.PRODUCTS_URI))
                .build();
    }
}
