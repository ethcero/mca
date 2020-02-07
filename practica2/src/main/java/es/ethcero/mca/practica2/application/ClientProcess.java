
package es.ethcero.mca.practica2.application;

import es.ethcero.mca.practica2.model.Address;
import es.ethcero.mca.practica2.model.Client;
import es.ethcero.mca.practica2.model.Coverage;
import es.ethcero.mca.practica2.model.exception.ClientNotFoundException;
import es.ethcero.mca.practica2.repository.ClientRepository;
import es.ethcero.mca.practica2.repository.InsuranceRepository;
import es.ethcero.mca.practica2.repository.IssueRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.List;

/**
 * @author fran
 */
@Service
public class ClientProcess {

    @Autowired
    private ClientRepository clientRepository;
    @Autowired
    private InsuranceRepository insuranceRepository;
    @Autowired
    private IssueRepository issueRepository;

    @Transactional
    public void addClient(String name, Address address) {
       clientRepository.save(new Client(name, address));
    }

    @Transactional
    public void addIssue(long clientId, long insuranceId, Double amount, Coverage coverage) {
        Client client = clientRepository.findById(clientId)
                .orElseThrow(ClientNotFoundException::new);

        issueRepository.save(client.newIssue(insuranceId, amount, coverage));
    }

    @Transactional
    public void addInsurance(long clientId, Address address,  List<Coverage> coverages) {
        Client client = clientRepository.findById(clientId)
                .orElseThrow(ClientNotFoundException::new);

        insuranceRepository.save(client.newInsurance(address, coverages));
    }


}
