package com.itsqmet.entity;

import jakarta.persistence.*;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Date;

@Entity
@Data
@NoArgsConstructor
public class Prestamo {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;
    private Date fecha_prestamo;
    private Date fecha_devolucion;
    @ManyToOne
    @JoinColumn(name = "codigo_libro")
    private Libro libro;
    @ManyToOne
    @JoinColumn(name ="codigo_usuario")
    private Usuario usuario;
}
