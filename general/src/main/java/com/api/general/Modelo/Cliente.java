/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.api.general.modelo;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import jakarta.validation.constraints.Max;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import java.util.Date;
import lombok.Getter;
import lombok.Setter;

/**
 *
 * Autor: Angel Cedillo
 * Descripcion: Manejo de clientes para la base facturacion
 * Fecha de creacion: 06-04-2024
 */

@Entity
@Table (name="TCliente")
@Getter
@Setter
public class Cliente {
    @Id
    @Column (name="CLI_Id") 
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;
    
    @NotBlank(message="La identificacion del cliente es obligatorio")
    @Size(max=20, message="La identificacion del cliente no puede tener más de 20 caracteres")
    @Column(name="CLI_Identificacion")
    private String identificacion;

    @NotBlank(message="El tipo de identificación del cliente es obligatorio")
    @Size(max=1, message="El tipo de identificacion solo puede tener un caracter")
    @Column(name="CLI_TipoIdentificacion")
    private String tipoIdentificacion;

    @NotBlank(message="El primer nombre del cliente es obligatorio")
    @Size(max=50, message="El primer nombre del cliente no puede tener más de 50 caracteres")
    @Column(name="CLI_NombrePrincipal")
    private String nombrePrincipal;

    @NotBlank(message="El segundo nombre del cliente es obligatorio")
    @Size(max=50, message="El segundo nombre del cliente no puede tener más de 50 caracteres")
    @Column(name="CLI_NombreSecundario")
    private String nombreSecundario;

    @NotBlank(message="El apellido paterno del cliente es obligatorio")
    @Size(max=50, message="El apellido paterno del cliente no puede tener más de 50 caracteres")
    @Column(name="CLI_ApellidoPaterno")
    private String apellidoPaterno;

    @NotBlank(message="El apellido materno del cliente es obligatorio")
    @Size(max=50, message="El apellido materno del cliente no puede tener más de 50 caracteres")
    @Column(name="CLI_ApellidoMaterno")
    private String apellidoMaterno;

    @NotBlank(message="La dirección del cliente es obligatoria")
    @Size(max=100, message="La dirección del cliente no puede tener más de 100 caracteres")
    @Column(name="CLI_Direccion")
    private String direccion;

    @NotBlank(message="El teléfono del cliente es obligatorio")
    @Size(max=20, message="El teléfono del cliente no puede tener más de 20 caracteres")
    @Column(name="CLI_Telefono")
    private String telefono;

    @NotBlank(message="El correo del cliente es obligatorio")
    @Size(max=100, message="El correo del cliente no puede tener más de 100 caracteres")
    @Column(name="CLI_Correo")
    private String correo;

    @NotNull(message="La fecha de nacimiento del cliente es obligatoria")
    @Column(name="CLI_FechaNacimiento")
    private Date fechaNacimiento;

    @NotNull(message="La fecha de creación del cliente es obligatoria")
    @Column(name="CLI_FechaCreacion")
    private Date fechaCreacion;

    @NotNull(message="El campo estado es obligatorio")
    @Min(value=0, message="El campo estado debe ser igual o mayor que 0")
    @Column(name="CLI_Estado")
    private int estado;
}
