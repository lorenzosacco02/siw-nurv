package it.uniroma3.siw.controller;

import it.uniroma3.siw.controller.validator.VideoValidator;
import it.uniroma3.siw.model.Tratta;
import it.uniroma3.siw.model.User;
import it.uniroma3.siw.model.Video;
import it.uniroma3.siw.repository.VideoRepository;
import it.uniroma3.siw.service.TrattaService;
import it.uniroma3.siw.service.UserService;
import it.uniroma3.siw.service.VideoService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import java.io.IOException;
import java.security.Principal;
import java.time.LocalDate;
import java.time.LocalDateTime;

import static it.uniroma3.siw.model.Video.MAX_SIZE;

@Controller
public class VideoController {

    @Autowired
    private VideoService videoService;
    @Autowired
    private UserService userService;
    @Autowired
    private TrattaService trattaService;
    @Autowired
    private VideoValidator videoValidator;

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

    @GetMapping("/admin/addVideo")
    public String addVideo(Model model) {
        model.addAttribute("user", userService.getCurrentUser());
        model.addAttribute("tratte" , trattaService.getAll());
        return "user/admin/selectTratta";
    }

    @GetMapping("/admin/addVideo/{tratta_id}")
    public String addVideoTratta(@PathVariable Long tratta_id, Model model) {
        model.addAttribute("video", new Video());
        model.addAttribute("user", userService.getCurrentUser());
        model.addAttribute("tratta", tratta_id);
        return "user/admin/formNewVideo";
    }

    @PostMapping("/admin/addVideo/{tratta_id}")
    public String uploadVideo(@Valid @ModelAttribute Video video,
                              BindingResult bindingResult,
                              @PathVariable Long tratta_id,
                              Model model) throws IOException {

        MultipartFile file = video.getMultipartFile();

        if (file == null || file.isEmpty()) {
            bindingResult.rejectValue("multipartFile", "Video.blank", "Devi caricare un file.");
        } else if (file.getSize() > Video.MAX_SIZE) {
            bindingResult.rejectValue("multipartFile", "Video.tooLarge", "Il file supera il limite di 50MB.");
        } else {
            video.setFile(file.getBytes());
        }

        if (bindingResult.hasErrors()) {
            model.addAttribute("user", userService.getCurrentUser());
            model.addAttribute("video", video);
            model.addAttribute("tratta", tratta_id);
            return "user/admin/formNewVideo";
        }

        video.setTratta(trattaService.getById(tratta_id));
        video.setUser(userService.getCurrentUser());

        videoService.save(video);

        return "redirect:/tratta/" + tratta_id;
    }



}
