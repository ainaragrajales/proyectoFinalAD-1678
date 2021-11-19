package com.ainara;

import javax.persistence.*;
import java.util.Objects;

@Entity
@Table(name = "proyectos", schema = "gestionProyectos", catalog = "")
public class ProyectosEntity {
    private Object idProyecto;
    private String nombre;
    private String ciudad;

    @Id
    @Column(name = "id_proyecto", nullable = false)
    public Object getIdProyecto() {
        return idProyecto;
    }

    public void setIdProyecto(Object idProyecto) {
        this.idProyecto = idProyecto;
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
    @Column(name = "ciudad", nullable = false, length = 30)
    public String getCiudad() {
        return ciudad;
    }

    public void setCiudad(String ciudad) {
        this.ciudad = ciudad;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        ProyectosEntity that = (ProyectosEntity) o;
        return Objects.equals(idProyecto, that.idProyecto) && Objects.equals(nombre, that.nombre) && Objects.equals(ciudad, that.ciudad);
    }

    @Override
    public int hashCode() {
        return Objects.hash(idProyecto, nombre, ciudad);
    }
}
