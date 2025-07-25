package com.itsqmet.repository;

import com.itsqmet.entity.Usuario;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface UsuarioRepository extends JpaRepository<Usuario,Long> {
    //budcar usuario por su nombre de usuario
    Optional<Usuario> findByUsername(String username);
}
