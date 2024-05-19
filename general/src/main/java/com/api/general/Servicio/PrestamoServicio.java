/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.api.general.Servicio;

import com.api.general.Modelo.Prestamo;
import com.api.general.Modelo.Producto;
import com.api.general.Repositorio.ClienteRepositorio;
import com.api.general.Repositorio.PrestamoRepositorio;
import com.api.general.Repositorio.ProductoRepositorio;
import com.api.general.modelo.Cliente;
import com.api.general.Modelo.Devolucion;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 *
 * @author ariel
 */

@Service
public class PrestamoServicio {
     @Autowired
    private PrestamoRepositorio prestamoRepositorio;
     @Autowired
    private ProductoRepositorio productoRepositorio;
     @Autowired
    private ClienteRepositorio clienteRepositorio;
    public List<Prestamo> ListarPrestamo(){
        return prestamoRepositorio.ConsultaNoDevueltos();
    }
    
    public String CrearPrestamo(Prestamo pPrestamo){
        
        Producto pProducto = new Producto();
        Cliente pCliente = new Cliente();
        String errors="";
        
        List<Prestamo> checkPrestamosVencidos = new ArrayList<>();
        
        int stockRestante;
        
        Date fechaActualSinFormato = new Date();
        
        prestamoRepositorio.ConsultarDevolucion(fechaActualSinFormato);
        
        checkPrestamosVencidos = prestamoRepositorio.ConsultaEnMora(pPrestamo.getCliente());
        
        if (checkPrestamosVencidos.isEmpty()) {
            LocalDateTime fecha = LocalDateTime.now();
            DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd");
            String fechaActual = fecha.format(formatter);

            pProducto = productoRepositorio.ConsulaProductoId(pPrestamo.getProducto());
            pCliente = clienteRepositorio.ConsultaClienteId(pPrestamo.getCliente());

            errors=ValidadoresPrestamo(pCliente, pProducto, pPrestamo.getCantidad());

            if(errors==""){
                try {
                    stockRestante=pProducto.getCantidad()-pPrestamo.getCantidad();
                    pProducto.setCantidad(stockRestante);
                    pPrestamo.setEstado(4);
                    pPrestamo.setFechaCreacion(fechaActual);
                    
                    productoRepositorio.save(pProducto);
                    prestamoRepositorio.save(pPrestamo);
                    return ("Prestamo generado correctamente");
                } catch (Exception e) {
                    return ("Error generando usuarios, intentelo nuevamente. " + e.getMessage());
                }
            } else {
                return "Se encontraron los siguientes errores: " + errors + " Intente nuevamente";
            }      
        } else {
            return "No se pueden agendar prestamos, hay productos en mora";
        }
    }
    
    public String DevolucionPrestamo(Devolucion pDevolucion){
        
        if(pDevolucion.getParcial()==1){
            return DevolucionParcial(pDevolucion);
        } else{
        Prestamo pPrestamo = new Prestamo();       
        pPrestamo = prestamoRepositorio.ConsultaPrestamoId(pDevolucion.getId());
        if(pPrestamo==null){
            return "Id del prestamo no encontrado.";
        }       
        if(pPrestamo.getEstado()==5){
            return "Prestamo ya devuelto.";
        }       
        Producto pProducto = new Producto();
        Cliente pCliente = new Cliente();
        String errors="";
        int stockRestante;
        pProducto = productoRepositorio.ConsulaProductoId(pPrestamo.getProducto());
        pCliente = clienteRepositorio.ConsultaClienteId(pPrestamo.getCliente());
        errors=ValidadoresPrestamo(pCliente, pProducto, pPrestamo.getCantidad());
        
        if(errors==""){
            try {
                stockRestante=pProducto.getCantidad()+pPrestamo.getCantidad();
                pProducto.setCantidad(stockRestante);
                pPrestamo.setEstado(5);
                pPrestamo.setCantidad(0);
                productoRepositorio.save(pProducto);
                prestamoRepositorio.save(pPrestamo);
                return ("Producto Devuelto Correctamente");
            } catch (Exception e) {
                return ("Error generando usuarios, intentelo nuevamente. " + e.getMessage());
            }
        } else {
            return "Se encontraron los siguientes errores: " + errors + " Intente nuevamente";
        }    }
    }
    
     public String DevolucionParcial(Devolucion pDevolucion){
        
        Prestamo pPrestamo = new Prestamo();
        
        pPrestamo = prestamoRepositorio.ConsultaPrestamoId(pDevolucion.getId());
        
        if(pPrestamo==null){
            return "Id del prestamo no encontrado.";
        }
        
        if(pPrestamo.getEstado()==5){
            return "Prestamo ya devuelto.";
        }
        
        
        Producto pProducto = new Producto();
        Cliente pCliente = new Cliente();
        String errors="";
        int stockRestante;
        pProducto = productoRepositorio.ConsulaProductoId(pPrestamo.getProducto());
        pCliente = clienteRepositorio.ConsultaClienteId(pPrestamo.getCliente());
        errors=ValidadoresPrestamo(pCliente, pProducto, pPrestamo.getCantidad());
        
        if(errors==""){
            try {
                stockRestante=pProducto.getCantidad()+pDevolucion.getCantidad();
                pProducto.setCantidad(stockRestante);
                pPrestamo.setCantidad(pPrestamo.getCantidad()-pDevolucion.getCantidad());
                if(pPrestamo.getCantidad()==0){
                    pPrestamo.setEstado(5);
                }
                productoRepositorio.save(pProducto);
                prestamoRepositorio.save(pPrestamo);
                return ("Se han devuelto correctamente " + pDevolucion.getCantidad() + ", "
                        + "aun faltan " + pPrestamo.getCantidad() + " unidades por devolver");
            } catch (Exception e) {
                return ("Error generando usuarios, intentelo nuevamente. " + e.getMessage());
            }
        } else {
            return "Se encontraron los siguientes errores: " + errors + " Intente nuevamente";
        }     
    }

    
    public Prestamo ActualizarPrestamo (Prestamo pPrestamo){
        return prestamoRepositorio.save(pPrestamo);
    }
    
    public String EliminarPrestamo (Integer pId){
        String MsgRespuesta ="";
        try{
            prestamoRepositorio.deleteById(pId);
            MsgRespuesta="Prestamo eliminado con exito";
        }
        catch(Exception e){
            MsgRespuesta="Error al cliente estado: " + pId + " error:"+ e.getMessage();
        }
        return MsgRespuesta;
    }
    
    public String ValidadoresPrestamo (Cliente pCliente, Producto pProducto, int cantidad){
        
        String errores="";
        
        if(pProducto == null)
            errores=errores+ "No se encontró el producto correspondiente. ";
        if(pCliente == null)
            errores=errores+ "No se encontró el cliente correspondiente. ";
        if(pProducto != null && cantidad>pProducto.getCantidad())
             errores=errores+ "No hay suficiente stock. ";
        if (pProducto != null && pProducto.getEstado()!=1)
             errores=errores+ "Producto no activo. ";
        if (pCliente != null && pCliente.getEstado()!=1)
             errores=errores+ "Cliente no activo. ";
        
        return errores;
    }
}
