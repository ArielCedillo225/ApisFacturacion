
package com.api.general.Controlador;

import com.api.general.Modelo.RespuestaApi;
import com.api.general.Servicio.ClienteServicio;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import com.api.general.modelo.Cliente;
import com.api.general.modelo.IngresoCliente;
import jakarta.validation.Valid;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class ClienteControlador {
    @Autowired
    private ClienteServicio clienteServicio;
    
    @GetMapping("/Cliente/Listar")
    public List<Cliente> ObtenerClientes(){
        return clienteServicio.Listar();
    }
    
    @PostMapping("/Cliente/Insertar")
    public ResponseEntity<Object> InsertarCliente(@RequestBody Cliente pCliente) {
       String MsgRespuesta="";
        try {
            clienteServicio.Crear(pCliente);
            MsgRespuesta="Cliente: " + pCliente.getNombrePrincipal() + " " + pCliente.getApellidoPaterno()+" " +" Insertado Correctamente";
            return ResponseEntity.ok(MsgRespuesta);
        } catch (Exception e) {
            
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("Error al ingresar el cliente");
        }
        
    }
    
    @DeleteMapping("/Cliente/Eliminar/{id}")
    public String Eliminar(@PathVariable("id") Integer pId){
        return clienteServicio.EliminarEstado(pId);
    }
    
    @ExceptionHandler(MethodArgumentNotValidException.class)
    private ResponseEntity<String> handleValidationExceptions(MethodArgumentNotValidException ex){
        StringBuilder errorMessage = new StringBuilder("No se puede ingresar el cliente por: ");
        ex.getBindingResult().getFieldErrors().forEach((error)->{
            String fieldName = error.getField();
            String errorMessageDeatail = error.getDefaultMessage();
            errorMessage.append("\n - ").append(fieldName).append(": ").append(errorMessageDeatail);
        });
        return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(errorMessage.toString());
                
    }
    
    @GetMapping("/Cliente/Listar/{id}")
    public List<Cliente> ObtenerClientesActivo(@PathVariable("id") Integer pId){
        return clienteServicio.ListarActivos(pId);
    }
    
    @GetMapping("/Cliente/ListarCedula/{ci}")
    public Cliente ObtenerClientePorCedula(@PathVariable("ci") String pId){
        return clienteServicio.ListarPorCedula(pId);
    }
    
    @GetMapping("/Cliente/ActualizarEstado/{ci}")
    public RespuestaApi ActualizarEstadoCliente(@RequestBody String pCedula){
        return clienteServicio.ActualizarEstado(pCedula);
    }
    
    
    @PostMapping("/Cliente/Registrar")
    public RespuestaApi RegistrarEstado(@RequestBody IngresoCliente pIngresoCliente) {
            return clienteServicio.Registrar(pIngresoCliente);        
    }
    
    
    
}
