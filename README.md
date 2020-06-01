# Master Cloud Apps

## Integración y entrega continua

## Notas

La práctica se realiza en un repositorio GitHub separado para poder integrarlo facilmente con otras herramientas. Aquí se matiene como un submódulo.

## Enunciado

### Configuración de un pipeline de integración continua

#### Objetivo

El objetivo de la práctica es que el alumno configure un pipeline de integración continua de un proyecto software poniendo en práctica los conceptos vistos en la asignatura.

#### Proyecto Software

El pipeline de integración continua se utilizará para la gestión del ciclo de vida de un proyecto software existente. El alumno puede elegir el proyecto que prefiera siempre que tenga pruebas unitarias y de sistema (e2e). Se valorará que el proyecto use una base de datos externa, pero no es obligatorio.

#### Pipeline de Integración Continua

El pipeline de Integración Continua deberá tener las siguientes características:

- Se podrá utilizar cualquier servidor de CI
- El proyecto software se alojará en un repositorio privado al cual se dará acceso al profesor.
- Se quieren ejecutar en algún momento las siguientes cosas:
  - Tests unitarios, tan a menudo como sea posible
  - Test de sistema, al menos una vez al día
  - Análisis de calidad y seguridad con Sonar, al menos una vez al día
  - Archivado de artefactos de desarrollo en el propio CI, o en algún sistema externo
  - Empaquetado y publicación de releases estables en algún repositorio de
artefactos
  - Se desea disponer todas las noches de una versión de desarrollo actualizada
con los cambios del día

El alumno debe describir cuál es el proceso de desarrollo (ramas) del proyecto y decidir cuántos jobs se definen, asociados a qué eventos en el repositorio y qué hace cada uno de ellos.

#### Se pide

Entregar un fichero pdf con la siguiente documentación:

- Asunciones sobre el modelo de desarrollo del repositorio
- Diagramas describiendo cuándo se ejecuta cada job
- Los enlaces al repositorio
- Los diferentes ficheros de configuración de los jobs