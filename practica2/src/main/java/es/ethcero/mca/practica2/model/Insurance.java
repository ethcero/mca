
package es.ethcero.mca.practica2.model;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

/**
 * @author fran
 */
@Entity
@NoArgsConstructor
@Getter
@Setter
public class Insurance {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private long id;

    @Embedded
    private Address address;

    @ElementCollection(fetch = FetchType.EAGER)
    private List<Coverage> coverages = new ArrayList<>();

    public Insurance(Address address) {
        this.address = address;
    }

    public void addCoverage(Coverage coverage) {
        if(!coverages.contains(coverage)) {
            this.coverages.add(coverage);
        }
    }

    public void checkCovered(Issue issue) {
       issue.checkCovered(coverages);
    }
}
