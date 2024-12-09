/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.mycompany.inventario;

import java.math.BigDecimal;
import java.util.Date;

/**
 *
 * @author anto_
 */
public class ProductoDetallesDTO {
    
    private int idProducto;
    private String nombreProducto;
    private String proveedor;
    private String categoria;
    private String subcategoria;
    private String descripcion;
    private int stock;
    private BigDecimal precioUnidadSinIva;
    private String tipoIva;
    private BigDecimal precioUnidadConIva;
    private Date fechaAdquisicion;
    private Date fechaCaducidad;
    private BigDecimal precioTotalSinIva;
    private BigDecimal precioTotalConIva;
    private Boolean disponibilidad;
    private Boolean activo;
    
    //Constructor:

    public ProductoDetallesDTO(int idProducto, String nombreProducto, String proveedor, String categoria, String subcategoria, String descripcion, int stock, BigDecimal precioUnidadSinIva, String tipoIva, BigDecimal precioUnidadConIva, Date fechaAdquisicion, Date fechaCaducidad, BigDecimal precioTotalSinIva, BigDecimal precioTotalConIva, Boolean disponibilidad, Boolean activo) {
        this.idProducto = idProducto;
        this.nombreProducto = nombreProducto;
        this.proveedor = proveedor;
        this.categoria = categoria;
        this.subcategoria = subcategoria;
        this.descripcion = descripcion;
        this.stock = stock;
        this.precioUnidadSinIva = precioUnidadSinIva;
        this.tipoIva = tipoIva;
        this.precioUnidadConIva = precioUnidadConIva;
        this.fechaAdquisicion = fechaAdquisicion;
        this.fechaCaducidad = fechaCaducidad;
        this.precioTotalSinIva = precioTotalSinIva;
        this.precioTotalConIva = precioTotalConIva;
        this.disponibilidad = disponibilidad;
        this.activo = activo;
    }

    
    public ProductoDetallesDTO() {
    }
    
    
    //GETTERS:

    public int getIdProducto() {
        return idProducto;
    }

    public String getNombreProducto() {
        return nombreProducto;
    }

    public String getProveedor() {
        return proveedor;
    }

    public String getCategoria() {
        return categoria;
    }

    public String getSubcategoria() {
        return subcategoria;
    }

    public String getDescripcion() {
        return descripcion;
    }

    public int getStock() {
        return stock;
    }

    public BigDecimal getPrecioUnidadSinIva() {
        return precioUnidadSinIva;
    }

    public String getTipoIva() {
        return tipoIva;
    }

    public BigDecimal getPrecioUnidadConIva() {
        return precioUnidadConIva;
    }

    public Date getFechaAdquisicion() {
        return fechaAdquisicion;
    }

    public Date getFechaCaducidad() {
        return fechaCaducidad;
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

    public void setNombreProducto(String nombreProducto) {
        this.nombreProducto = nombreProducto;
    }

    public void setProveedor(String proveedor) {
        this.proveedor = proveedor;
    }

    public void setCategoria(String categoria) {
        this.categoria = categoria;
    }

    public void setSubcategoria(String subcategoria) {
        this.subcategoria = subcategoria;
    }

    public void setDescripcion(String descripcion) {
        this.descripcion = descripcion;
    }

    public void setStock(int stock) {
        this.stock = stock;
    }

    public void setPrecioUnidadSinIva(BigDecimal precioUnidadSinIva) {
        this.precioUnidadSinIva = precioUnidadSinIva;
    }

    public void setTipoIva(String tipoIva) {
        this.tipoIva = tipoIva;
    }

    public void setPrecioUnidadConIva(BigDecimal precioUnidadConIva) {
        this.precioUnidadConIva = precioUnidadConIva;
    }

    public void setFechaAdquisicion(Date fechaAdquisicion) {
        this.fechaAdquisicion = fechaAdquisicion;
    }

    public void setFechaCaducidad(Date fechaCaducidad) {
        this.fechaCaducidad = fechaCaducidad;
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
