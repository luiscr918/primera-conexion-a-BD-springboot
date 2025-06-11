package com.itsqmet.service;

import com.itsqmet.entity.Genero;
import com.itsqmet.repository.GeneroRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class GeneroService {
    @Autowired
    GeneroRepository generoRepository;

    //llamar a todos los generos
    public List<Genero> obtenerGeneros(){
        return generoRepository.findAll();
    }
}
