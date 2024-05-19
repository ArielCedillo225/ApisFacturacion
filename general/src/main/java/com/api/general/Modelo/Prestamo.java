/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.api.general.Modelo;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.Max;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import java.util.Date;
import lombok.Getter;
import lombok.Setter;


/**
 * Autor: Angel Cedillo
 * Descripcion: Manejo de productos para la base facturacion
 * Fecha de creacion: 30-04-2024
 */

@Entity
@Table (name="TPrestamo")
@Getter
@Setter
public class Prestamo {
    @Id
    @Column (name="PRE_Id") 
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;
    
    @NotNull(message="El campo estado es obligatorio")
    @Min(value=0, message="El campo estado debe ser igual o mayor que 0")
    @Column(name="PRE_Estado")
    private int estado;
    
    @NotNull(message="El id del cliente es obligatorio")
    @Min(value=0, message="El id del cliente debe ser igual o mayor que 0")
    @Column(name="PRE_Cliente")
    private int cliente;
    
    @NotNull(message="El id del producto es obligatorio")
    @Min(value=0, message="El id del producto debe ser igual o mayor que 0")
    @Column(name="PRE_Producto")
    private int producto;
    
    @NotNull(message="La fecha de creación del prestamo es obligatoria")
    @Column(name="PRE_FechaCreacion")
    private String fechaCreacion;
    
    @NotNull(message="La fecha de devolucion es obligatoria")
    @Column(name="PRE_FechaDevolucion")
    private Date fechaDevolucion;
    
    @NotNull(message="El campo cantidad es obligatorio")
    @Min(value=1, message="El campo cantidad debe ser igual o mayor a 1")
    @Column(name="PRE_Cantidad")
    private int cantidad;
}
