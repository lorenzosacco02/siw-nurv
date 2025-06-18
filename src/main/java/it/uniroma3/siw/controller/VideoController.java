package it.uniroma3.siw.controller;

import it.uniroma3.siw.model.Tratta;
import it.uniroma3.siw.model.User;
import it.uniroma3.siw.model.Video;
import it.uniroma3.siw.repository.VideoRepository;
import it.uniroma3.siw.service.TrattaService;
import it.uniroma3.siw.service.UserService;
import it.uniroma3.siw.service.VideoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
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
        model.addAttribute("user", userService.getCurrentUser());
        model.addAttribute("tratta", tratta_id);
        return "user/admin/formNewVideo";
    }

    @PostMapping("/admin/addVideo/{tratta_id}")
    public String uploadVideo(@RequestParam("file") MultipartFile file,
                              @RequestParam("nome") String nome,
                              @RequestParam("data") LocalDate data,
                              @PathVariable Long tratta_id,
                              RedirectAttributes redirectAttributes,
                              Model model) throws IOException {

        User utente = userService.getCurrentUser(); // o usa principal.getName()
        Tratta tratta = trattaService.getById(tratta_id);


        if (file.isEmpty() || tratta == null) {
            return "redirect:/admin/addVideo/" + tratta_id; // oppure mostra messaggio più chiaro
        }

        if (file.getSize() > MAX_SIZE) {
            redirectAttributes.addFlashAttribute("uploadError", "Il file supera la dimensione massima consentita di 50MB.");
            return "redirect:/admin/addVideo/" + tratta_id;
        }


        Video video = new Video();

        video.setData(data);
        video.setNome(nome);
        video.setFile(file.getBytes());
        video.setTratta(tratta);
        video.setUser(utente); // se Video ha un campo 'utente'

        videoService.save(video);

        model.addAttribute("user", utente);
        return "redirect:/tratta/" + tratta_id; // oppure /video/success
    }

}
