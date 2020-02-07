
package es.ethcero.mca.practica2.model;

import es.ethcero.mca.practica2.model.exception.InsuranceNotFoundException;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;
import java.util.HashSet;
import java.util.List;
import java.util.Set;


@Entity
@NoArgsConstructor
@Getter
@Setter
public class Client {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private long id;

    private String name;

    @Embedded
    private Address address;

    @ElementCollection(targetClass = Insurance.class)
    @CollectionTable(name = "insurances", joinColumns = @JoinColumn(name = "client_id"))
    private Set<Insurance> insurances = new HashSet<>();

    @ElementCollection(targetClass = Issue.class)
    @CollectionTable(name = "issues", joinColumns = @JoinColumn(name = "client_id"))
    private Set<Issue> issues = new HashSet<>();

    public Client(String name, Address address) {
        this.name = name;
        this.address = address;
    }

    public Insurance newInsurance(Address address, List<Coverage> coverages) {
        Insurance insurance = new Insurance(address);
        coverages.forEach(insurance::addCoverage);
        this.insurances.add(insurance);
        return insurance;
    }

    public Issue newIssue(long insuranceId, Double amount, Coverage coverage) {

        Issue issue = new Issue(amount, coverage);

        if (couldBeFraud()) {
            issue.setFraud();
        }

        if (!findInsurance(insuranceId).isCovered(coverage)) {
            issue.notCovered();
        }

        this.issues.add(issue);

        return issue;
    }

    private boolean couldBeFraud() {
        return (issues.size() >= 2 && sumIssuesAmount() > 1000);
    }

    private Double sumIssuesAmount() {
        return issues.stream()
                .filter(Issue::isCovered)
                .map(Issue::getAmount).reduce(0D, Double::sum);
    }

    private Insurance findInsurance(long insuranceId) {
        return insurances.stream().filter(insurance -> insuranceId == insurance.getId())
                .findFirst()
                .orElseThrow(InsuranceNotFoundException::new);
    }
}