/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.mycompany.inventario;

import java.util.List;
import java.util.Optional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PatchMapping;
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
@RequestMapping("/api/productos")
public class ProductoController {
    @Autowired
    private ProductoRepository productoRepository;
    
    
    
    @GetMapping
    public List<Producto> getAllProductos() {
        return productoRepository.findAll();
    }
    
    
    @GetMapping("/{id}")
    public Producto obtenerProducto(@PathVariable int id) {
        return productoRepository.findById(id).orElse(null);
    }
    
    
    @PatchMapping("/{id}") //Patch: no es necesario que envíes todos los campos del producto en el frontend
    public ResponseEntity<Producto> actualizarProductoParcial(@PathVariable int id, @RequestBody Producto productoParcial) {
        // Buscar el producto existente en la base de datos
        Optional<Producto> productoExistenteOpt = productoRepository.findById(id);
        
        if (productoExistenteOpt.isPresent()) {
            Producto productoExistente = productoExistenteOpt.get();

            // Actualizar solo los campos que se han recibido
            if (productoParcial.getNombreProducto() != null) {
                productoExistente.setNombreProducto(productoParcial.getNombreProducto());
            }
            if (productoParcial.getDescripcion() != null) {
                productoExistente.setDescripcion(productoParcial.getDescripcion());
            }
            if (productoParcial.getStock() != null) {
                productoExistente.setStock(productoParcial.getStock());
            }
            if (productoParcial.getPrecioUnidadSinIva() != null) {
                productoExistente.setPrecioUnidadSinIva(productoParcial.getPrecioUnidadSinIva());
            }
            if (productoParcial.getPrecioUnidadConIva() != null) {
                productoExistente.setPrecioUnidadConIva(productoParcial.getPrecioUnidadConIva());
            }
            if (productoParcial.getFechaAdquisicion() != null) {
                productoExistente.setFechaAdquisicion(productoParcial.getFechaAdquisicion());
            }
            if (productoParcial.getFechaCaducidad() != null) {
                productoExistente.setFechaCaducidad(productoParcial.getFechaCaducidad());
            }
            if (productoParcial.getActivo() != null) {
                productoExistente.setActivo(productoParcial.getActivo());
            }
            if (productoParcial.getDisponibilidad() != null) {
                productoExistente.setDisponibilidad(productoParcial.getDisponibilidad());
            }
            if (productoParcial.getPrecioTotalSinIva() != null) {
                productoExistente.setPrecioTotalSinIva(productoParcial.getPrecioTotalSinIva());
            }
            if (productoParcial.getPrecioTotalConIva() != null) {
                productoExistente.setPrecioTotalConIva(productoParcial.getPrecioTotalConIva());
            }
            
            // Aquí se puede agregar más lógica si necesitas modificar otros campos opcionales
            
            // Guardar el producto actualizado
            Producto productoActualizado = productoRepository.save(productoExistente);
            return ResponseEntity.ok(productoActualizado);
        } else {
            return ResponseEntity.notFound().build();  // Producto no encontrado
        }
    }


    @PostMapping
    public Producto crearProducto(@RequestBody Producto producto) {
        return productoRepository.save(producto);
    }
    
    
    
    
    @PutMapping("/{id}") //es necesario que envíes todos los campos desde el front
    public ResponseEntity<Producto> updateProducto(@PathVariable int id, @RequestBody Producto producto) {
        if (!productoRepository.existsById(id)) {
        return ResponseEntity.notFound().build();
        }
        producto.setIdProducto(id);
        productoRepository.save(producto);
        return ResponseEntity.ok(producto);
    }
    
    
    @DeleteMapping("/eliminar/{id}")
    public ResponseEntity<Void> deleteProducto(@PathVariable int id) {
        if (productoRepository.existsById(id)) {
            productoRepository.deleteById(id);
            return ResponseEntity.noContent().build();
        } else {
            return ResponseEntity.notFound().build();
        }

    
    }
}
