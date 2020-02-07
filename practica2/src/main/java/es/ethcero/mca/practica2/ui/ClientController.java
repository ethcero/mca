
package es.ethcero.mca.practica2.ui;

import es.ethcero.mca.practica2.application.ClientProcess;
import es.ethcero.mca.practica2.model.Client;
import es.ethcero.mca.practica2.model.Issue;
import es.ethcero.mca.practica2.repository.ClientRepository;
import es.ethcero.mca.practica2.repository.IssueRepository;
import es.ethcero.mca.practica2.ui.command.NewClientCommand;
import es.ethcero.mca.practica2.ui.command.NewInsuranceCommand;
import es.ethcero.mca.practica2.ui.command.NewIssueCommand;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * @author fran
 */
@RestController
@RequestMapping("/client")
public class ClientController {


    @Autowired
    private IssueRepository issueRepository;
    @Autowired
    private ClientRepository clientRepository;
    @Autowired
    private ClientProcess clientProcess;


    @PostMapping("")
    ResponseEntity newClient(@RequestBody NewClientCommand newClientCommand) {
        clientProcess.addClient(newClientCommand.getName(),newClientCommand.getAddress());
        return ResponseEntity.ok().build();
    }

    @PostMapping("/{clientId}/insurance")
    ResponseEntity newInsurance(@PathVariable long clientId, @RequestBody NewInsuranceCommand newInsuranceCommand) {
        clientProcess.addInsurance(clientId, newInsuranceCommand.getAddress(), newInsuranceCommand.getCoverages());
        return ResponseEntity.ok().build();
    }

    @PostMapping("/{clientId}/issue")
    ResponseEntity newIssue(@PathVariable long clientId, @RequestBody NewIssueCommand newIssueCommand) {
        clientProcess.addIssue(clientId, newIssueCommand.getInsuranceId(), newIssueCommand.getAmount(), newIssueCommand.getCoverage());
        return ResponseEntity.ok().build();
    }

    @GetMapping("/{clientId}/issue")
    ResponseEntity<List<Issue>> issues(@PathVariable long clientId) {
        return ResponseEntity.ok().body(issueRepository.findByClientId(clientId));
    }

    @GetMapping("")
    ResponseEntity<List<Client>> clients() {
        return ResponseEntity.ok().body(clientRepository.findAll());
    }
}
