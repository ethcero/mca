# Master Cloud Apps

## Contenedores y orquestadores

## Práctica 4. Helm

### App data

* Client: http://localhost:8080
* Server Port: `8080`
* Worker Port: `8081`
* External Service Port: `8082`
* AMQP Queues:
  * `newTasks`: schedule the works
  * `tasksProgress`: publish the progress and results

## Helm

El directorio `k8s-ingress` contiene los fichero spec de la aplicación para cargarlos directamente en k8s.

El chart de helm se encuentra en el directorio `practica4-helm3`. En la raiz se encuentra un fichero de configuración externo `config.yaml` para poder ser usado en la instalación del chart.

`helm install practica4 -f config.yaml practica4-helm3`

## Enunciado

El objetivo de esta práctica consiste en crear un chart de Helm para la Práctica 3.
Los aspectos que deben de poder configurar al desplegar el chart deben ser los siguientes:

* Los nombres de todos los recursos y etiquetas deberán prefijarse con el nombre de
la release. Se deberá verificar que dos releases con nombres diferentes pueden
desplegarse en el mismo namespace sin interferencias.

* Se deberá poder configurar el dominio en el ingress.

* Se deberá poder configurar si se aplican Network Policies o no en la release.
Se deberá crear un chart para la versión 2 y otro para la versión 3.
