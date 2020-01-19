package es.urjc.code;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.List;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.node.ObjectNode;
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
    public void run(String... args) throws Exception {

        this.V1Queries();
        this.V1_5Queries();
        this.V2Queries();

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

        // Técnicos con la habilidad “Wearables”
        List<Technician> technicians = technicianRepository.FindByLabel("\"Wearables\"");
        System.out.println("Técnicos con la habilidad “Wearables”:");
        System.out.println("----------------------------------------");
        showData(technicians);

        // Productos que alguna vez hayan tenido un precio menor que 10 euros
        List<Product> products = productRepository.findByHistoricPriceLessThan(10D);
        System.out.println("Productos que alguna vez hayan tenido un precio menor que 10 euros:");
        System.out.println("----------------------------------------");
        showData(products);
        System.out.println("#########################################");
    }

    private void V2Queries() throws Exception {
        ObjectMapper mapper = new ObjectMapper();
        System.out.println("#########################################");
        System.out.println("V2");

        // Listado de todos los chats de una fecha dada
        List<Chat> chats = chatRepository.findByDate(new SimpleDateFormat("yyyy-MM-dd hh:mm:ss").parse("2020-01-19 10:33:16"));
        System.out.println("Listado de todos los chats de una fecha dada:");
        System.out.println("----------------------------------------");
        showData(chats);

        // Listado de chats sobre el producto de nombre “Home Pod”
        chats = chatRepository.findByProductName("Home Pod");
        System.out.println("Listado de chats sobre el producto de nombre “Home Pod”:");
        System.out.println("----------------------------------------");
        showData(chats);

        System.out.println("Añadir un valor al histórico de precios de un producto:");
        System.out.println("NUEVO DATO: {\"date\": \"19/01/2020\", \"price\": 12.0}");
        System.out.println("----------------------------------------");
        productRepository.pushPriceToHistoric("Home Pod", "19/01/2020", 12.0);
        Product p = productRepository.findByName("Home Pod");
        System.out.println(p);
        System.out.println();

        System.out.println("Borrar la habilidad “Video” de todos los técnicos con nivel mayor que 3");
        System.out.println("----------------------------------------");
        technicianRepository.deleteLabelWhenLevelMoreThan("Video", 3);
        showData(technicianRepository.findAll());

        System.out.println("Insertar una línea de conversación de chat para uno de los casos:");
        System.out.println("\"client\": 7, \"product\": 2, \"technician\": 12");
        System.out.println("Nueva linea: {\"date\": \"2020-01-20 9:01:00\", \"author\": \"T\", \"message\": \"Texto nuevo añadido\"}");
        System.out.println("----------------------------------------");

        chatRepository.addChatLine(7,12,2,"2020-01-20 9:01:00","T","Texto nuevo añadido");
        showData(chatRepository.findAll());
        System.out.println("#########################################");




    }

    private static void showData(List datos) {
        for (Object p : datos) {
            System.out.println(p);
        }
        System.out.println();
    }


}
