package es.urjc.code;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

import javax.transaction.Transactional;

/**
 * Repositorio para técnicos.
 *
 *  @author J. Manuel Colmenar
 */
public interface TecnicoRepository extends JpaRepository<Tecnico, Long> {

    @Query("select t from Tecnico t where t.nivel < ?1 and t.id not in (select ch.tecnico from Chat ch)")
    List<Tecnico> findTecnicosSinChatNivelMenor(int nivel);

    @Query("select t from Tecnico t where FUNCTION('JSON_CONTAINS',t.habilidades,CONCAT('\"',?1,'\"'),'$') = 1")
    List<Tecnico> findByHabilidad(String habilidad);

    @Modifying
    @Transactional
    @Query(value = "UPDATE tecnico SET HABILIDADES=JSON_REMOVE(habilidades,JSON_UNQUOTE(JSON_SEARCH(habilidades,'one',?1))) WHERE JSON_SEARCH(habilidades,'one',?1) IS NOT NULL",
    nativeQuery = true)
    void borrarHabilidad(String habilidad);

}
