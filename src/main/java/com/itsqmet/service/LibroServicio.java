package com.itsqmet.service;

import com.itsqmet.entity.Libro;
import com.itsqmet.repository.LibroRepositorio;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class LibroServicio {
    @Autowired
    private LibroRepositorio libroRepositorio; //sirve para intanciar

    //Mostrar los libros
    public List<Libro> mostrarLibros(){
        return libroRepositorio.findAll();
    }
    //Buscar Libro por el titulo
    public List<Libro> buscarPorTitulo(String buscarLibro){
        if (buscarLibro==null||buscarLibro.isEmpty()){
            return libroRepositorio.findAll();
        }else{
            return libroRepositorio.findByTituloContainingIgnoreCase(buscarLibro);
        }
    }
    //Buscar Libro por ID
    public Optional<Libro> buscarLibroId(Long id){
        return  libroRepositorio.findById(id);

    }
    //Guardar Libro
    public  Libro GuardarLibro(Libro libro){
        return libroRepositorio.save(libro);
    }
    //Eliminar el Libro
    public void eliminarLibro(Long id){
        libroRepositorio.deleteById(id);
    }

}
