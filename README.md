# Master Cloud Apps

 ## Aplicaciones nativas en la nube

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