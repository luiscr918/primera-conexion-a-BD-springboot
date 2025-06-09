package com.itsqmet.repository;

import com.itsqmet.entity.Autor;
import com.itsqmet.entity.Libro;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface AutorRepository extends JpaRepository<Autor, Long> {
    List<Autor> findByNombreContainingIgnoreCase(String titulo); //buscar por titulo
}
