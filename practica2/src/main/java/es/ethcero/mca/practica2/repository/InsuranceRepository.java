
package es.ethcero.mca.practica2.repository;

import es.ethcero.mca.practica2.model.Insurance;
import org.springframework.data.jpa.repository.JpaRepository;

/**
 * @author fran
 */

public interface InsuranceRepository extends JpaRepository<Insurance, Long> {

}
