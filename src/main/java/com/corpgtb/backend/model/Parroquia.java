package com.corpgtb.backend.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;

@Entity
@Table(name = "parroquia")
@Data
@NoArgsConstructor
@AllArgsConstructor
public class Parroquia {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "parroquia_id")
    Long parroquiaId;

    @Column(name = "nombre")
    private String nombre;

    @Column(name = "estado")
    private Boolean estado;

    @ManyToOne
    @JoinColumn(name = "ciudad_id", referencedColumnName = "ciudad_id")
    Ciudad ciudadId;
}
