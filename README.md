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

El en el directorio `k8s-ingress` se encuentra la misma práctica pero usando un Ingress en el componente `server`. El requisito es tener un ingress previamente desplegado en el cluster con permisos para leer los recursos del namespace en el que la aplicación es desplegada.

### Instalar Ingress

Crea el componente ngnix-ingress y los roles necesarios.

`kubectl apply -f https://raw.githubusercontent.com/kubernetes/ingress-nginx/nginx-0.30.0/deploy/static/mandatory.yaml`

#### Docker for Mac

Crea el servicio.

`kubectl apply -f https://raw.githubusercontent.com/kubernetes/ingress-nginx/nginx-0.30.0/deploy/static/provider/cloud-generic.yaml`

#### Minikube

`minikube addons enable ingress`
