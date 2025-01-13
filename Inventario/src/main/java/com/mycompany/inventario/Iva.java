/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.mycompany.inventario;

import java.math.BigDecimal;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

/**
 *
 * @author anto_
 */

@Entity
@Table(name = "Iva")
public class Iva {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id_iva")
    private Integer idIva;

    @Column(name = "tipo_iva")
    private String tipoIva;

    @Column(name = "incremento")
    private BigDecimal incremento;
    
    @Column(name = "id_categoria")
    private int idCategoria;

    /*@ManyToOne
    @JoinColumn(name = "id_categoria")
    private Categoria categoria;*/
    
    
    //Constructor:
    public Iva() {
    }
    
    //GETTERS:

    public Integer getIdIva() {
        return idIva;
    }

    public String getTipoIva() {
        return tipoIva;
    }

    public BigDecimal getIncremento() {
        return incremento;
    }

    public int getIdCategoria() {
        return idCategoria;
    }

    //SETTERS:

    public void setIdIva(Integer idIva) {
        this.idIva = idIva;
    }

    public void setTipoIva(String tipoIva) {
        this.tipoIva = tipoIva;
    }

    public void setIncremento(BigDecimal incremento) {
        this.incremento = incremento;
    }

    public void setIdCategoria(int idCategoria) {
        this.idCategoria = idCategoria;
    }
    
}
