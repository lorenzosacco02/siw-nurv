package it.uniroma3.siw.controller.validator;

import it.uniroma3.siw.model.Credentials;
import it.uniroma3.siw.model.Tratta;
import it.uniroma3.siw.service.CredentialsService;
import it.uniroma3.siw.service.TrattaService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.validation.Errors;
import org.springframework.validation.Validator;

@Component
public class TrattaValidator  implements Validator{

    @Autowired
    private CredentialsService credentialsService;
    @Autowired
    private TrattaService trattaService;

    @Override
    public boolean supports(Class<?> clazz) {
        return Tratta.class.equals(clazz);
    }

    @Override
    public void validate(Object target, Errors errors) {
        Tratta  tratta = (Tratta) target;
        if(tratta.getNome() != null && trattaService.getByName(tratta.getNome()) != null) {
            errors.reject("Tratta.exists");
        }
        if(tratta.getDescrizione() != null && tratta.getDescrizione().length() > 100) {
            errors.rejectValue("descrizione", "Tratta.length");
        }
    }
}
