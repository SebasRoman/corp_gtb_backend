package com.corpgtb.backend.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;

@Entity
@Table(name = "provincia")
@Data
@NoArgsConstructor
@AllArgsConstructor
public class Provincia {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "provincia_id")
    private Long provinciaId;

    @Column(name = "nombre")
    private String nombre;

    @Column(name = "estado")
    private Boolean estado;
}
