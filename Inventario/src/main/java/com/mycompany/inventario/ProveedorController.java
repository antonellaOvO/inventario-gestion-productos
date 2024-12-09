/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.mycompany.inventario;

import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 *
 * @author anto_
 */

@RestController
@RequestMapping("/api/proveedores")
public class ProveedorController {
     @Autowired
    private ProveedorRepository proveedorRepository;
    
    @GetMapping
    public List<Proveedor> getAllProveedors() {
        return proveedorRepository.findAll();
    }
    
    
    @GetMapping("/{id}")
    public Proveedor obtenerProveedor(@PathVariable int id) {
        return proveedorRepository.findById(id).orElse(null);
    }

    @PostMapping
    public Proveedor crearProveedor(@RequestBody Proveedor proveedor) {
        return proveedorRepository.save(proveedor);
    }
    
    
    @PutMapping("/{id}")
    public Proveedor updateProveedor(@PathVariable int id, @RequestBody Proveedor proveedor) {
        proveedor.setIdProveedor(id);
        return proveedorRepository.save(proveedor);
    }
    
    
    @DeleteMapping("/eliminar/{id}")
    public ResponseEntity<Void> deleteProveedor(@PathVariable int id) {
        if (proveedorRepository.existsById(id)) {
            proveedorRepository.deleteById(id);
            return ResponseEntity.noContent().build();
        } else {
            return ResponseEntity.notFound().build();
        }

    
    }
}
