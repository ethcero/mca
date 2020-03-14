# Master Cloud Apps

## Contenedores y orquestadores

## Práctica 3. Kubernetes

### Build

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
  * `newTasks`: schedule the works
  * `tasksProgress`: publish the progress and results

## Kubernetes

En el directorio `k8s` se encuentran los archivos de especificaciones para el despliegue de la aplicación en Kubernetes. Cada fichero contiene el Service y el Deployment correspondiente a cada componente. Se puede desplegar todo con el comando `kubectl apply -f k8s`

### Instalar Ingress

Crea el componente ngnix-ingress y los roles necesarios.

`kubectl apply -f https://raw.githubusercontent.com/kubernetes/ingress-nginx/nginx-0.30.0/deploy/static/mandatory.yaml`

#### Docker for Mac

Crea el servicio.

`kubectl apply -f https://raw.githubusercontent.com/kubernetes/ingress-nginx/nginx-0.30.0/deploy/static/provider/cloud-generic.yaml`

#### Minikube

`minikube addons enable ingress`

## Enunciado

El objetivo de esta práctica consiste en mejorar diversos aspectos de la aplicación
Kubernetes implementada en la práctica 2.
En concreto, se deberán ampliar los siguientes aspectos:

* Incluir Ingress para el servidor sea accesible en la URL `http://www.mastercloudapps.es/server/`

* Añadir PersistentVolumes para que las bases de datos puedan persistir sus datos en
el host en las rutas /db/mongo y /db/mysql. Verificad que los datos se guardan
correctamente añadiendo una web al server en la que se puedan consultar los datos
persistidos en la Mongo y que al borrar y volver a desplegar la aplicación los datos
siguen estando disponibles.

* Configurar el usuario con el que se ejecutan los contenedores server, worker y
externalservice para que sea diferente a root. En caso de que fuera necesario,
adaptar las imágenes docker para que se ejecuten correctamente.

* Definir las network-policies necesarias para que los pods únicamente se puedan
comunicar con aquellos contenedores que deberían hacerlo según la topología de
red:
  * Server con Rabbit y Mongo.
  * Worker con Rabbit y MySQL.
  * Externalservice con Worker.
