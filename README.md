# Master Cloud Apps

## Computacion en la nube (Practica 1)

### Creación de AMI

Se crea un AMI de AWS usando Packer. 

La configuración de Packer parte de una imagen Debian-9 limpia donde se provisionan ficheros de configuración para Systemd y el jar de la aplicación.

Se define un script de provision que se ejecutará en la máquina remota en tiempo de empaquetado que instala las dependencias de JAVA y coloca los archivos provisionados en el lugar
 correcto para poder ser ejecutados por systemd.

### Build
 El script `build.sh` se encarga de compilar la aplicación JAVA con Maven y mover el artefacto generado al directorio de Packer para que sea provisionado a la imagen.
 
 
 
### Enunciado

Aplicación básica (6 pts)

Un cliente acude a CodeURJC solicitando que desarrollemos una API REST que permita
manejar los recursos de S3 de manera sencilla, ya que están descontentos con la que usan
actualmente. Sus aplicaciones ya usan una interfaz de API definida, por lo que debemos
ceñirnos a ella:

- `GET /api/buckets/`
Recupera todos los buckets de nuestra cuenta.
- `GET /api/buckets/<bucket_name>/objects`
Recupera un bucket concreto dado su nombre
- `POST /api/buckets/<bucket_name>`
Crea un nuevo bucket vacío.
- `POST /api/buckets/<bucket_name>/uploadObject`
Sube un nuevo objeto a un bucket.
- `DELETE /api/buckets/<bucket_name>`
Borra un bucket (solo si está vacío)
- `DELETE /api/buckets/<bucket_name>/<object_name>`
Borra un objeto de un bucket

Nos proporciona además un fichero de Postman que utilizan para probar dicha API.
Además, se desea que esta aplicación sea desplegada en una instancia EC2 de forma que
la API sea accesible y el cliente pueda probarla. El alumno entregará pruebas de que la
aplicación ha estado disponible en forma de Video/GIF dónde se vea la instancia creada en
el panel de EC2, así como que está disponible a través de su DNS público.

Dada nuestra extensa experiencia en la tecnología Java, hemos decidido que usaremos
SpringBoot junto al SDK de AWS. Dado que la aplicación estará públicamente expuesta, y
permite crear recursos en S3, debe estar securizada:
- Es necesario que la aplicación se despliegue en el puerto 443 (puerto HTTPS por
defecto).
- Puede utilizarse un usuario en memoria, no es necesario utilizar base de datos.
Estos requisitos se consideran mínimos para que la práctica se pueda considerar aprobada.
Además, se incluyen a continuación algunos requisitos adicionales que se valorarán en la
nota.

Funcionalidad adicional (1 pto)

Aparte de los métodos descritos, el cliente quiere extender la API de la siguiente manera:
- Permitir que en la subida de objetos a un bucket se pueda definir si el objeto es
público o privado.
- Implementar un nuevo método en el controlador que permita copiar archivos de un
bucket a otro

Utilización de security group propio para la instancia (1 pto)

Se valorará el uso de un security group específico para la instancia. Se mostrará en el vídeo
y este security group deberá filtrar los accesos entrantes de forma que no se permita
acceder a la aplicación salvo a IPs del rango de la empresa (193.147.*.*).
NOTA: Este rango de direcciones IP es el de la URJC, si estáis fuera de la red de la
universidad, podeis abrir un browser (e incluso una máquina virtual) de forma gratuita en el
portal MyApps de la URJC: https://myapps.urjc.es/myapps. Todas las aplicaciones del
MyApps funcionan en la red interna de la universidad bajo el mismo rango de IPv4.

Creación de una AMI con la aplicación (2 pts)

Se valorará la creación de una AMI con la aplicación. Se obtendrá el máximo de puntos si al
arrancar una instancia a partir de esta AMI la aplicación arranca automáticamente (los
pasos seguidos se definirán en un archivo README.md dentro del proyecto. Se incluirá en
el vídeo la AMI creada y cómo se arranca la instancia a partir de ella.
Una vez grabado el video de que la aplicación funciona, se detendrá y borrará la instancia
que contiene la aplicación para evitar costes adicionales, así como la AMI y SNAPSHOTS
creadas 