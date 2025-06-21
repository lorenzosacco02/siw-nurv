package it.uniroma3.siw.controller;

import it.uniroma3.siw.controller.validator.TrattaValidator;
import it.uniroma3.siw.model.Tratta;
import it.uniroma3.siw.service.TrattaService;
import it.uniroma3.siw.service.UserService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;

@Controller
public class TrattaController {

    @Autowired
    private TrattaService trattaService;

    @Autowired
    private UserService userService;
    @Autowired
    private TrattaValidator trattaValidator;

    @GetMapping("/tratta/{id}")
    public String dettaglioTratta(@PathVariable Long id,
                                  Model model) {
        model.addAttribute("user", userService.getCurrentUser());
        model.addAttribute("tratta", trattaService.getById(id));
        return "user/tratta";
    }

    @GetMapping("/tratte")
    public String tratte(Model model) {
        model.addAttribute("user", userService.getCurrentUser());
        model.addAttribute("tratte", trattaService.getAll());
        return "user/index";
    }

    @GetMapping("/admin/addTratta")
    public String addTratta(Model model) {
        model.addAttribute("user", userService.getCurrentUser());
        model.addAttribute("tratta", new Tratta());
        return "user/admin/formNewTratta";
    }

    @PostMapping("/admin/addTratta")
    public String saveTratta(@Valid @ModelAttribute Tratta tratta,
                             BindingResult bindingResult,
                             Model model) {
        trattaValidator.validate(tratta, bindingResult);
        if (bindingResult.hasErrors()) {
            model.addAttribute("user", userService.getCurrentUser());
            model.addAttribute("tratta", tratta);
            return "user/admin/formNewTratta";
        }
        model.addAttribute("user", userService.getCurrentUser());
        trattaService.save(tratta);
        return "redirect:/tratta/" + tratta.getId();
    }

    @GetMapping("/admin/deleteTratta/{tratta_id}")
    public String deleteTratta(@PathVariable Long tratta_id) {
        trattaService.delete(tratta_id);
        return "redirect:/tratte";
    }
}


