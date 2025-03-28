package com.iticbcn.mywebapp.llibresapp.controllers;

import java.time.LocalDate;
import java.util.Optional;
import java.util.Set;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.support.SessionStatus;

import com.iticbcn.mywebapp.llibresapp.model.Llibre;
import com.iticbcn.mywebapp.llibresapp.services.BookService;

@Controller
public class BookController {

    private final BookService bookService;

    @Autowired
    public BookController(BookService bookService) {
        this.bookService = bookService;
    }

    @GetMapping("/")
    public String iniciar(Model model) {
        return "login";
    }


    @PostMapping("/index")
    public String login(@RequestParam("user") String user, @RequestParam("password") String password ,Model model) {
        if (user.equals("toni") && password.equals("h3ll0!!")) {
            return "index";
        } else {
            return "login";
        }        
    }

    @GetMapping("/index")
    public String index(Model model) {

            return "index";
        
    }

    @GetMapping("/consulta") 
    public String consulta(Model model) {

        Set<Llibre> llibres = bookService.findAll();

        model.addAttribute("llibres", llibres);
        
        return "consulta";
    }

    @GetMapping("/inserir") 
    public String inputInserir(Model model) {
        return "inserir";
    }
    
    @GetMapping("/cercaid")
    public String inputCerca(Model model) {
        Llibre llibre = new Llibre();
        llibre.setIdLlibre(0);
        model.addAttribute("llibreErr", true);
        model.addAttribute("message", "");
        model.addAttribute("llibre", llibre);

        return "cercaid";

    }

    @PostMapping("/inserir")
    public String inserir(@RequestParam(name = "titol") String titol,  
                          @RequestParam(name = "autor") String autor,
                          @RequestParam(name = "editorial") String editorial,  
                          @RequestParam(name = "datapublicacio") String datapublicacio,
                          @RequestParam(name = "tematica") String tematica,
                          @RequestParam(name = "isbn") String isbn,
                          Model model) {

        String message = "";
        boolean llibreErr = false;
        LocalDate dataPublicacio;
        try {
            dataPublicacio = LocalDate.parse(datapublicacio);
        } catch (Exception e) {
            message = "El format de la data de publicació és incorrecte.";
            llibreErr = true;
            model.addAttribute("message", message);
            model.addAttribute("llibreErr", llibreErr);
            return "inserir";
        }

        try {
            bookService.validateISBNFormat(isbn);
        } catch (Exception e) {
            message = "El format del isbn és incorrecte.";
            llibreErr = true;
            model.addAttribute("message", message);
            model.addAttribute("llibreErr", llibreErr);
            return "inserir";
        }
        
        Llibre llibre = new Llibre(0, titol, autor, isbn, editorial, dataPublicacio, tematica);
        bookService.save(llibre);
        return "inserir";
    }

    @PostMapping("/cercaid")
    public String cercaId(@RequestParam(name = "idLlibre", required = false) String idLlibre, 
                            Model model) {
        
        int idLlib = 0;
        String message = "";
        boolean llibreErr = false;

        try {
            idLlib = Integer.parseInt(idLlibre.trim());
            Optional<Llibre> llibre = bookService.findByIdLlibre(idLlib);
            if(llibre !=null) {
                model.addAttribute("llibre", llibre.get());
            } else {
                message = "No hi ha cap llibre amb aquesta id";
                llibreErr = true;
            }
        
        } catch (NumberFormatException e) {
            message = "La id de llibre ha de ser un nombre enter";
            llibreErr = true;
        
        } catch (Exception e) {
            message = "No hi ha cap llibre amb aquesta id";
            llibreErr = true;
        } 
        
        model.addAttribute("message", message);
        model.addAttribute("llibreErr",llibreErr);

        return "cercaid";

    }
        
            @PostMapping("/logout")
    public String logout(SessionStatus status) {
        status.setComplete();
        return "redirect:/";
    }
}