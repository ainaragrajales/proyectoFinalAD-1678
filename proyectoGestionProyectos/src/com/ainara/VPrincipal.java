package com.ainara;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class VPrincipal {
    private JMenuBar menu_bar;
    private JMenu menu_proveedores;
    private JMenu menu_piezas;
    private JMenu menu_proyectos;
    private JMenu menu_gestion;
    private JMenu menu_ayuda;
    private JMenuItem item_gestion_proveedores;
    private JMenu submenu_consultas_proveedores;
    private JMenuItem subitem_consultaCodigo_proveedores;
    private JMenuItem subitem_consultaNombre_proveedores;
    private JMenuItem subitem_consultaDireccion_proveedores;
    private JMenuItem item_gestion_piezas;
    private JMenu submenu_consultas_piezas;
    private JMenuItem subitem_consultaCodigo_piezas;
    private JMenuItem subitem_consultaNombre_piezas;
    private JMenuItem item_gestion_proyectos;
    private JMenu submenu_consultas_proyectos;
    private JMenuItem subitem_consultaCodigo_proyectos;
    private JMenuItem subitem_consultaNombre_proyectos;
    private JMenuItem subitem_consultaCiudad_proyectos;
    private JMenuItem item_relacionar_gestion;
    private JMenuItem item_suministroProveedores_gestion;
    private JMenuItem item_suministrosPiezas_gestion;
    private JMenuItem item_estadisticas_gestion;


    public VPrincipal() {
        menu_proveedores.addActionListener(e -> {

        });
        menu_piezas.addActionListener(e -> {

        });
        menu_proyectos.addActionListener(e -> {

        });
        menu_gestion.addActionListener(e -> {

        });
        menu_ayuda.addActionListener(e -> {

        });
    }
}
