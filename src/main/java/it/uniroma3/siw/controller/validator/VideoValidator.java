package it.uniroma3.siw.controller.validator;


import it.uniroma3.siw.model.Credentials;
import it.uniroma3.siw.model.Video;
import it.uniroma3.siw.service.CredentialsService;
import it.uniroma3.siw.service.VideoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.validation.Errors;
import org.springframework.validation.Validator;
import org.springframework.web.multipart.MultipartFile;

import java.time.LocalDate;

import static it.uniroma3.siw.model.Video.MAX_SIZE;

@Component
public class VideoValidator implements Validator {
    private final VideoService videoService;

    public VideoValidator(VideoService videoService) {
        this.videoService = videoService;
    }

    @Override
    public boolean supports(Class<?> clazz) {
        return Credentials.class.equals(clazz);
    }

    @Override
    public void validate(Object target, Errors errors) {
        Video video = (Video) target;
        if(video.getNome() !=null && !video.getNome().isEmpty() &&video.getTratta()!=null && videoService.getByNomeInTratta(video.getNome(), video.getTratta()) != null) {
            errors.reject("Video.exists");
        }

        if(video.getFile() == null){
            errors.rejectValue("file", "Video.blank");
        }

        if (video.getFile() != null && video.getFile().length > MAX_SIZE) {
            errors.rejectValue("file", "Video.length");
        }
    }
}

