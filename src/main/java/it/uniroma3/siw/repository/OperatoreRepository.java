package it.uniroma3.siw.repository;

import java.util.List;

import org.springframework.data.repository.CrudRepository;

import it.uniroma3.siw.model.Operatore;

public interface OperatoreRepository extends CrudRepository<Operatore, Long> {
    List<Operatore> findByNome(String nome);
}
