package it.uniroma3.siw.repository;

import java.util.List;

import org.springframework.data.repository.CrudRepository;

import it.uniroma3.siw.model.Anomalia;
import it.uniroma3.siw.model.TipoDiAnomalia;

public interface AnomaliaRepository extends CrudRepository<Anomalia, Long> {
    List<Anomalia> findByTipoAnomalia(TipoDiAnomalia tipo);
}
