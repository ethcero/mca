
package es.ethcero.mca.practica2.model;

import java.util.HashSet;
import java.util.Set;

import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.OneToOne;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;


@Entity
@NoArgsConstructor
@Getter
@Setter
public class Client {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private long id;

    private String name;

    @OneToOne(targetEntity = Insurance.class)
    private Insurance insurance;

    @OneToMany(mappedBy = "clientId", fetch = FetchType.LAZY)
    private Set<Issue> issues = new HashSet<>();

    public Client(String name) {
        this.name = name;
    }

    public void setInsurance(Insurance insurance){
        this.insurance = insurance;
    }

    public Issue addIssue(Double amount, Coverage coverage) {

        Issue issue = new Issue(id, amount, coverage);

        if (couldBeFraud()) {
            issue.setFraud();
        }

        if (!insurance.isCovered(coverage)) {
            issue.notCovered();
        }

        this.issues.add(issue);
        return issue;
    }

    private boolean couldBeFraud() {
        return (issues.size() >= 2 && sumIssuesAmount() > 1000);
    }

    private Double sumIssuesAmount() {
        return issues.stream().map(Issue::getAmount).reduce(0D, Double::sum);
    }

}