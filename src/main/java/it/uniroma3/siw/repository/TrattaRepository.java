package it.uniroma3.siw.repository;

import java.util.List;

import org.springframework.data.repository.CrudRepository;

import it.uniroma3.siw.model.Tratta;

public interface TrattaRepository extends CrudRepository<Tratta, Long> {
    List<Tratta> findByNomeContainingIgnoreCase(String nome);
}
