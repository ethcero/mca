package es.urjc.code;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Controller;

import java.text.ParseException;
import java.util.List;

/**
 * Cargador de la BD y solución de apartados de la práctica.
 *
 * @author J. Manuel Colmenar
 */
@Controller
public class DatabaseLoader implements CommandLineRunner {

    @Autowired
    private ClienteRepository clienteRepository;

    @Autowired
    private ChatRepository chatRepository;

    @Autowired
    private TecnicoRepository tecnicoRepository;

    @Autowired
    private ProductoRepository productoRepository;



    @Override
    public void run(String... args) throws ParseException {

        System.out.println("\n-- Listado del correcto almacenamiento de la info --------\n");
        System.out.println("----- Listado de productos comprados por: pedro.garcia@email.com");
        Cliente c = clienteRepository.findByEmail("pedro.garcia@email.com");
        c.getProductos().forEach(p -> System.out.println(p.getProducto()));
        System.out.println("-----------------------------------------\n");

        System.out.println("\n-- Ciudad cuyos clientes han realizado más compras -------\n");
        System.out.println(clienteRepository.ciudadClientesMasCompras());
        System.out.println("-----------------------------------------\n");

        muestraDatos("Para cada cliente, mostrar su nombre, apellidos y el número de servicios que ha\n" + "comprado",
                clienteRepository.totalServiciosPorCliente());

    }

    private static void muestraDatos(String titulo, List datos) {
        System.out.println(titulo);
        System.out.println("-----------------------------------------");
        for (Object p : datos) {
            System.out.println(p);
        }
        System.out.println();

    }


}
