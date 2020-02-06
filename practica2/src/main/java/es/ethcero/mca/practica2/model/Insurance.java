
package es.ethcero.mca.practica2.model;

import java.util.ArrayList;
import java.util.List;

import javax.persistence.ElementCollection;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

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

    @ElementCollection(fetch = FetchType.EAGER)
    private List<Coverage> coverages = new ArrayList<>();

    public void addCoverage(Coverage coverage) {
        if(!coverages.contains(coverage)) {
            this.coverages.add(coverage);
        }
    }

    public boolean isCovered(Coverage coverage) {
        return coverages.contains(coverage);
    }
}
