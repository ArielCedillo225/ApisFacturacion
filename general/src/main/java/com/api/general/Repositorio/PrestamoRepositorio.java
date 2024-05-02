/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.api.general.Repositorio;

import com.api.general.Modelo.Prestamo;
import jakarta.transaction.Transactional;
import java.util.Date;
import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

/**
 *
 * @author pc
 */
@Repository
public interface PrestamoRepositorio extends JpaRepository<Prestamo, Integer> {
    @Query(value = "Select pre.* from TPrestamo pre where pre.PRE_Id = ?1", nativeQuery = true)
    Prestamo ConsultaPrestamoId(@Param("Id") Integer pId);
    
    @Query(value = "Select pre.* from TPrestamo pre where pre.PRE_Estado = 6", nativeQuery = true)
    List<Prestamo> ConsultaEnMora();
    
    @Modifying
    @Transactional
    @Query(value = "Update TPrestamo SET PRE_Estado=6 WHERE ?1>=PRE_FechaDevolucion", nativeQuery = true)
    void ConsultarDevolucion(@Param("FechaDevolucion") Date fechaDevolucion);
    
    /**
     * @Query(value = "Select cli.* from TCliente cli where cli.CLI_Estado = ?1", nativeQuery = true)
        List<Prestamo> ConsultaClientePorEstado(@Param("estado") Integer activo);
    
    @Query(value = "Select cli.* from TCliente cli where cli.CLI_Identificacion = ?1", nativeQuery = true)
        Prestamo ConsultaClientePorCedula(@Param("con") String pId);
     **/
}
