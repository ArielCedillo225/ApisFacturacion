/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.api.general.modelo;

import java.util.Date;
import lombok.Getter;
import lombok.Setter;

/**
 *
 * @author LAB 309 PC8
 */
@Getter
@Setter
public class IngresoCliente {
    private String identificacion;
    private String nombres;
    private String direccion;
    private String telefono;
    private String correo;
    private Date fechaNacimiento;
}
