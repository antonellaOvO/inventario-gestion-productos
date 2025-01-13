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
import javax.persistence.Table;

/**
 *
 * @author anto_
 */

@Entity
@Table(name = "Empleados")
public class Empleado {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id_empleado")
    private Integer idEmpleado;

    /*@ManyToOne
    @JoinColumn(name = "id_rol")
    private Rol rol;*/
    
    @Column(name = "id_rol")
    private int idRol;

    @Column(name = "contrasenya")
    private String contrasenya;

    @Column(name = "email")
    private String email;

    @Column(name = "fecha_alta", insertable = false)
    private LocalDateTime fechaAlta;

    @Column(name = "fecha_baja", insertable = false)
    private LocalDateTime fechaBaja;

    @Column(name = "activo", insertable = false)
    private Boolean activo;
    
    //Constructor:

    public Empleado() {
    }
    
    
    //GETTERS:

    public Integer getIdEmpleado() {
        return idEmpleado;
    }

    public int getIdRol() {
        return idRol;
    }

    public String getContrasenya() {
        return contrasenya;
    }

    public String getEmail() {
        return email;
    }

    public LocalDateTime getFechaAlta() {
        return fechaAlta;
    }

    public LocalDateTime getFechaBaja() {
        return fechaBaja;
    }

    public Boolean getActivo() {
        return activo;
    }
    
    
    //SETTERS:

    public void setIdEmpleado(Integer idEmpleado) {
        this.idEmpleado = idEmpleado;
    }

    public void setIdRol(int idRol) {
        this.idRol = idRol;
    }

    public void setContrasenya(String contrasenya) {
        this.contrasenya = contrasenya;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public void setFechaAlta(LocalDateTime fechaAlta) {
        this.fechaAlta = fechaAlta;
    }

    public void setFechaBaja(LocalDateTime fechaBaja) {
        this.fechaBaja = fechaBaja;
    }

    public void setActivo(Boolean activo) {
        this.activo = activo;
    }
    
    
}
