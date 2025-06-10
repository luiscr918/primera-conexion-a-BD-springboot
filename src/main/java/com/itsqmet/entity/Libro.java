package com.itsqmet.entity;

import jakarta.persistence.*;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Entity
@Table(name = "Libros") //el table solo se pone si quiero ponerle otro nombre a la tabla
@Data
@NoArgsConstructor
public class Libro {
     @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY) //esto hace que sea autoincrementable
    private long id;

     @Column (unique = true) //para que mi titulo sea unico
     private String titulo;
     private String editorial;
     private String paginas;
     private Double precio;

    @ManyToOne
    @JoinColumn(name = "codigo_autor")
    private Autor autor;

    @ManyToOne
    @JoinColumn(name = "codigo_genero")
    private Genero genero;
    @OneToMany(mappedBy = "libro", fetch = FetchType.LAZY) //solo cuando de click en la opcion libro va a traer datos
    private List<Prestamo> prestamos;

}

