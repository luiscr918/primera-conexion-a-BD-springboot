package com.itsqmet.service;

import com.itsqmet.entity.Autor;
import com.itsqmet.entity.Usuario;
import com.itsqmet.repository.UsuarioRepository;
import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class UsuarioService {
    @Autowired
    private UsuarioRepository usuarioRepository;
    //cifrar la contraseña
    @Autowired
    private PasswordEncoder passwordEncoder;

    //Mostrar los usuarios
    public List<Usuario> mostrarUsuario(){
        return usuarioRepository.findAll();
    }

    //Buscar usuario por ID
    public Optional<Usuario> buscarUsuarioId(Long id){
        return  usuarioRepository.findById(id);

    }
    //Guardar usuario
    public  Usuario GuardarUsuario(Usuario usuario){
        //encripto
        String passwordEncriptado=passwordEncoder.encode(usuario.getPassword());
        //añado el encriptado al objeto
        usuario.setPassword(passwordEncriptado);
        return usuarioRepository.save(usuario);
    }
    //Eliminar el usuario
    public void eliminarUsuario(Long id){
        usuarioRepository.deleteById(id);
    }

}
