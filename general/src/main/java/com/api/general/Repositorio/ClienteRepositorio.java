/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.api.general.Repositorio;
import com.api.general.modelo.Cliente;
import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

/**
 *
 * @author pc
 */
@Repository
public interface ClienteRepositorio extends JpaRepository<Cliente, Integer> {
    @Query(value = "Select cli.* from TCliente cli where cli.CLI_Id = ?1", nativeQuery = true)
    Cliente ConsultaClienteId(@Param("Id") Integer pId);
    
    @Query(value = "Select cli.* from TCliente cli where cli.CLI_Estado = ?1", nativeQuery = true)
        List<Cliente> ConsultaClientePorEstado(@Param("estado") Integer activo);
    
    @Query(value = "Select cli.* from TCliente cli where cli.CLI_Identificacion = ?1", nativeQuery = true)
        Cliente ConsultaClientePorCedula(@Param("con") String pId);
}