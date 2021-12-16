package com.ainara;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;

public class VGestionProyectos {
    private JPanel VPanelGestionProyectos;
    private JTabbedPane panel_proyectos;
    private JPanel pestana_gestionProyectos;
    private JTextField et_cod_proyecto;
    private JTextField et_nom_proyecto;
    private JTextField et_ciudad_proyecto;
    private JButton b_limpiar_proyecto;
    private JButton b_insertar_proyecto;
    private JButton b_modificar_proyecto;
    private JButton b_eliminar_proyecto;
    private JPanel pestana_listadoProyectos;
    private JTextField et_codList_proyecto;
    private JTextField et_nomList_proyecto;
    private JTextField et_ciudList_proyecto;
    private JButton b_first_proyecto;
    private JButton b_prev_proyecto;
    private JButton b_next_proyecto;
    private JButton b_last_proyecto;
    private JButton b_ejecutarConsulta_proyecto;
    private JButton b_baja_proyecto;

    private ArrayList<ProyectosEntity> listaProyectos = new ArrayList<>();

    private ProyectosEntity proyecto;

    private int indice = 0;

    public VGestionProyectos() {
        CargarLista();

        //Pestaña Gestión Proyectos
        b_limpiar_proyecto.addActionListener(e -> {
            et_cod_proyecto.setText("");
            et_nom_proyecto.setText("");
            et_ciudad_proyecto.setText("");
        });
        b_insertar_proyecto.addActionListener(e -> {
            if (!ComprobarCamposVacios()){
                JOptionPane.showMessageDialog(null, CamposVacios(), "Campos vacios", JOptionPane.WARNING_MESSAGE);
            } else {
                proyecto = new ProyectosEntity(et_nom_proyecto.getText(), et_ciudad_proyecto.getText());
                listaProyectos.add(proyecto);
                new Cargas().insertarProyecto(proyecto);
                CargarLista();
            }
        });
        b_modificar_proyecto.addActionListener(e -> {
            if (!ComprobarCamposVacios()){
                JOptionPane.showMessageDialog(null, CamposVacios(), "Campos vacios", JOptionPane.WARNING_MESSAGE);
            } else {
                int cod_proy = Integer.parseInt(et_cod_proyecto.getText());
                proyecto = new ProyectosEntity(cod_proy, et_nom_proyecto.getText(), et_ciudad_proyecto.getText());
                listaProyectos.add(proyecto);
                new Cargas().modificarProyecto(proyecto);
                CargarLista();
            }
        });
        b_eliminar_proyecto.addActionListener(e -> {
            int opcion = JOptionPane.showConfirmDialog(null, "Seguro que lo quieres eliminar?", "Eliminar", JOptionPane.YES_NO_OPTION);

            if (opcion == JOptionPane.YES_OPTION){
                int cod_prov = Integer.parseInt(et_cod_proyecto.getText());
                proyecto = new ProyectosEntity(cod_prov, et_nom_proyecto.getText(), et_ciudad_proyecto.getText());
                listaProyectos.remove(proyecto);
                new Cargas().eliminarProyecto(cod_prov);
                CargarLista();
                et_cod_proyecto.setText("");
                et_nom_proyecto.setText("");
                et_ciudad_proyecto.setText("");
            }
        });

        //Pestaña Listado Proyectos
        et_codList_proyecto.setEnabled(false);
        et_nomList_proyecto.setEnabled(false);
        et_ciudList_proyecto.setEnabled(false);

        b_ejecutarConsulta_proyecto.addActionListener(e -> {
            et_codList_proyecto.setText(String.valueOf(listaProyectos.get(0).getIdProyecto()));
            et_nomList_proyecto.setText(listaProyectos.get(0).getNombre());
            et_ciudList_proyecto.setText(listaProyectos.get(0).getCiudad());
            indice = 0;
        });
        b_first_proyecto.addActionListener(e -> {
            proyecto = listaProyectos.get(0);
            et_codList_proyecto.setText(String.valueOf(proyecto.getIdProyecto()));
            et_nomList_proyecto.setText(proyecto.getNombre());
            et_ciudList_proyecto.setText(proyecto.getCiudad());
            indice = 0;
        });
        b_prev_proyecto.addActionListener(e -> {
            int total = listaProyectos.size() - 1;
            if (indice > 0 && indice <= total) {
                int prev_indice = indice - 1;
                ProyectosEntity proyNuevo = listaProyectos.get(prev_indice);

                et_codList_proyecto.setText(String.valueOf(proyNuevo.getIdProyecto()));
                et_nomList_proyecto.setText(proyNuevo.getNombre());
                et_ciudList_proyecto.setText(proyNuevo.getCiudad());
                indice = prev_indice;
            }
        });
        b_next_proyecto.addActionListener(e -> {
            int total = listaProyectos.size() - 1;
            if (indice >= 0 && indice < total) {
                int next_indice = indice - 1;
                ProyectosEntity proyNuevo = listaProyectos.get(next_indice);

                et_codList_proyecto.setText(String.valueOf(proyNuevo.getIdProyecto()));
                et_nomList_proyecto.setText(proyNuevo.getNombre());
                et_ciudList_proyecto.setText(proyNuevo.getCiudad());
                indice = next_indice;
            }
        });
        b_last_proyecto.addActionListener(e -> {
            int total = listaProyectos.size();
            proyecto = listaProyectos.get(total-1);
            et_codList_proyecto.setText(String.valueOf(proyecto.getIdProyecto()));
            et_nomList_proyecto.setText(proyecto.getNombre());
            et_ciudList_proyecto.setText(proyecto.getCiudad());
            indice = total-1;
        });
        b_baja_proyecto.addActionListener(e -> {
            int opcion = JOptionPane.showConfirmDialog(null, "Seguro que lo quieres eliminar?", "Eliminar", JOptionPane.YES_NO_OPTION);

            if (opcion == JOptionPane.YES_OPTION){
                int cod_prov = Integer.parseInt(et_codList_proyecto.getText());
                proyecto = new ProyectosEntity(cod_prov, et_nomList_proyecto.getText(), et_ciudList_proyecto.getText());
                listaProyectos.remove(proyecto);
                new Cargas().eliminarProyecto(cod_prov);
                CargarLista();
                et_codList_proyecto.setText("");
                et_nomList_proyecto.setText("");
                et_ciudList_proyecto.setText("");
            }
        });
    }

    public void CargarLista(){
        listaProyectos = new Cargas().listaProyectos();

    }

    private boolean ComprobarCamposVacios(){
        boolean hayDato = true;
        ArrayList<JTextField> campos = new ArrayList<>();
        campos.add(et_cod_proyecto);
        campos.add(et_nom_proyecto);
        campos.add(et_ciudad_proyecto);

        for (JTextField campo : campos) {
            if (campo.getText().equalsIgnoreCase("")) {
                hayDato = false;
            }
        }
        return hayDato;
    }

    private String CamposVacios(){
        String vacios = "Los siguientes campos están vacios:\n";

        if (et_cod_proyecto.getText().equalsIgnoreCase("")){
            vacios += "     Cod proyecto\n";
        }
        if (et_nom_proyecto.getText().equalsIgnoreCase("")){
            vacios += "     Nombre proyecto\n";
        }
        if (et_ciudad_proyecto.getText().equalsIgnoreCase("")){
            vacios += "     Ciudad proyecto\n";
        }

        vacios += "\nIntroduce los datos de los campos anteriores\n";
        return vacios;
    }

    public JPanel getVPanelGestionProyectos() {
        return VPanelGestionProyectos;
    }
}
