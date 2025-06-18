package com.itsqmet.controller;

import com.itsqmet.service.AutorService;
import com.itsqmet.service.LibroServicio;
import jakarta.persistence.Entity;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@RequestMapping("/admin")
@Controller
public class AdminController {

    @GetMapping("/home")
    public String mostrarPaginaAdmin(){
        return "pages/panelAdmin";
    }
}
