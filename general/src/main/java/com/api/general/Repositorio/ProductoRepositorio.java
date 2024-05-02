/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.api.general.Repositorio;

import com.api.general.Modelo.Producto;
import java.util.Date;
import java.util.List;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

/**
 *
 * @author ariel
 */
@Repository
public interface ProductoRepositorio extends JpaRepository<Producto, Integer> {
    @Query(value = "Select pro.* from TProducto pro where pro.PRO_Id = ?1", nativeQuery = true)
    Producto ConsulaProductoId(@Param("Id") Integer pId);
    
    @Query(value = "Select pro.* from TProducto pro where pro.PRO_Estado = 2", nativeQuery = true)
        List<Producto> ConsultaProductoActivo();
        
    
}
