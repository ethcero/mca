# Master Cloud Apps

## Aplicaciones nativas en la nube

### Practica 3. Serverless

#### Despliegue en AWS

```bash
sam build
sam deploy --guided
```

#### Cleanup

To delete the sample application that you created, use the AWS CLI. Assuming you used your project name for the stack name, you can run the following:

```bash
aws cloudformation delete-stack --stack-name sam-app
```

#### Pruebas en local

AWS SAM CLI puede emular el API de la aplicación. Usamos el comando `sam local start-api` para ejecutar el API localmente en el puerto 3000.

Para ejecutar correctamente la aplicación en local necesitamos una instancia de DynamoDB. El script `init_local_dynamodb.sh` levanta un docker con DynamoDB e inicializa la tabla que necesita la aplicación.

Después podemos inicializar la aplicación en local usando el template `template-local.yaml`.

El fichero `template-local.yaml` contiene una variable de entorno configurada en cada Function para indicar el acceso a DynamoDB en local.

```bash
AWS_DYNAMODB_LOCAL: http://host.docker.internal:8000
```

La secuencia completa sería dentro del directorio `sam-app`:

```bash
my-application$ sam build -t template-local.yaml
my-application$ ./init_local_dynamodb.sh
my-application$ sam local start-api -t template-local.yaml
my-application$ curl http://localhost:3000/
```

Se puede usar la colección Postman configurando el parámetro `APP_URL=http://localhost:3000` para testear la aplicación completa.

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