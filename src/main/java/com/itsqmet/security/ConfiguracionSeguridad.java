package com.itsqmet.security;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;

@Configuration
@EnableWebSecurity
public class ConfiguracionSeguridad {
    @Bean
    public SecurityFilterChain securityFilterChain(HttpSecurity http) throws
            Exception{
        http
                .authorizeHttpRequests(auth ->auth
                        .requestMatchers("/","login","/formularioUsuario","/guardar-usuario","/css/**","/js/**").permitAll()
                        .requestMatchers("/autores").hasRole("BIBLIOTECARIO")
                        .requestMatchers("/formularioLibro","/guardar-libro").hasRole("ESTUDIANTE")
                        //ASIGNAR MISMA RUTA A MAS DE UN ROL
                        .requestMatchers("/libros").hasAnyRole("ESTUDIANTE","BIBLIOTECARIO")
                        .requestMatchers("/admin").hasRole("ADMIN")
                        .anyRequest().authenticated()
                )
                .formLogin(form->form
                                .loginPage("/login")
                                .permitAll()
                                .defaultSuccessUrl("/postLogin",true)
                        )
                .logout(logout->logout
                        .logoutUrl("/logout")
                        .logoutSuccessUrl("/login?logout")
                        .permitAll()
                );
        return http.build();
    }
    @Bean
    public PasswordEncoder passwordEncoder(){
        return new BCryptPasswordEncoder();
    }
}
