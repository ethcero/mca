# Master Cloud Apps

## Repositorios y modelos de desarrollo

### Enunciado

Se encuentra trabajando en la empresa Codes&Bricks, y al equipo se le ha encomendado el
desarrollo de una nueva aplicación de comercio electrónico para un cliente. Para comenzar
es necesario realizar tres funcionalidades f1, f2 y f3.

La funcionalidad f1 deben entregarse tan pronto como sea posible, de forma que se realiza
en primer lugar y se libera una primera versión con ella. Esta La funcionalidad f1 está
compuesta de tres commits que implican la creación de los ficheros a, b y c (el contenido es
indiferente). Una vez terminada se debe generar una release de la misma.

Las funcionalidades f2 y f3 se desarrollan en paralelo.

La funcionalidad f2 está compuesta de dos commits que implican la creación del fichero d
(el contenido es indiferente) y la modificación del fichero a, donde se debe cambiar la
primera línea añadiendo al principio “f2”.

La funcionalidad f3 está compuesta de dos commits que implican la creación del fichero e
(el contenido es indiferente) y la modificación del fichero a, donde se debe cambiar la
primera línea añadiendo al final “f3”.

Ambas funcionalidades se desarrollan en paralelo por desarrolladores diferentes (no es
necesario simular este punto). En un momento dado, se termina f2 y se integra con el resto
de la aplicación. Mientras se sigue desarrollando f3. se saca una release con f2.
Inmediatamente tras la release con f2, se notifica una incidencia (bug) en producción, y se
desarrolla un hotfix consistente en añadir el fichero f al repositorio. Este hotfix se integra
donde corresponda y se saca una nueva release. Finalmente f3 se da por terminado y se
integra con el resto de la aplicación, dando lugar a una nueva release.

Se pide
Desarrollar este escenario siguiendo dos modelos de desarrollo:

- Trunk-based development
- Git flow
