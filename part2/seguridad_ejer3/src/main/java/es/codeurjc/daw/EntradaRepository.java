package es.codeurjc.daw;

import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface EntradaRepository extends JpaRepository<Entrada, Long> {

    List<Entrada> findByName(String name);
}
