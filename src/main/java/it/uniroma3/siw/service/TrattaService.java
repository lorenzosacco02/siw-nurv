package it.uniroma3.siw.service;

import it.uniroma3.siw.model.Tratta;
import it.uniroma3.siw.repository.TrattaRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Iterator;
import java.util.List;

@Service
public class TrattaService{

    @Autowired
    private TrattaRepository trattaRepository;

    @Transactional
    public Iterable<Tratta> getAll(){
        return this.trattaRepository.findAll();
    }

    @Transactional
    public Tratta getById(Long id){
        return this.trattaRepository.findById(id).orElse(null);
    }

    @Transactional
    public Tratta save(Tratta tratta){
        return this.trattaRepository.save(tratta);
    }

    public void delete(Long trattaId) {
        this.trattaRepository.deleteById(trattaId);
    }
}

