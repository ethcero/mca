package es.urjc.code;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Random;

import es.urjc.code.entities.Chat;
import es.urjc.code.entities.Client;
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

        this.initDB();

        // Recupera products
        List<Product> products = productRepository.findAll();
        System.out.println("Productos con findAll():");
        System.out.println("----------------------------------------");
        showData(products);

    }


    private static void showData(List datos) {
        for (Object p : datos) {
            System.out.println(p);
        }
        System.out.println();
    }

    private void initDB() {

        if (productRepository.count() > 0)
            return;

        ArrayList<Product> products = new ArrayList<>();
        products.add(new Product("MacBook pro", "Apple",1000D ));
        products.add(new Product("Portatil HP", "HP",500D ));
        products.add(new Product("teclado", "Logitech",100D ));
        products.add(new Product("pantalla", "HP",100D ));
        products.add(new Product("raton", "Logitech",10D ));
        products.forEach(p -> productRepository.save(p));


        ArrayList<Client> clients = new ArrayList<>();
        clients.add(new Client("Juanito", "Detal", "juanito@f.com", "Madrid"));
        clients.add(new Client("Fulanito", "Detal", "fulanito@f.com", "Murcia"));
        clients.add(new Client("Jaimito", "Borromeo", "jaimito@f.com", "Lorca"));
        clients.add(new Client("Pepito", "Pascual", "pepito@f.com", "Alcorcon"));
        clients.forEach(c -> clientRepository.save(c));

        ArrayList<Technician> technicians = new ArrayList<>();
        technicians.add(new Technician("Jaime",1));
        technicians.add(new Technician("Jaime",3));
        technicians.add(new Technician("Pedro", 10));
        technicians.add(new Technician("Miguel", 2));
        technicians.forEach(t -> technicianRepository.save(t));

        products.remove(0);
        clients.remove(0);
        technicians.remove(0);

        Random rand = new Random();
        char[] author = {'C', 'T'};
        for (int i = 0; i < 20; i++) {
            Chat chat = new Chat(
                    new Date(),
                    author[rand.nextInt(author.length)],
                    clients.get(rand.nextInt(clients.size())),
                    technicians.get(rand.nextInt(technicians.size())),
                    products.get(rand.nextInt(products.size())),
                    "Message body text Nº:" + i);

            chatRepository.save(chat);
        }
    }

}
