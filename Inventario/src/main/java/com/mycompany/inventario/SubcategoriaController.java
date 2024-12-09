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
@RequestMapping("/api/subcategorias")
public class SubcategoriaController {
    
    @Autowired
    private SubcategoriaRepository subcategoriaRepository;
    
    @GetMapping
    public List<Subcategoria> getAllSubcategorias() {
        return subcategoriaRepository.findAll();
    }
    
    
    @GetMapping("/{id}") 
    public Subcategoria obtenerSubcategoria(@PathVariable int id) {
        return subcategoriaRepository.findById(id).orElse(null);
    }
    
    /*@GetMapping("/categoria/{idCategoria}")
    public List<Subcategoria> obtenerSubcategoriasPorCategoria(@PathVariable int idCategoria) {
        return subcategoriaRepository.findByIdCategoria(idCategoria);
    }*/


    @PostMapping
    public Subcategoria crearSubcategoria(@RequestBody Subcategoria subcategoria) {
        return subcategoriaRepository.save(subcategoria);
    }
    
    
    @PutMapping("/{id}")
    public Subcategoria updateSubcategoria(@PathVariable int id, @RequestBody Subcategoria subcategoria) {
        subcategoria.setIdSubcategoria(id);
        return subcategoriaRepository.save(subcategoria);
    }
    
    
    @DeleteMapping("/eliminar/{id}")
    public ResponseEntity<Void> deleteSubcategoria(@PathVariable int id) {
        if (subcategoriaRepository.existsById(id)) {
            subcategoriaRepository.deleteById(id);
            return ResponseEntity.noContent().build();
        } else {
            return ResponseEntity.notFound().build();
        }

    
    }
}
