/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.mycompany.inventario;

import java.time.LocalDateTime;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

/**
 *
 * @author anto_
 */

@Entity
@Table(name = "Subcategorias")
public class Subcategoria {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id_subcategoria")
    private int idSubcategoria;

    @ManyToOne
    @JoinColumn(name = "id_categoria")
    private Categoria categoria;
    
   
    
    @Column(name = "nombre_subcategoria")
    private String nombreSubcategoria;
    
    @Column(name = "fecha_creacion", insertable = false)
    private LocalDateTime fechaCreacion;
    
    //Constructor:

    public Subcategoria() {
    }
    
    
    //GETTERS:

    public int getIdSubcategoria() {
        return idSubcategoria;
    }

    public Categoria getCategoria() {
        return categoria;
    }


    public String getNombreSubcategoria() {
        return nombreSubcategoria;
    }

    public LocalDateTime getFechaCreacion() {
        return fechaCreacion;
    }
    
    //SETTERS:

    public void setIdSubcategoria(int idSubcategoria) {
        this.idSubcategoria = idSubcategoria;
    }

    public void setCategoria(Categoria categoria) {
        this.categoria = categoria;
    }


    public void setNombreSubcategoria(String nombreSubcategoria) {
        this.nombreSubcategoria = nombreSubcategoria;
    }

    public void setFechaCreacion(LocalDateTime fechaCreacion) {
        this.fechaCreacion = fechaCreacion;
    }
    
    
}
