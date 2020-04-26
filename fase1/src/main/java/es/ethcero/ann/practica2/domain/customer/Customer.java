
package es.ethcero.ann.practica2.domain.customer;

import javax.persistence.Embedded;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

import es.ethcero.ann.practica2.domain.common.Money;
import lombok.Getter;
import lombok.NoArgsConstructor;

/**
 * @author fran
 */
@Entity
@Getter
@NoArgsConstructor
@Table(name="customers")
public class Customer {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String name;

    @Embedded
    private Money credit;

    public Customer(String name, Money credit) {
        this.name = name;
        this.credit = credit;
    }

    public void addCredit(Money ammount) {
        this.credit.add(ammount);
    }

    public void reserveCredit(Money orderTotal) {
        if(credit.isGreaterThanOrEqual(orderTotal)) {
           credit.subtract(orderTotal);
        } else {
            throw new InsufficientCreditException();
        }
    }
}
