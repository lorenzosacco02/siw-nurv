package it.uniroma3.siw.service;

import it.uniroma3.siw.model.Anomalia;
import it.uniroma3.siw.repository.AnomaliaRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class AnomaliaService {
    @Autowired
    AnomaliaRepository anomaliaRepository;

    public void save(Anomalia anomalia) {
        anomaliaRepository.save(anomalia);
    }

}
