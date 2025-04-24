package it.uniroma3.siw.repository;

import java.util.Optional;

import org.springframework.data.repository.CrudRepository;

import it.uniroma3.siw.model.Mappa;

public interface MappaRepository extends CrudRepository<Mappa, Long>{
	 Optional<Mappa> findFirstBy();
}
