package com.itsqmet.controller;

import com.itextpdf.text.Document;
import com.itextpdf.text.DocumentException;
import com.itextpdf.text.Paragraph;
import com.itextpdf.text.pdf.PdfWriter;
import com.itsqmet.entity.Autor;
import com.itsqmet.entity.Genero;
import com.itsqmet.entity.Libro;
import com.itsqmet.service.AutorService;
import com.itsqmet.service.GeneroService;
import com.itsqmet.service.LibroServicio;
import jakarta.servlet.http.HttpServletResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.io.IOException;
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
    //metodo para pdf
    @GetMapping("/librosPDF")
    public void exportarPDF(HttpServletResponse response )throws IOException, DocumentException {
        response.setContentType("application/pdf");
        response.setHeader("Content-Disposition", "attachment; filename=libros.pdf");
        List<Libro> libros = libroServicio.mostrarLibros();
        Document documento = new Document();
        PdfWriter.getInstance(documento, response.getOutputStream());
        documento.open();
        documento.add(new Paragraph("Listado de Libros"));
        documento.add(new Paragraph(" "));
        for (Libro libro : libros ){
            documento.add(new Paragraph(libro.getId() + " - " + libro.getTitulo() + " - " + libro.getAutor().getNombre()));
        }
        documento.close();
    }

}
