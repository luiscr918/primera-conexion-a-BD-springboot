package com.itsqmet.entity;

import jakarta.persistence.*;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Entity
@Data
@NoArgsConstructor
public class Usuario {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;
    private String nombre;
    private String direccion;
    @OneToMany(mappedBy = "usuario", fetch = FetchType.LAZY) //solo cuando de click en la opcion libro va a traer datos
    private List<Prestamo> prestamos;


    @OneToOne (mappedBy = "usuario")
    private TarjetaSuscripcion tarjetaSuscripcion;

}
