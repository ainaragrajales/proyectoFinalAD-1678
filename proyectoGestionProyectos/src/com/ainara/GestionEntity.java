package com.ainara;

import javax.persistence.*;
import java.io.Serializable;
import java.util.Objects;

@Entity
@Table(name = "gestion", schema = "gestionProyectos")
public class GestionEntity implements Serializable {
    private int idGestion;
    private Double cantidad;
    private ProveedoresEntity proveedoresByCodProv;
    private PiezasEntity piezasByCodPieza;
    private ProyectosEntity proyectosByCodProyecto;

    public GestionEntity() {
    }

    public GestionEntity(int idGestion, Double cantidad, ProveedoresEntity proveedoresByCodProv, PiezasEntity piezasByCodPieza, ProyectosEntity proyectosByCodProyecto) {
        this.idGestion = idGestion;
        this.cantidad = cantidad;
        this.proveedoresByCodProv = proveedoresByCodProv;
        this.piezasByCodPieza = piezasByCodPieza;
        this.proyectosByCodProyecto = proyectosByCodProyecto;
    }

    public GestionEntity(Double cantidad, ProveedoresEntity proveedoresByCodProv, PiezasEntity piezasByCodPieza, ProyectosEntity proyectosByCodProyecto) {
        this.cantidad = cantidad;
        this.proveedoresByCodProv = proveedoresByCodProv;
        this.piezasByCodPieza = piezasByCodPieza;
        this.proyectosByCodProyecto = proyectosByCodProyecto;
    }

    @Id
    @Column(name = "id_gestion", nullable = false)
    public int getIdGestion() {
        return idGestion;
    }

    public void setIdGestion(int idGestion) {
        this.idGestion = idGestion;
    }

    @Basic
    @Column(name = "cantidad", nullable = true, precision = 0)
    public Double getCantidad() {
        return cantidad;
    }

    public void setCantidad(Double cantidad) {
        this.cantidad = cantidad;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        GestionEntity that = (GestionEntity) o;
        return Objects.equals(idGestion, that.idGestion) && Objects.equals(cantidad, that.cantidad);
    }

    @Override
    public int hashCode() {
        return Objects.hash(idGestion, cantidad);
    }

    @ManyToOne
    @JoinColumn(name = "cod_prov", referencedColumnName = "id_prov", nullable = false)
    public ProveedoresEntity getProveedoresByCodProv() {
        return proveedoresByCodProv;
    }

    public void setProveedoresByCodProv(ProveedoresEntity proveedoresByCodProv) {
        this.proveedoresByCodProv = proveedoresByCodProv;
    }

    @ManyToOne
    @JoinColumn(name = "cod_pieza", referencedColumnName = "id_pieza", nullable = false)
    public PiezasEntity getPiezasByCodPieza() {
        return piezasByCodPieza;
    }

    public void setPiezasByCodPieza(PiezasEntity piezasByCodPieza) {
        this.piezasByCodPieza = piezasByCodPieza;
    }

    @ManyToOne
    @JoinColumn(name = "cod_proyecto", referencedColumnName = "id_proyecto", nullable = false)
    public ProyectosEntity getProyectosByCodProyecto() {
        return proyectosByCodProyecto;
    }

    public void setProyectosByCodProyecto(ProyectosEntity proyectosByCodProyecto) {
        this.proyectosByCodProyecto = proyectosByCodProyecto;
    }
}
