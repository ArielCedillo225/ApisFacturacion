
package com.api.general.Servicio;

import com.api.general.Modelo.ActivarEstado;
import com.api.general.Repositorio.ClienteRepositorio;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.api.general.modelo.Cliente;
import com.api.general.modelo.IngresoCliente;
import com.api.general.Modelo.RespuestaApi;
import java.util.Date;

/**
 *
 * Autor: Angel Cedillo
 * Descripcion: 
 * Fecha de creacion: 06/04/2024
 */


@Service
public class ClienteServicio {
    @Autowired
    private ClienteRepositorio clienteRepositorio;
    public List<Cliente> Listar(){
        return clienteRepositorio.findAll();
    }
    
    public Cliente Crear(Cliente pCliente){
        return clienteRepositorio.save(pCliente);
    }
    
    public Cliente Actualizar (Cliente pCliente){
        return clienteRepositorio.save(pCliente);
    }
    
    public String Eliminar (Integer pId){
        String MsgRespuesta ="";
        try{
            clienteRepositorio.deleteById(pId);
            MsgRespuesta="Cliente eliminado con exito";
        }
        catch(Exception e){
            MsgRespuesta="Error al cliente: " + pId + " error:"+ e.getMessage();
        }
        return MsgRespuesta;
    }
    
    /*Cambia el estado del dato evitando eliminarlo*/
    public String EliminarEstado(Integer pId){
        String MsgRespuesta ="";
        Cliente clienteEntidad = new Cliente();
        
        try{
            clienteEntidad=clienteRepositorio.ConsultaClienteId(pId);
            clienteEntidad.setEstado(3);
            clienteRepositorio.save(clienteEntidad);
            MsgRespuesta="Cliente eliminado con exito, estado cambiado a eliminado";
        }catch(Exception e){
            MsgRespuesta="Error al eliminar Cliente: " + pId + " error:"+ e.getMessage();
        }
        return MsgRespuesta;
    }
    
    public List<Cliente> ListarActivos(Integer pId){
        return clienteRepositorio.ConsultaClientePorEstado(pId);
    }
    
    public Cliente ListarPorCedula(String pId){
        return clienteRepositorio.ConsultaClientePorCedula(pId);
    }
    
    public String ActualizarEstado(ActivarEstado cIdentificacion){
        RespuestaApi vRespuesta = new RespuestaApi();
        Cliente vCliente = new Cliente();
        vCliente= clienteRepositorio.ConsultaClientePorCedula(cIdentificacion.getIdentificacion());
        if (vCliente.getEstado()==1){
            return "Cliente ya Activo";
            
        } else {
            vCliente.setEstado(1);
        try {
            clienteRepositorio.save(vCliente);
            return "Activacion realizada correctamente";
        } catch (Exception e) {
            return "Proceso no realizado, Error: " +e.getMessage();
        }  
        }
    }
    
    
    public Cliente IngresoDatos(IngresoCliente pIngresoCliente ){
        
        Cliente vCliente = new Cliente();
        String nombres[]=GenerarNombre(pIngresoCliente.getNombres());
        
        Date fechaActual = new Date();

        vCliente.setIdentificacion(pIngresoCliente.getIdentificacion());
        vCliente.setTipoIdentificacion(GenerarTipoIdentificacion(pIngresoCliente.getIdentificacion()));
        vCliente.setNombrePrincipal(nombres[0]);
        vCliente.setNombreSecundario(nombres[1]);
        vCliente.setApellidoPaterno(nombres[2]);
        vCliente.setApellidoMaterno(nombres[3]);
        vCliente.setDireccion(pIngresoCliente.getDireccion());
        vCliente.setTelefono(pIngresoCliente.getTelefono());
        vCliente.setCorreo(pIngresoCliente.getCorreo());
        vCliente.setFechaNacimiento(pIngresoCliente.getFechaNacimiento());
        vCliente.setFechaCreacion(fechaActual);
        vCliente.setEstado(2);
        return vCliente;
    }
    
    
    
    public String GenerarTipoIdentificacion(String vIdentificacion){
        String tipo;
        tipo = switch (vIdentificacion.length()) {
            case 10 -> "C";
            case 13 -> "R";
            default -> "P";
        };
        return tipo;
    }
    
    public String[] GenerarNombre(String pNombre){
         String nombres[]=pNombre.split(" ");
        return nombres;
    }
    
    public String Registrar(IngresoCliente pIngresoCliente){
        RespuestaApi vRespuesta = new RespuestaApi();
        Cliente vCliente = new Cliente();
        
        vCliente= clienteRepositorio.ConsultaClientePorCedula(pIngresoCliente.getIdentificacion());
        
        if(vCliente!=null){
            return "Ya cedula del cliente ya esta registrada";
        } else{
            vCliente=IngresoDatos(pIngresoCliente);
        
        switch (verificarCorreo(vCliente.getCorreo())) {
            case 1 -> {
                return "Se debe colocar un @";
            }
            case 2 -> {
                return "El arroba no puede ir ni al inicio ni al final del correo";
            }
            default -> {
            }
        }
        
        try {
            clienteRepositorio.save(vCliente);
            return "Cliente agregado correctamente";
        } catch (Exception e) {
            return "Proceso no realizado. Error: " +e.getMessage();
        }        
        }
    }
    
    public RespuestaApi Respuesta(String pCodigo, String pMsgTecnico, String pMsgUsuario){
        RespuestaApi vRespuesta = new RespuestaApi();
        vRespuesta.setCodigoRespuesta(pCodigo);
        vRespuesta.setMensajeTecnico(pMsgTecnico);
        vRespuesta.setMensajeUsuario(pMsgUsuario);
        return vRespuesta;
    }
    
    
    public int verificarCorreo (String pCorreo) {
        
        char checkArroba = '@';
        
        int posicionArroba = pCorreo.indexOf(checkArroba);
        
        if(posicionArroba > 0 && posicionArroba < pCorreo.length() - 1){
            return 3;
        } else if(posicionArroba >= 0){
            return 2;
        } else {
            return 1;
        }
    }
}


