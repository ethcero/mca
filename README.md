# Master Cloud Apps

## Contenedores y orquestadores

## Práctica 2. Kubernetes

### Notas

En el directorio `k8s` se encuentran los archivos de especificaciones para el despliegue de la aplicación en Kubernetes. Cada fichero contiene el Service y el Deployment correspondiente a cada componente. Se puede desplegar todo con el comando `kubectl apply -f k8s`

El en el directorio `k8s-ingress` se encuentra la misma práctica pero usando un Ingress en el componente `server`. El requisito es tener un ingress previamente desplegado en el cluster con permisos para leer los recursos del namespace en el que la aplicación es desplegada.

#### Instalar Ingress

Crea el componente ngnix-ingress y los roles necesarios.

`kubectl apply -f https://raw.githubusercontent.com/kubernetes/ingress-nginx/nginx-0.30.0/deploy/static/mandatory.yaml`

##### Docker for Mac

Crea el servicio.

`kubectl apply -f https://raw.githubusercontent.com/kubernetes/ingress-nginx/nginx-0.30.0/deploy/static/provider/cloud-generic.yaml`

##### Minikube

`minikube addons enable ingress`

### Enunciado

El objetivo de esta práctica consiste en desplegar en Kubernetes la aplicación “dockerizada”
de la Práctica 1. Para ello, habrá que transformar el docker-compose en uno o varios
manifiestos de Kubernetes. Se tendrán que definir los deployments y servicios para
desplegar la aplicación. No se requiere el uso de ingress, ni volúmenes ni de grupos de
autoescalado