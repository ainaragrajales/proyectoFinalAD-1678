package com.ainara;

import javax.swing.*;
import java.util.ArrayList;

public class VConsultaCiudadProyectos {
    private JPanel VPanelConsCiudProyectos;
    private JTextField et_ciud_proy;
    private JButton buscarProyectoButton;
    private JComboBox<ProyectosEntity> comboBox_ciud_proy;
    private JTextArea textArea_proy;

    private ArrayList<ProyectosEntity> listaProyectos = new ArrayList<>();
    private ArrayList<ProyectosEntity> listaProyectosCiud = new ArrayList<>();

    private ProyectosEntity proyecto;

    public VConsultaCiudadProyectos() {
        et_ciud_proy.addActionListener(e -> {
            comboBox_ciud_proy.removeAllItems();
            textArea_proy.setText("");
            listaProyectosCiud.clear();
        });
        buscarProyectoButton.addActionListener(e -> {
            String ciudad = et_ciud_proy.getText();
            buscarPorNombre(ciudad);
            cargarCombobox();
        });
        comboBox_ciud_proy.addActionListener(e -> {
            proyecto = (ProyectosEntity) comboBox_ciud_proy.getSelectedItem();
            if (proyecto != null){
                String proveedorString =
                        "\nCOD:                    " + proyecto.getIdProyecto() + "\n" +
                                "\nNOMBRE:               " + proyecto.getNombre() + "\n" +
                                "\nCIUDAD:           " + proyecto.getCiudad() + "\n";

                textArea_proy.setText(proveedorString);
            }
        });
    }

    public void buscarPorNombre(String nombre) {
        listaProyectos = new Cargas().listaProyectos();
        for (int i = 0; i < listaProyectos.size(); i++) {
            if (listaProyectos.get(i).getNombre().equalsIgnoreCase(nombre)) {
                listaProyectosCiud.add(listaProyectos.get(i));
            }
        }
        byte[] nom_bytes = nombre.getBytes();
        byte[] nom_prov;
        if (listaProyectosCiud == null) {
            for (int i = 0; i < listaProyectos.size(); i++) {
                nom_prov = listaProyectos.get(i).getNombre().getBytes();
                for (int j = 0; j < nom_prov.length; j++) {
                    if (nom_bytes[j] == nom_prov[j]) {
                        listaProyectosCiud.add(listaProyectos.get(i));
                    }
                }
            }
        }
    }

    public void cargarCombobox() {
        DefaultComboBoxModel<ProyectosEntity> proveedoresModel = new DefaultComboBoxModel<>();

        for (ProyectosEntity proveedor : listaProyectosCiud) {
            proveedoresModel.addElement(proveedor);
        }
        comboBox_ciud_proy.setModel(proveedoresModel);
    }


    public JPanel getVPanelConsCiudProyectos() {
        return VPanelConsCiudProyectos;
    }
}
