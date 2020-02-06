
package es.ethcero.mca.practica2.application;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

import javax.transaction.Transactional;

import es.ethcero.mca.practica2.model.Client;
import es.ethcero.mca.practica2.model.Coverage;
import es.ethcero.mca.practica2.model.Insurance;
import es.ethcero.mca.practica2.model.exception.ClientNotFoundException;
import es.ethcero.mca.practica2.repository.ClientRepository;
import es.ethcero.mca.practica2.repository.InsuranceRepository;
import es.ethcero.mca.practica2.repository.IssueRepository;

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
    public void addClient(String name) {
       clientRepository.save(new Client(name));
    }

    @Transactional
    public void addIssue(long clientId, Double amount, Coverage coverage) {
        Client client = clientRepository.findById(clientId)
                .orElseThrow(() -> new ClientNotFoundException(clientId));

        issueRepository.save(client.addIssue(amount, coverage));
    }

    @Transactional
    public void addInsurance(long clientId, List<Coverage> coverages) {
        Client client = clientRepository.findById(clientId)
                .orElseThrow(() -> new ClientNotFoundException(clientId));

        Insurance insurance = new Insurance();
        coverages.forEach(insurance::addCoverage);
        insuranceRepository.save(insurance);

        client.setInsurance(insurance);
        clientRepository.save(client);
    }


}
