
package es.urjc.code;

/**
 * @author fran
 */
public class ClienteTotalDTO {

    private String nombre;
    private String apellidos;
    private long total;

    public ClienteTotalDTO(String nombre, String apellidos, long total) {
        this.nombre = nombre;
        this.apellidos = apellidos;
        this.total = total;
    }

    @Override
    public String toString() {
        return "ClienteTotalDTO{" + "nombre='" + nombre + '\'' + ", apellidos='" + apellidos + '\'' + ", total=" + total + '}';
    }
}
