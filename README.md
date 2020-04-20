# Master Cloud Apps

 ## Aplicaciones nativas en la nube
 
 
### NOTAS

Para resolver la primera parte del enunciado, que añade control de stock, se crean dos nuevos módulos en el proyecto.

- `product`: Contiene las APIs y backend del microservicio
- `product-service`: Contiene el Controller SpringBoot
- `product-web-api`: Contiene los pojos de la interfaz rest
- `product-api`: Contiene la interfaz de backend

Se añade el paso de verificar stock a la saga en el microservicio Orders. Directorio `order-backend` `CreateOrderSaga.java`

Se amplian los tests E2E para añadir la nueva funcionalidad de Stock. Directorio `end-to-end-tests`


Paso para ponerlo a funcionar:

1. Compilar la aplicación
    ```
    ./gradlew assemble
    ```
2. Lanza los servicios
    
    ```
    export DOCKER_HOST_IP=...
    ./gradlew mysqlComposeBuild
    ./gradlew mysqlComposeUp
    ```
    
    > You need to set DOCKER_HOST_IP before running Docker Compose. This must be an IP address or resolvable hostname. It cannot be localhost. See this guide to setting DOCKER_HOST_IP for more information.

3. Lanza los tests E2E para verificar el funcionamiendo
    
    ```
    ./gradlew :end-to-end-tests:cleanTest :end-to-end-tests:test --info
    ```

#### API Gateway

El API Gateway se encuentra en el módulo `api-gateway` y se ha implementado el endopoint `/orders/{orderId}/details` donde se devuelve los detalles del Order y el Product
```
{
    "orderId": 1,
    "orderState": "APPROVED",
    "rejectionReason": null,
    "productName": "lapiz"
}
```

El gateway se despliega en el puerto 8080

Se adjunta una colección de Postman para facilitar el testeado del API.

### Enunciado

El objetivo de esta práctica consiste en implementar una aplicación derivada de un ejemplo
implementado con el framework Eventuate.

En concreto, se pide lo siguiente (para 7.5):

- Añadir un nuevo microservicio a la [aplicación Orders - Consumers](https://github.com/eventuate-tram/eventuate-tram-sagas-examples-customers-and-orders).
- Este nuevo microservicio se encargará de controlar el stock del producto que pide el
usuario, de forma que si el stock no es suficiente, el pedido se rechazará.
- Queda a libertad del alumno ampliar los datos del pedido para poder referenciar el
producto y su stock.
- Los productos tendrán como dato extra su nombre.
- Será necesario implementar la API REST para el microservicio de productos de
forma que se pueda consultar su stock.

Opcional (para el 10):

- Se deberá incluir un API Gateway basado en Spring API Gateway que permita
obtener, con una única petición, información del pedido e información del producto
en un pedido concreto
- Además se incluirá en el API Gateway en enrutado de los servicios order, consumer
y product.