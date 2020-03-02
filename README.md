# Master Cloud Apps

## Contenedores y orquestadores

### Práctica 1

#### Build

Contruye las imagenes y las sube al registry de Docker

`./build-and-publish.sh`

#### Run

Ejecuta la aplicación con las imagenes de producción.
`docker-compose -f docker-compose.yml up`

#### Develop

Contruye las imagenes en local y ejecuta la aplicación.
`docker-compose -f docker-compose-dev.yml up`

#### App data

* Client: http://localhost:8080
* Server Port: `8080`
* Worker Port: `8081`
* External Service Port: `8082`
* AMQP Queues:
    - `newTasks`: schedule the works
    - `tasksProgress`: publish the progress and results