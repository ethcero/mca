# Master Cloud Apps

##  Despliegue continuo

## Enunciando

###   Técnicas y herramientas para despliegue continuo

### Objetivo

El objetivo de la práctica es que el alumno afiance los conceptos y problemas relativos al despliegue continuo de aplicaciones.

### Se pide

Existen diferentes técnicas y herramientas para ayudar a actualizar aplicaciones en producción, y es difícil cubrir todas ellas. Por ejemplo, a la hora de actualizar, las técnicas son diferentes si la aplicación tiene estado o si no lo tiene. Además, hay herramientas que se focalizan en técnicas o tecnologías concretas. Por ejemplo, Nomad permite actualizar tanto aplicaciones dockerizadas como aplicaciones que se ejecutan de forma nativa, sin Docker.

El objetivo de la práctica es que el alumno investigue y elija alguna de estas técnicas o tecnologías y las describa o aplique. En el primer caso, bastará con describir la técnica con referencias a las fuentes, y describiendo cómo podría aplicarse con algún ejemplo concreto. En el segundo caso, se proporcionará un ejemplo práctico que pueda aplicarse en un entorno de pruebas. Si esto no fuera posible, al menos se deberían proporcionar los recursos que supuestamente permitirían aplicar el ejemplo en un entorno estándar.

Algunos ejemplos de trabajos que se pueden hacer incluyen:
- Despliegue continuo en un pipeline de CI: extender el pipeine de CI de la asignatura
de CI incluyendo despliegue automático de la aplicación de cada release. El despliegue puede basarse en un minikube con kubectl, o en Istio, o en cualqueir otra tecnología.
- Actualización de aplicaciones en Kubernetes con StatefulSets. Los StatefulSets con similares a los ReplicaSets, pero asumen que la aplicación tiene estado (por ejemplo un cluster de MySQL) y aplican ciertas restricciones en la creación y eliminación de pods. Estudiar técnicas de actualización con StatefulSets y describir con algún ejemplo cómo se podría aplicar.
- Despliegue continuo de aplicaciones con herramientas GitOps como FluxCD
- Despliegue continuo de aplicaciones o infraestructura con Nomad, CloudFormation,
Terraform, ...