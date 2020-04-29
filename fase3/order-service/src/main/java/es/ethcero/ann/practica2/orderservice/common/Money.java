
package es.ethcero.ann.practica2.orderservice.common;

import org.apache.commons.lang.builder.HashCodeBuilder;
import org.apache.commons.lang.builder.ToStringBuilder;
import org.hamcrest.Matchers;

import java.math.BigDecimal;

import javax.persistence.Access;
import javax.persistence.AccessType;
import javax.persistence.Embeddable;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

/**
 * @author fran
 */

@Embeddable
@Access(AccessType.FIELD)
@AllArgsConstructor
@NoArgsConstructor
@Getter
public class Money {

    public static final Money ZERO = new Money(0);
    private BigDecimal amount;

    public Money(int amount) {
        this.amount = new BigDecimal(amount);
    }

    public Money(String amount) {
        this.amount = new BigDecimal(amount);
    }

    public boolean isGreaterThanOrEqual(Money other) {
        return amount.compareTo(other.amount) >= 0;
    }

    public void add(Money other) {
        amount = amount.add(other.amount);
    }

    public void subtract(Money other) {
        amount = amount.subtract(other.amount);
    }

    @Override
    public String toString() {
        return ToStringBuilder.reflectionToString(this);
    }

    @Override
    public boolean equals(Object obj) {
        if(obj instanceof Money) {
            return Matchers.comparesEqualTo(amount).matches(((Money) obj).getAmount());
        }

        return false;
    }

    @Override
    public int hashCode() {
        return HashCodeBuilder.reflectionHashCode(this);
    }
}
