
package es.ethcero.mca.practica2.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import es.ethcero.mca.practica2.model.Insurance;

/**
 * @author fran
 */

public interface InsuranceRepository extends JpaRepository<Insurance, Long> {

}
