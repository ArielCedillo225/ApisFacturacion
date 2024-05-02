/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.api.general.Controlador;

import com.api.general.Modelo.Prestamo;
import com.api.general.Servicio.PrestamoServicio;
import jakarta.validation.Valid;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

/**
 *
 * @author ariel
 */

@RestController
public class PrestamoControlador {
     @Autowired
    private PrestamoServicio prestamoServicio;
    
    @GetMapping("/Prestamo/Listar")
    public List<Prestamo> ObtenerPrestamo(){
        return prestamoServicio.ListarPrestamo();
    }
    
    @PostMapping("/Prestamo/Insertar")
    public String InsertarPrestamo(@RequestBody Prestamo pPrestamo) {
       return prestamoServicio.CrearPrestamo(pPrestamo);
        
    }
    
    @GetMapping("/Prestamo/Devolver/{id}")
    public String DevolverPrestamo(@PathVariable("id") Integer pId) {
       return prestamoServicio.DevolucionPrestamo(pId);
        
    }
    
    @DeleteMapping("/Prestamo/Eliminar/{id}")
    public String EliminarPrestamo(@PathVariable("id") Integer pId){
        return prestamoServicio.EliminarPrestamo(pId);
    }
    
}
