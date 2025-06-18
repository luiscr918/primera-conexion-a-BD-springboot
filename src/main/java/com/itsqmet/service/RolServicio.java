package com.itsqmet.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.itsqmet.repository.RolRepository;
import com.itsqmet.entity.Rol;

import java.util.List;

@Service
public class RolServicio {
    @Autowired
    private RolRepository rolRepository;

    //mostrar todos los roles
    public List<Rol> mostrarRoles(){
        List<Rol> roles=rolRepository.findAll();
       return  roles;
    }
}
