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
@RequestMapping("/api/ivas")
public class IvaController {
    @Autowired
    private IvaRepository ivaRepository;
    
    @GetMapping
    public List<Iva> getAllIvas() {
        return ivaRepository.findAll();
    }
    
    
    @GetMapping("/{id}")
    public Iva obtenerIva(@PathVariable int id) {
        return ivaRepository.findById(id).orElse(null);
    }
    
    @GetMapping("/categoria/{idCategoria}")
    public List<Iva> obtenerIvaPorCategoria(@PathVariable int idCategoria) {
        return ivaRepository.findByIdCategoria(idCategoria);
    }

    @PostMapping
    public Iva crearIva(@RequestBody Iva iva) {
        return ivaRepository.save(iva);
    }
    
    
    @PutMapping("/{id}")
    public Iva updateIva(@PathVariable int id, @RequestBody Iva iva) {
        iva.setIdIva(id);
        return ivaRepository.save(iva);
    }
    
    
    @DeleteMapping("/eliminar/{id}")
    public ResponseEntity<Void> deleteIva(@PathVariable int id) {
        if (ivaRepository.existsById(id)) {
            ivaRepository.deleteById(id);
            return ResponseEntity.noContent().build();
        } else {
            return ResponseEntity.notFound().build();
        }

    
    }
}
