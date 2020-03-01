package es.ethcero.mca.worker.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import es.ethcero.mca.worker.models.Log;

public interface LogRepository extends JpaRepository<Log, Long> {

}