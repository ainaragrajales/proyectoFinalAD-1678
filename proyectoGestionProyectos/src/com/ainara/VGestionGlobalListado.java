package com.ainara;

import javax.swing.*;
import java.awt.event.ComponentAdapter;
import java.awt.event.ComponentEvent;
import java.util.ArrayList;

public class VGestionGlobalListado {
    private JPanel VPanelGestionGlobalListado;
    private JList<GestionEntity> list_gest;
    private JTextField et_nom_prov;
    private JTextField et_ape_prov;
    private JTextField et_nom_pieza;
    private JTextField et_desc_pieza;
    private JTextField et_nom_proy;
    private JTextField et_ciud_proy;
    private JTextField et_cant_gest;
    private JTextField et_id_gest;

    private ArrayList<GestionEntity> listaGestiones = new ArrayList<>();

    private GestionEntity gestion;
    private ProveedoresEntity proveedor;
    private PiezasEntity pieza;
    private ProyectosEntity proyecto;

    public VGestionGlobalListado() {

        listaGestiones = new Cargas().listaGestiones();

        Cargar_JList();


        gestion = list_gest.getSelectedValue();

        if (gestion != null){
            proveedor = gestion.getProveedoresByCodProv();
            pieza = gestion.getPiezasByCodPieza();
            proyecto = gestion.getProyectosByCodProyecto();
            et_id_gest.setText(String.valueOf(gestion.getIdGestion()));
            et_nom_prov.setText(proveedor.getNombre());
            et_ape_prov.setText(proveedor.getApellidos());
            et_nom_pieza.setText(pieza.getNombre());
            et_desc_pieza.setText(pieza.getDescripcion());
            et_nom_proy.setText(proyecto.getNombre());
            et_ciud_proy.setText(proyecto.getCiudad());
            et_cant_gest.setText(String.valueOf(gestion.getCantidad()));

        }

    }

    private void Cargar_JList() {
        DefaultListModel<GestionEntity> model = new DefaultListModel<>();
        for (GestionEntity gestion : listaGestiones) {
            model.addElement(gestion);
        }
        list_gest.setModel(model);
    }


    public JPanel getVPanelGestionGlobalListado() {
        return VPanelGestionGlobalListado;
    }
}
