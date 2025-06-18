package it.uniroma3.siw.controller;

import it.uniroma3.siw.service.TrattaService;
import it.uniroma3.siw.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

@Controller
public class TrattaController {

        @Autowired
        private TrattaService trattaService;

        @Autowired
        private UserService userService;

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
}


