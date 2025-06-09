package com.itsqmet.controller;

import com.itsqmet.entity.Autor;
import com.itsqmet.entity.Libro;
import com.itsqmet.service.AutorService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.List;
import java.util.Optional;

@Controller
public class AutorController {
    @Autowired
    private AutorService autorService;

    //Leer los autores
    @GetMapping("/autores")
    public String listarAutores(@RequestParam (name="buscarAutor",required = false,defaultValue = "")
                               String buscarAutor, Model model){
        List <Autor> autores = autorService.buscarPorNombre(buscarAutor);
        model.addAttribute("buscarAutor",buscarAutor);
        model.addAttribute("autores",autores);
        return "pages/listaAutores";
    }
    //Insertar un Nuevo Libro
    @GetMapping("/formularioAutor")
    public String formularioAutor(Model model){
        model.addAttribute("autor", new Autor());

        return "pages/formularioAutor";
    }
    @PostMapping("/guardar-autor")
    public String guardarAutor(Autor autor){
        autorService.GuardarAutor(autor);
        return "redirect:/autores";
    }
    //Actualizar Libro
    @GetMapping("/editar-autor/{id}")
    public String actualizarAutor(@PathVariable Long id, Model model){
        Optional<Autor> autor = autorService.buscarAutorId(id);
        model.addAttribute("autor", autor);
        return "pages/formularioAutor";
    }
    //Eliminar Libro
    @GetMapping("/eliminar-autor/{id}")
    public String eliminarLibro(@PathVariable Long id){
        autorService.eliminarAutor(id);
        return "redirect:/autores";
    }
}
