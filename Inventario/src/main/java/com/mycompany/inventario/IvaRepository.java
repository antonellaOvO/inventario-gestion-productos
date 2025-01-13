/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Interface.java to edit this template
 */
package com.mycompany.inventario;

import java.util.List;
import org.springframework.data.jpa.repository.JpaRepository;

/**
 *
 * @author anto_
 */
public interface IvaRepository extends JpaRepository<Iva, Integer> {
    // Método para obtener ivas por idCategoria
    List<Iva> findByIdCategoria(int idCategoria);
}
