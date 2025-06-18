package it.uniroma3.siw.controller;

import it.uniroma3.siw.model.Video;
import it.uniroma3.siw.repository.VideoRepository;
import it.uniroma3.siw.service.VideoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

@Controller
public class VideoController {

    @Autowired
    private VideoService videoService;

    @GetMapping("/video/{id}/stream")
    public ResponseEntity<byte[]> streamVideo(@PathVariable Long id) {
        Video video = videoService.getById(id); // recupera il video dal DB

        if (video.getFile() == null || video.getFile().length == 0) {
            return ResponseEntity.notFound().build();
        }

        HttpHeaders headers = new HttpHeaders();
        headers.setContentType(MediaType.valueOf("video/mp4"));
        headers.setContentLength(video.getFile().length);

        return new ResponseEntity<>(video.getFile(), headers, HttpStatus.OK);
    }

}
