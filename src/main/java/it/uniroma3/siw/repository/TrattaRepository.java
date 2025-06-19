package it.uniroma3.siw.repository;

import java.util.List;

import it.uniroma3.siw.model.TipoDiAnomalia;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;

import it.uniroma3.siw.model.Tratta;
import org.springframework.data.repository.query.Param;

public interface TrattaRepository extends CrudRepository<Tratta, Long> {
    List<Tratta> findByNomeContainingIgnoreCase(String nome);

    @Query(value = """
            SELECT DISTINCT t.* 
            FROM tratta t
            WHERE 
                (:nome IS NULL OR LOWER(t.nome) LIKE LOWER(CONCAT(:nome, '%')))
                AND (
                    :anomalia IS NULL OR EXISTS (
                        SELECT 1
                        FROM video v
                        JOIN anomalia a ON a.video_id = v.id
                        WHERE v.tratta_id = t.id 
                        AND a.tipo_anomalia = :anomalia
                        AND a.risolta = false
                    )
                )
                ORDER BY t.nome ASC
            """, nativeQuery = true)
    Iterable<Tratta> findByCriteria(@Param("nome") String nome, @Param("anomalia") String anomalia);

    List<Tratta> findByNomeOrederdById(String nome);

}
