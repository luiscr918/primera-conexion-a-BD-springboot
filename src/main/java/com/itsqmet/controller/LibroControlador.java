package com.itsqmet.controller;

import com.itsqmet.entity.Autor;
import com.itsqmet.entity.Genero;
import com.itsqmet.entity.Libro;
import com.itsqmet.service.AutorService;
import com.itsqmet.service.GeneroService;
import com.itsqmet.service.LibroServicio;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@Controller
public class LibroControlador {
    @Autowired
    private LibroServicio libroServicio;
    //traer la lista de autores datos de la base
    @Autowired
    private AutorService autorService;
    //traer la lista de generos de la base
    @Autowired
    private GeneroService generoService;

    //Leer los libros
    @GetMapping("/libros")
    public String listarLibros(@RequestParam (name="buscarLibro",required = false,defaultValue = "")
                                    String buscarLibro, Model model){
        List <Libro> libros = libroServicio.buscarPorTitulo(buscarLibro);
        model.addAttribute("buscarLibro",buscarLibro);
        model.addAttribute("libros",libros);
        return "pages/listaLibros";
    }
    //Insertar un Nuevo Libro
    @GetMapping("/formularioLibro")
    public String formularioLibro(Model model){
        List<Autor> autores=autorService.mostrarAutor();
        List<Genero> generos=generoService.obtenerGeneros();
        model.addAttribute("libro", new Libro());
        model.addAttribute("autores",autores);
        model.addAttribute("generos",generos);
        return "pages/formularioLibro";
    }
    @PostMapping("/guardar-libro")
    public String guardarLibro(Libro libro){
        libroServicio.GuardarLibro(libro);
        return "redirect:/libros";
    }
    //Actualizar Libro
    @GetMapping("/editar-libro/{id}")
    public String actualizarLibro(@PathVariable Long id, Model model){
        Optional<Libro> libro = libroServicio.buscarLibroId(id);
        model.addAttribute("libro", libro);
        model.addAttribute("autores",autorService.mostrarAutor()); //es lo mismo que hice arriba pero sin declarar una lista
        //traer los generos pára actualizar
        model.addAttribute("generos",generoService.obtenerGeneros());
        return "pages/formularioLibro";
    }
    //Eliminar Libro
    @GetMapping("/eliminar-libro/{id}")
    public String eliminarLibro(@PathVariable Long id){
         libroServicio.eliminarLibro(id);
         return "redirect:/libros";
    }

}
