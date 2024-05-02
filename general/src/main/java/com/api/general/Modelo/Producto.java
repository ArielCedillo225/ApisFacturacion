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
@Table (name="TProducto")
@Getter
@Setter
public class Producto {
    @Id
    @Column (name="PRO_Id") 
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;
    
    @NotBlank(message="El nombre del producto es obligatorio")
    @Size(max=50, message="El nombre del producto no puede tener más de 20 caracteres")
    @Column(name="PRO_Nombre")
    private String nombre;
    
    @NotBlank(message="La descripcion del producto es obligatorio")
    @Size(max=100, message="La descripcion del producto no puede tener más de 20 caracteres")
    @Column(name="PRO_Descripcion")
    private String descripcion;
    
    @NotNull(message="El campo precio es obligatorio")
    @Min(value=0, message="El campo precio debe ser igual o mayor que 0")
    @Column(name="PRO_Precio")
    private float precio;
    
    @NotNull(message="El campo excento iva es obligatorio")
    @Min(value=0, message="El campo excento iva debe ser igual o mayor que 0")
    @Column(name="PRO_ExcentoIva")
    private float excentoIva;
    
    @NotNull(message="La fecha de creación del producto es obligatoria")
    @Column(name="PRO_FechaCreacion")
    private Date fechaCreacion;
    
    @NotNull(message="El campo estado es obligatorio")
    @Min(value=0, message="El campo estado debe ser igual o mayor que 0")
    @Column(name="PRO_Estado")
    private int estado;
    
    @NotNull(message="El campo cantidad es obligatorio")
    @Min(value=0, message="El campo cantidad debe ser igual o mayor que 0")
    @Column(name="PRO_Cantidad")
    private int cantidad;
}
