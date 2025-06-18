package com.itsqmet.controller;


import org.springframework.security.core.Authentication;
import org.springframework.security.core.userdetails.User;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class LoginController {
    @GetMapping("/login")
    public String mostarLogin(){
        return "pages/UsuarioPag/Login";
    }

    @GetMapping("/postLogin")
    public String redirigirPorRol(Authentication authentication){
        //fevuelve un objeto que representa al usuari autenticado
        User usuario=(User) authentication.getPrincipal();
        //Obtine las autoridades del usuario autenticado
        String role =usuario.getAuthorities().stream()
                .map(grantedAuthority -> grantedAuthority.getAuthority())
                .findFirst()
                .orElse("");
        if(role.equals("ROLE_ADMIN")){
            return "redirect:/admin/home";
        } else if (role.equals("ROLE_BIBLIOTECARIO")) {
            return "redirect:/autores";
        } else if (role.equals("ROLE_ESTUDIANTE")) {
            return "redirect:/libros";
        }else {
            return "redirect:/login?error=true";
        }

    }
}
