# Master Cloud Apps

## Aplicaciones nativas en la nube

### Practica 3. Serverless

### Enunciado

El objetivo de esta práctica consiste en implementar una API REST con las tecnologías serverless ofrecidas por AWS. En concreto, se utilizarán las siguientes:

* API Gateway
* Lambda
* DynamoDB
* SAM

La aplicación deberá ofrecer la misma funcionalidad que la práctica 2 de la asignatura “Tecnologías de servicios de Internet”. En concreto, ofrecerá una API REST que permitirá gestionar un foro:

* El foro está formado por 2 entidades:
  * Entradas​. Cada entrada contiene los siguientes campos: Nombre del autor,
Nickname del autor, Titulo, Texto, Lista de comentarios.
  * Comentarios​. Cada comentario contiene los siguientes campos: Nickname
del autor del comentario, Contenido, Fecha del comentario.
* La aplicación ofrecerá ​7 endpoints REST​, a saber:
  * Recuperación de todas las entradas
  * Recuperación de una entrada
  * Adición de una nueva entrada
  * Borrado de una entrada existente
  * Modificación de una entrada existente (se modificará la entrada al completo)
  * Adición de un nuevo comentario a una entrada
  * Borrado de un comentario existente

Para simplificar la implementación de la versión serverless, se ofrece en el enunciado la práctica implementada con Node, Express y MongoDB. Además, también se ofrece una colección Postman para verificar que la implementación serverless es correcta funcionalmente.

Para el desarrollo de la práctica se puede obtener inspiración del ejemplo
https://github.com/MasterCloudApps/3.4.Aplicaciones-nativas-de-la-nube.T2-Serverless/tree /master/sections/4-Databases