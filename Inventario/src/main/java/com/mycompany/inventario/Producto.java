/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.mycompany.inventario;

import com.fasterxml.jackson.annotation.JsonFormat;
import java.math.BigDecimal;
import java.time.LocalDate;
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
@Table(name = "Productos")
public class Producto {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id_producto")
    private int idProducto;

    @ManyToOne
    @JoinColumn(name = "id_subcategoria")
    private Subcategoria subcategoria;

    @ManyToOne
    @JoinColumn(name = "id_iva")
    private Iva iva;

    @ManyToOne
    @JoinColumn(name = "id_proveedor")
    private Proveedor proveedor;
    
    /*@Column(name = "id_subcategoria")
    private int idSubcategoria;*/
    
    /*@Column(name = "id_iva")
    private int idIva;*/
    
    /*@Column(name = "id_proveedor")
    private int idProveedor;*/

    @Column(name = "nombre_producto")
    private String nombreProducto;

    @Column(name = "descripcion")
    private String descripcion;

    @Column(name = "stock")
    private Integer stock;

    @Column(name = "precio_unidad_sin_iva")
    private BigDecimal precioUnidadSinIva;

    @Column(name = "precio_unidad_con_iva")
    private BigDecimal precioUnidadConIva;

    @Column(name = "fecha_alta", insertable = false) // insertable = false le indica a JPA que este campo no debe ser insertado ni actualizado en la base de datos desde el lado de la aplicación 
    private LocalDateTime fechaAlta;

    @JsonFormat(pattern = "yyyy-MM-dd")
    @Column(name = "fecha_adquisicion")
    private LocalDate fechaAdquisicion;

    @JsonFormat(pattern = "yyyy-MM-dd")
    @Column(name = "fecha_caducidad")
    private LocalDate fechaCaducidad;

    @Column(name = "fecha_actualizacion", insertable = false)
    private LocalDateTime fechaActualizacion;

    @Column(name = "fecha_baja", insertable = false)
    private LocalDateTime fechaBaja;

    @Column(name = "precio_total_sin_iva")
    private BigDecimal precioTotalSinIva;

    @Column(name = "precio_total_con_iva")
    private BigDecimal precioTotalConIva;

    
    @Column(name = "disponibilidad", insertable = false)
    private Boolean disponibilidad;

    @Column(name = "activo")
    private Boolean activo;
    
    //Constructor:

    public Producto() {
    }
    
    //GETTERS:

    public int getIdProducto() {
        return idProducto;
    }

    public Subcategoria getSubcategoria() {
        return subcategoria;
    }

    public Iva getIva() {
        return iva;
    }

    public Proveedor getProveedor() {
        return proveedor;
    }


    public String getNombreProducto() {
        return nombreProducto;
    }

    public String getDescripcion() {
        return descripcion;
    }

    public Integer getStock() {
        return stock;
    }

    public BigDecimal getPrecioUnidadSinIva() {
        return precioUnidadSinIva;
    }

    public BigDecimal getPrecioUnidadConIva() {
        return precioUnidadConIva;
    }

    public LocalDateTime getFechaAlta() {
        return fechaAlta;
    }

    public LocalDate getFechaAdquisicion() {
        return fechaAdquisicion;
    }

    public LocalDate getFechaCaducidad() {
        return fechaCaducidad;
    }

    public LocalDateTime getFechaActualizacion() {
        return fechaActualizacion;
    }

    public LocalDateTime getFechaBaja() {
        return fechaBaja;
    }

    public BigDecimal getPrecioTotalSinIva() {
        return precioTotalSinIva;
    }

    public BigDecimal getPrecioTotalConIva() {
        return precioTotalConIva;
    }

    public Boolean getDisponibilidad() {
        return disponibilidad;
    }

    public Boolean getActivo() {
        return activo;
    }
    
    
    //SETTERS:

    public void setIdProducto(int idProducto) {
        this.idProducto = idProducto;
    }

    public void setSubcategoria(Subcategoria subcategoria) {
        this.subcategoria = subcategoria;
    }

    public void setIva(Iva iva) {
        this.iva = iva;
    }

    public void setProveedor(Proveedor proveedor) {
        this.proveedor = proveedor;
    }



    public void setNombreProducto(String nombreProducto) {
        this.nombreProducto = nombreProducto;
    }

    public void setDescripcion(String descripcion) {
        this.descripcion = descripcion;
    }

    public void setStock(Integer stock) {
        this.stock = stock;
    }

    public void setPrecioUnidadSinIva(BigDecimal precioUnidadSinIva) {
        this.precioUnidadSinIva = precioUnidadSinIva;
    }

    public void setPrecioUnidadConIva(BigDecimal precioUnidadConIva) {
        this.precioUnidadConIva = precioUnidadConIva;
    }

    public void setFechaAlta(LocalDateTime fechaAlta) {
        this.fechaAlta = fechaAlta;
    }

    public void setFechaAdquisicion(LocalDate fechaAdquisicion) {
        this.fechaAdquisicion = fechaAdquisicion;
    }

    public void setFechaCaducidad(LocalDate fechaCaducidad) {
        this.fechaCaducidad = fechaCaducidad;
    }

    public void setFechaActualizacion(LocalDateTime fechaActualizacion) {
        this.fechaActualizacion = fechaActualizacion;
    }

    public void setFechaBaja(LocalDateTime fechaBaja) {
        this.fechaBaja = fechaBaja;
    }

    public void setPrecioTotalSinIva(BigDecimal precioTotalSinIva) {
        this.precioTotalSinIva = precioTotalSinIva;
    }

    public void setPrecioTotalConIva(BigDecimal precioTotalConIva) {
        this.precioTotalConIva = precioTotalConIva;
    }

    public void setDisponibilidad(Boolean disponibilidad) {
        this.disponibilidad = disponibilidad;
    }

    public void setActivo(Boolean activo) {
        this.activo = activo;
    }
    
    
}
