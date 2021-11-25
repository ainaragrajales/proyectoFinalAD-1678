package com.ainara;

import javax.persistence.*;
import java.io.Serializable;
import java.util.Objects;

@Entity
@Table(name = "proveedores", schema = "gestionProyectos", catalog = "")
public class ProveedoresEntity implements Serializable {
    private int idProv;
    private String nombre;
    private String apellidos;
    private String dir;

    @Id
    @Column(name = "id_prov", nullable = false)
    public int getIdProv() {
        return idProv;
    }

    public void setIdProv(int idProv) {
        this.idProv = idProv;
    }

    @Basic
    @Column(name = "nombre", nullable = false, length = 30)
    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    @Basic
    @Column(name = "apellidos", nullable = false, length = 40)
    public String getApellidos() {
        return apellidos;
    }

    public void setApellidos(String apellidos) {
        this.apellidos = apellidos;
    }

    @Basic
    @Column(name = "dir", nullable = false, length = 45)
    public String getDir() {
        return dir;
    }

    public void setDir(String dir) {
        this.dir = dir;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        ProveedoresEntity that = (ProveedoresEntity) o;
        return Objects.equals(idProv, that.idProv) && Objects.equals(nombre, that.nombre) && Objects.equals(apellidos, that.apellidos) && Objects.equals(dir, that.dir);
    }

    @Override
    public int hashCode() {
        return Objects.hash(idProv, nombre, apellidos, dir);
    }
}
