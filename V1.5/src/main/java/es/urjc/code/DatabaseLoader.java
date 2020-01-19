package es.urjc.code;

import java.util.List;

import es.urjc.code.entities.Chat;
import es.urjc.code.entities.Product;
import es.urjc.code.entities.Technician;
import es.urjc.code.repositories.ChatRepository;
import es.urjc.code.repositories.ClientRepository;
import es.urjc.code.repositories.ProductRepository;
import es.urjc.code.repositories.TechnicianRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Controller;

/**
 * Cargador de la BD y ejemplos de consulta.
 *
 * @author J. Manuel Colmenar
 */
@Controller
public class DatabaseLoader implements CommandLineRunner {

    @Autowired
    private ProductRepository productRepository;
    @Autowired
    private ClientRepository clientRepository;
    @Autowired
    private TechnicianRepository technicianRepository;
    @Autowired
    private ChatRepository chatRepository;

    @Override
    public void run(String... args) {

        this.V1Queries();
        this.V1_5Queries();

    }

    private void V1Queries() {
        System.out.println("#########################################");
        System.out.println("V1");
        // Listado de chats para un cliente concreto
        List<Chat> chats = chatRepository.findByClientEmail("fulanito@f.com");
        System.out.println("Listado de chats para un cliente concreto:");
        System.out.println("----------------------------------------");
        showData(chats);

        //Técnicos de nivel menor que 5 que no hayan atendido a ningún cliente.
        List<Technician> technicians = technicianRepository.findByNotAttendedAndLevelLessThan(5);
        System.out.println("Técnicos de nivel menor que 5 que no hayan atendido a ningún cliente:");
        System.out.println("----------------------------------------");
        showData(technicians);
        System.out.println("#########################################");
    }

    private void V1_5Queries() {
        System.out.println("#########################################");
        System.out.println("V1.5");
        // Productos que alguna vez hayan tenido un precio menor que 10 euros
        List<Product> products = productRepository.findByHistoricPriceLessThan(10D);
        System.out.println("Productos que alguna vez hayan tenido un precio menor que 10 euros:");
        System.out.println("----------------------------------------");
        showData(products);
        System.out.println("#########################################");
    }

    private static void showData(List datos) {
        for (Object p : datos) {
            System.out.println(p);
        }
        System.out.println();
    }


}
