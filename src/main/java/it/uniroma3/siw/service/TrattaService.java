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

    public Iterable<Tratta> getAll(){
        return this.trattaRepository.findAll();
    }
}

