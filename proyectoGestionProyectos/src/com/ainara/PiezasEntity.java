package com.ainara;

import javax.persistence.*;
import java.io.Serializable;
import java.util.Objects;

@Entity
@Table(name = "piezas", schema = "gestionProyectos")
public class PiezasEntity implements Serializable {
    private int idPieza;
    private String nombre;
    private double precio;
    private String descripcion;

    public PiezasEntity(int idPieza) {
        this.idPieza = idPieza;
    }

    public PiezasEntity() {
    }

    public PiezasEntity(int idPieza, String nombre, double precio, String descripcion) {
        this.idPieza = idPieza;
        this.nombre = nombre;
        this.precio = precio;
        this.descripcion = descripcion;
    }

    public PiezasEntity(String nombre, double precio, String descripcion) {
        this.nombre = nombre;
        this.precio = precio;
        this.descripcion = descripcion;
    }

    @Id
    @Column(name = "id_pieza", nullable = false)
    public int getIdPieza() {
        return idPieza;
    }

    public void setIdPieza(int   idPieza) {
        this.idPieza = idPieza;
    }

    @Basic
    @Column(name = "nombre", nullable = false, length = 40)
    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    @Basic
    @Column(name = "precio", nullable = false, precision = 0)
    public double getPrecio() {
        return precio;
    }

    public void setPrecio(double precio) {
        this.precio = precio;
    }

    @Basic
    @Column(name = "descripcion", nullable = false, length = 100)
    public String getDescripcion() {
        return descripcion;
    }

    public void setDescripcion(String descripcion) {
        this.descripcion = descripcion;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        PiezasEntity that = (PiezasEntity) o;
        return Double.compare(that.precio, precio) == 0 && Objects.equals(idPieza, that.idPieza) && Objects.equals(nombre, that.nombre) && Objects.equals(descripcion, that.descripcion);
    }

    @Override
    public int hashCode() {
        return Objects.hash(idPieza, nombre, precio, descripcion);
    }

    @Override
    public String toString() {
        return String.valueOf(idPieza);
    }
}
