package com.itsqmet.controller;

import com.itsqmet.entity.Autor;
import com.itsqmet.entity.Genero;
import com.itsqmet.entity.Libro;
import com.itsqmet.entity.Usuario;
import com.itsqmet.service.UsuarioService;
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
public class UsuarioController {

    @Autowired
    private UsuarioService usuarioService;


    //listar usuarios
    @GetMapping("/usuarios")
    public String listarUsuarios( Model model){
        model.addAttribute("usuarios",usuarioService.mostrarUsuario());
        return "pages/UsuarioPag/listaUsuarios";
    }
    //Insertar un Nuevo Usuario
    @GetMapping("/formularioUsuario")
    public String formularioUsuario(Model model){
        model.addAttribute("usuario", new Usuario());
        return "pages/UsuarioPag/formularioUsuario";
    }
    @PostMapping("/guardar-usuario")
    public String guardarUsuario(Usuario usuario){
        usuarioService.GuardarUsuario(usuario);
        return "redirect:/usuarios";
    }
    //Actualizar Usuario
    @GetMapping("/editar-usuario/{id}")
    public String actualizarUsuario(@PathVariable Long id, Model model){
        Optional<Usuario>usuario  = usuarioService.buscarUsuarioId(id);
        model.addAttribute("usuario",usuario);
        return "pages/UsuarioPag/formularioUsuario";
    }
    //Eliminar Usuario
    @GetMapping("/eliminar-usuario/{id}")
    public String eliminarUsuario(@PathVariable Long id){
        usuarioService.eliminarUsuario(id);
        return "redirect:/usuarios";
    }



}
