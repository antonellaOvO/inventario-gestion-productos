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
@RequestMapping("/api/roles")
public class RolController {
    
    @Autowired
    private RolRepository rolRepository;
    
    @GetMapping
    public List<Rol> getAllRols() {
        return rolRepository.findAll();
    }
    
    
    @GetMapping("/{id}")
    public Rol obtenerRol(@PathVariable int id) {
        return rolRepository.findById(id).orElse(null);
    }

    @PostMapping
    public Rol crearRol(@RequestBody Rol rol) {
        return rolRepository.save(rol);
    }
    
    
    @PutMapping("/{id}")
    public Rol updateRol(@PathVariable int id, @RequestBody Rol rol) {
        rol.setIdRol(id);
        return rolRepository.save(rol);
    }
    
    
    @DeleteMapping("/eliminar/{id}")
    public ResponseEntity<Void> deleteRol(@PathVariable int id) {
        if (rolRepository.existsById(id)) {
            rolRepository.deleteById(id);
            return ResponseEntity.noContent().build();
        } else {
            return ResponseEntity.notFound().build();
        }

    
    }
}
