
package io.eventuate.examples.tram.sagas.ordersandcustomers.apigateway.common;

/**
 * @author fran
 */
public class ServiceDestinations {
/*    public static final String HOST = "host.docker.internal";
    public static final String ORDERS_URI= "http://"+HOST+":8081";
    public static final String CUSTOMERS_URI= "http://"+HOST+":8082";
    public static final String PRODUCTS_URI= "http://"+HOST+   ":8083";
*/
    public static final String ORDERS_URI= "http://orderservice:8080";
    public static final String CUSTOMERS_URI= "http://customerservice:8080";
    public static final String PRODUCTS_URI= "http://productservice:8080";

}
