package com.itsqmet.entity;

import jakarta.persistence.*;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.format.annotation.DateTimeFormat;

import java.util.Date;

@Entity
@Data
@NoArgsConstructor
public class Prestamo {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;

    @ManyToOne
    @JoinColumn(name = "codigo_libro")
    private Libro libro;
    @ManyToOne
    @JoinColumn(name ="codigo_usuario")
    private Usuario usuario;

    @DateTimeFormat(pattern = "yyyy-mm-dd")
    private Date fecha_prestamo;
    @DateTimeFormat(pattern = "yyyy-mm-dd")
    private Date fecha_devolucion;
}
