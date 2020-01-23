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
