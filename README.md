# Master Cloud Apps

## Escalabilidad y tolerancia a fallos

## Video

<https://drive.google.com/open?id=1TOxaRdN0cuOdOMaFUk1uHyR4GkY8KjXp>

## Resultados

### Escenario 1

```text
RPS: 15
Duración: 60s
Total peticiones: 900
```
Se usa un puerto directo al `Service`. Chaos-monkey se ejecuta en un pod que mata pods aleatoriamente cada 30 segundos.
```bash
artillery quick -d 60 -r 15 http://192.168.64.2:32517
```
```text
Summary report @ 19:56:53(+0200) 2020-03-30
  Scenarios launched:  900
  Scenarios completed: 533
  Requests completed:  533
  Mean response/sec: 14.9
  Response time (msec):
    min: 5.2
    max: 722.5
    median: 37.5
    p95: 161.1
    p99: 496.6
  Scenario counts:
    0: 900 (100%)
  Codes:
    200: 533
  Errors:
    ECONNREFUSED: 367
```

Porcentaje fallos: ~40%

### Escenario 2

```text
RPS: 15
Duración: 60s
Total peticiones: 900
```
Se usa el puerto 80 directo al **Gateway de Istio**. Chaos-monkey se ejecuta en un pod que mata pods aleatoriamente cada 30 segundos.


```bash
artillery quick -d 60 -r 15 http://192.168.64.2
```

```text
All virtual users finished
Summary report @ 20:00:10(+0200) 2020-03-30
  Scenarios launched:  900
  Scenarios completed: 900
  Requests completed:  900
  Mean response/sec: 11.12
  Response time (msec):
    min: 6.2
    max: 30838.6
    median: 1780.7
    p95: 28471.1
    p99: 30316.8
  Scenario counts:
    0: 900 (100%)
  Codes:
    200: 900
```

Porcentaje fallos: 0%

## Enunciado

El objetivo es crear una aplicación escalable y tolerante a fallos en Kubernetes. Partiendo
de la aplicación ejem4-webapp-stateless (con 2 replicas), se deberá modificar de la
siguiente forma:

* Se usará Hazelcast en vez de MySQL para mantener la sesión.
* Pruebas de caos:
  * Escenario 1:
    * Ejecutar chaos-pod-monkey.
    * Ejecutar pruebas de “uso” con JMeter o con Artillery y medir el % de
fallos.
  * Escenarios 2:
    * Se configurará el Gateway de Istio para aceptar peticiones.
    * Se ofrecerá un valor por defecto al usuario en caso de que no haya ningún pod disponible del servicio (circuit-breaker).
    * Ejecutar pruebas de “uso” con JMeter o con Artillery y medir el % de
fallos.

Se deberá entregar un vídeo demostrativo de ejecución de pruebas de carga con y sin istio.
