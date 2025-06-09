package com.itsqmet.service;

import com.itsqmet.entity.Autor;
import com.itsqmet.entity.Libro;
import com.itsqmet.repository.AutorRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service

public class AutorService {
@Autowired
    private AutorRepository autorRepository;

    //Mostrar los autores
    public List<Autor> mostrarAutor(){
        return autorRepository.findAll();
    }
    //Buscar Autor por el nombre
    public List<Autor> buscarPorNombre(String buscarAutor){
        if (buscarAutor==null||buscarAutor.isEmpty()){
            return autorRepository.findAll();
        }else{
            return autorRepository.findByNombreContainingIgnoreCase(buscarAutor);
        }
    }

    //Buscar Autor por ID
    public Optional<Autor> buscarAutorId(Long id){
        return  autorRepository.findById(id);

    }
    //Guardar autor
    public  Autor GuardarAutor(Autor autor){
        return autorRepository.save(autor);
    }
    //Eliminar el autor
    public void eliminarAutor(Long id){
        autorRepository.deleteById(id);
    }


}
