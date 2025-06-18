package it.uniroma3.siw.controller;

import it.uniroma3.siw.model.Anomalia;
import it.uniroma3.siw.model.TipoDiAnomalia;
import it.uniroma3.siw.model.Video;
import it.uniroma3.siw.repository.VideoRepository;
import it.uniroma3.siw.service.AnomaliaService;
import it.uniroma3.siw.service.UserService;
import it.uniroma3.siw.service.VideoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

@Controller
public class AnomaliaController {

    @Autowired
    private VideoService videoService;

    @Autowired
    private AnomaliaService anomaliaService;
    @Autowired
    private VideoRepository videoRepository;
    @Autowired
    private UserService userService;

    @GetMapping("/video/{videoId}/addAnomalia")
    public String aggiungiAnomalia(@PathVariable Long videoId, Model model) {
        model.addAttribute("anomalia", new Anomalia());
        model.addAttribute("video_id", videoId);
        return "user/formNewAnomalia";
    }

    @PostMapping("/video/{videoId}/addAnomalia")
    public String salvaAnomalia(@PathVariable Long videoId,
                                   @RequestParam TipoDiAnomalia tipoAnomalia,
                                   @RequestParam int gravita,
                                   @RequestParam String descrizione) {

        Video video = videoService.getById(videoId);
        if (video == null) {
            return "redirect:/error"; // o pagina custom di errore
        }

        Anomalia anomalia = new Anomalia();
        anomalia.setTipoAnomalia(tipoAnomalia);
        anomalia.setGravita(gravita);
        anomalia.setDescrizione(descrizione);
        anomalia.setVideo(video);
        anomalia.setUser(userService.getCurrentUser());

        anomaliaService.save(anomalia);

        return "redirect:/tratta/" + video.getTratta().getId();
    }

}
