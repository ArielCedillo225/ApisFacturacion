/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.api.general.Controlador;

import com.api.general.Modelo.Producto;
import com.api.general.Modelo.RespuestaApi;
import com.api.general.Servicio.ProductoServicio;
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
public class ProductoControlador {
    @Autowired
    private ProductoServicio productoServicio;
    
    @GetMapping("/Producto/Listar")
    public List<Producto> ObtenerProductos(){
        return productoServicio.ListarProducto();
    }
    
    @GetMapping("/Producto/ListarActivos")
    public List<Producto> ObtenerClientesActivo(){
        return productoServicio.ListarActivos();
    }
    
    @PostMapping("/Producto/Insertar")
    public Producto InsertarProducto(@RequestBody Producto pProducto) {
       return productoServicio.CrearProducto(pProducto);
    }
    
    @PutMapping("/Producto/Actualizar")
    public Producto ActualizarProducto(@RequestBody Producto pProducto) {
       return productoServicio.CrearProducto(pProducto);
    }
    
    @DeleteMapping("/Producto/Eliminar/{id}")
    public String Eliminar(@PathVariable("id") Integer pId){
        return productoServicio.EliminarProducto(pId);
    }
    
    @DeleteMapping("/Producto/EliminarPorEstado/{id}")
    public String EliminarEstado(@PathVariable("id") Integer pId){
        return productoServicio.EliminarPorEstadoProducto(pId);
    }
}
