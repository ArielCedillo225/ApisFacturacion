/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.api.general.Servicio;

import com.api.general.Repositorio.ProductoRepositorio;
import com.api.general.Modelo.Producto;
import com.api.general.Modelo.RespuestaApi;

import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
/**
 *
 * Autor: Angel Cedillo
 * Descripcion: 
 * Fecha de creacion: 30/04/2024
 */

@Service
public class ProductoServicio {
    @Autowired
    private ProductoRepositorio productoRepositorio;
    public List<Producto> ListarProducto(){
        return productoRepositorio.findAll();
    }
    
    public List<Producto> ListarActivos(){
        return productoRepositorio.ConsultaProductoActivo();
    }
    
    public Producto CrearProducto(Producto pCliente){
        return productoRepositorio.save(pCliente);
    }
    
    public Producto ActualizarProducto (Producto pCliente){
        return productoRepositorio.save(pCliente);
    }
    
    public String EliminarProducto (Integer pId){
        String MsgRespuesta ="";
        try{
            productoRepositorio.deleteById(pId);
            MsgRespuesta="Producto eliminado con exito";
        }
        catch(Exception e){
            MsgRespuesta="Error al producto estado: " + pId + " error:"+ e.getMessage();
        }
        return MsgRespuesta;
    }
    
    /*Cambia el estado del dato evitando eliminarlo*/
    public String EliminarPorEstadoProducto(Integer pId){
        String MsgRespuesta ="";
        Producto productoEntidad = new Producto();
        
        try{
            productoEntidad=productoRepositorio.ConsulaProductoId(pId);
            productoEntidad.setEstado(1);
            productoRepositorio.save(productoEntidad);
            MsgRespuesta="Producto eliminado con exito, estado cambiado a En Proceso";
        }catch(Exception e){
            MsgRespuesta="Error al eliminar Producto: " + pId + " error:"+ e.getMessage();
        }
        return MsgRespuesta;
    }
    
    public RespuestaApi Respuesta(String pCodigo, String pMsgTecnico, String pMsgUsuario){
        RespuestaApi vRespuesta = new RespuestaApi();
        vRespuesta.setCodigoRespuesta(pCodigo);
        vRespuesta.setMensajeTecnico(pMsgTecnico);
        vRespuesta.setMensajeUsuario(pMsgUsuario);
        return vRespuesta;
    }    
    
}

