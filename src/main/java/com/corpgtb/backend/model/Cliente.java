package com.corpgtb.backend.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.hibernate.search.annotations.*;
import org.hibernate.search.annotations.Index;

import javax.persistence.*;

@Entity
@Table(name = "cliente")
@Data
@NoArgsConstructor
@AllArgsConstructor
@Indexed
public class Cliente {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "cliente_id")
    private Long clienteId;

    @Field(index= Index.YES, analyze= Analyze.YES, store= Store.YES, name = "identificacion")
    @Column(name = "identificacion", length = 13, unique = true, nullable = false)
    private String identificacion;

    @Field(index=Index.YES, analyze=Analyze.YES, store=Store.YES, name = "razon_social")
    @Column(name = "razon_social")
    private String razonSocial;

    @Field(index=Index.YES, analyze=Analyze.YES, store=Store.YES, name = "nombre_comercial")
    @Column(name = "nombre_comercial")
    private String nombreComercial;

    @Column(name = "direccion")
    private String direccion;

    @Column(name = "telefono1")
    private String telefono1;

    @Column(name = "telefono2")
    private String telefono2;

    @Column(name = "cupo_credito")
    private Double cupoCredito;

    @Column(name = "dias_credito")
    private Integer diasCredito;

    @Column(name = "cuenta_contable")
    private String cuentaContable;

    @Column(name = "direccion_referencial")
    private String direccionReferencial;

    @Column(length = 45)
    @Field(index=Index.YES, analyze=Analyze.YES, store=Store.YES, name = "email")
    private String email;

    @Column(name = "estado")
    private Boolean estado;

    @ManyToOne(cascade = {CascadeType.ALL})
    @JoinColumn(name = "usuario_id", referencedColumnName = "usuario_id")
    private Usuario usuarioId;

    @ManyToOne
    @JoinColumn(name = "parroquia_id", referencedColumnName = "parroquia_id")
    private Parroquia parroquiaId;

}
