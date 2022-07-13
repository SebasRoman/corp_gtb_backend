package com.corpgtb.backend.model;

import com.corpgtb.backend.model.enums.RolEnum;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;

@Entity
@Table(name = "usuario")
@Data
@NoArgsConstructor
@AllArgsConstructor
public class Usuario {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "usuario_id")
    private Long usuarioId;

    @Column(name = "nombre" )
    private String nombre;

    @Column(name = "password")
    private String password;

    @Column(name = "estado")
    private Boolean estado;

    @Column(name = "rol")
    @Enumerated(EnumType.STRING)
    private RolEnum rol;
}
