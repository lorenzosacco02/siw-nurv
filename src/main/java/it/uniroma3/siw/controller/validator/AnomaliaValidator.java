package it.uniroma3.siw.controller.validator;

import it.uniroma3.siw.model.Anomalia;
import it.uniroma3.siw.model.Tratta;
import it.uniroma3.siw.service.AnomaliaService;
import it.uniroma3.siw.service.CredentialsService;
import it.uniroma3.siw.service.TrattaService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.validation.Errors;
import org.springframework.validation.Validator;

@Component
public class AnomaliaValidator implements Validator{

    @Autowired
    private AnomaliaService anomaliaService;

    @Override
    public boolean supports(Class<?> clazz) {
        return Anomalia.class.equals(clazz);
    }

    @Override
    public void validate(Object target, Errors errors) {
        Anomalia anomalia = (Anomalia) target;
        if(anomalia.getTipoAnomalia() == null) {
            errors.rejectValue("tipoAnomalia", "Anomalia.type.blank");
        }
    }
}
