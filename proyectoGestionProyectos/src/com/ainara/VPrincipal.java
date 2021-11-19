package com.ainara;

import javax.swing.*;
import java.awt.*;
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
    private JPanel VPanelPrincipal;


    public VPrincipal() {



        //Botones que llevan a las ventanas de Proveedores
        item_gestion_proveedores.addActionListener(e -> {
            JFrame frame_gestion_proveedores = new JFrame("Gestión de Proveedores");
            frame_gestion_proveedores.setContentPane(new VGestionProveedores().getVPanelGestionProveedores());
            frame_gestion_proveedores.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE); // Cierra la ventana pero no el programa
            frame_gestion_proveedores.setMinimumSize(new Dimension(400, 200)); // Lo ajusto a un tamaño para que se vea bien
            frame_gestion_proveedores.setLocationRelativeTo(null); // Saca la ventana al centro
            frame_gestion_proveedores.pack();
            frame_gestion_proveedores.setVisible(true);
        });

        subitem_consultaCodigo_proveedores.addActionListener(e -> {
            JFrame frame_consCod_proveedores = new JFrame("Consulta de Proveedores por Código");
            frame_consCod_proveedores.setContentPane(new VConsultaCodigoProveedores().getVPanelConsCodProveedores());
            frame_consCod_proveedores.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE); // Cierra la ventana pero no el programa
            frame_consCod_proveedores.setMinimumSize(new Dimension(400, 200)); // Lo ajusto a un tamaño para que se vea bien
            frame_consCod_proveedores.setLocationRelativeTo(null); // Saca la ventana al centro
            frame_consCod_proveedores.pack();
            frame_consCod_proveedores.setVisible(true);
        });

        subitem_consultaNombre_proveedores.addActionListener(e -> {
            JFrame frame_consNom_proveedores = new JFrame("Consulta de Proveedores por Nombre");
            frame_consNom_proveedores.setContentPane(new VConsultaNombreProveedores().getVPanelConsNombProveedores());
            frame_consNom_proveedores.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE); // Cierra la ventana pero no el programa
            frame_consNom_proveedores.setMinimumSize(new Dimension(400, 200)); // Lo ajusto a un tamaño para que se vea bien
            frame_consNom_proveedores.setLocationRelativeTo(null); // Saca la ventana al centro
            frame_consNom_proveedores.pack();
            frame_consNom_proveedores.setVisible(true);
        });

        subitem_consultaDireccion_proveedores.addActionListener(e -> {
            JFrame frame_consDir_proveedores = new JFrame("Consulta de Proveedores por Dirección");
            frame_consDir_proveedores.setContentPane(new VConsultaDireccionProveedores().getVPanelConsDirProveedores());
            frame_consDir_proveedores.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE); // Cierra la ventana pero no el programa
            frame_consDir_proveedores.setMinimumSize(new Dimension(400, 200)); // Lo ajusto a un tamaño para que se vea bien
            frame_consDir_proveedores.setLocationRelativeTo(null); // Saca la ventana al centro
            frame_consDir_proveedores.pack();
            frame_consDir_proveedores.setVisible(true);
        });


        //Botones que llevan a las ventanas de Piezas
        item_gestion_piezas.addActionListener(e -> {

        });

        subitem_consultaCodigo_piezas.addActionListener(e -> {

        });

        subitem_consultaNombre_piezas.addActionListener(e -> {

        });

        //Botones que llevan a las ventanas de Proyectos
        item_gestion_proyectos.addActionListener(e -> {

        });

        subitem_consultaCodigo_proyectos.addActionListener(e -> {

        });

        subitem_consultaNombre_proyectos.addActionListener(e -> {

        });

        subitem_consultaCiudad_proyectos.addActionListener(e -> {

        });

        //Botones que llevan a las ventanas de Gestión Global
        item_relacionar_gestion.addActionListener(e -> {

        });

        item_suministroProveedores_gestion.addActionListener(e -> {

        });

        item_suministrosPiezas_gestion.addActionListener(e -> {

        });

        item_estadisticas_gestion.addActionListener(e -> {
            
        });
    }

    public static void main(String[] args) {
        JFrame frame = new JFrame("Gestión de Proyectos");
        frame.setContentPane(new VPrincipal().VPanelPrincipal);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setMinimumSize(new Dimension(300, 300));
        frame.setLocationRelativeTo(null); // Saca la ventana al centro
        frame.pack();
        frame.setVisible(true);
    }
}
