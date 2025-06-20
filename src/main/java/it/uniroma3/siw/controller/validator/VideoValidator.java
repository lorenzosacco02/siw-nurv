package it.uniroma3.siw.controller.validator;


import it.uniroma3.siw.model.Credentials;
import it.uniroma3.siw.model.Video;
import it.uniroma3.siw.service.CredentialsService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.validation.Errors;
import org.springframework.validation.Validator;

import java.time.LocalDate;

@Component
public class VideoValidator implements Validator {
    @Override
    public boolean supports(Class<?> clazz) {
        return Credentials.class.equals(clazz);
    }

    @Override
    public void validate(Object target, Errors errors) {
        Video video = (Video) target;
        if(video.getData().isBefore(LocalDate.now())) {
            errors.rejectValue("data", "Data.old");
        }
    }
}

