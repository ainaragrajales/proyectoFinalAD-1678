package com.ainara;

import javax.swing.*;
import java.util.ArrayList;

public class VConsultaNombreProyectos {
    private JPanel VPanelConsNombProyectos;
    private JTextField et_nom_proy;
    private JButton buscarProyectoButton;
    private JComboBox<ProyectosEntity> comboBox_nom_proy;
    private JTextArea textArea_proy;

    private ArrayList<ProyectosEntity> listaProyectos = new ArrayList<>();
    private ArrayList<ProyectosEntity> listaProyectosNom = new ArrayList<>();

    private ProyectosEntity proyecto;

    public VConsultaNombreProyectos() {
        et_nom_proy.addActionListener(e -> {
            comboBox_nom_proy.removeAllItems();
            textArea_proy.setText("");
            listaProyectosNom.clear();
        });
        buscarProyectoButton.addActionListener(e -> {
            String nombre = et_nom_proy.getText();
            buscarPorNombre(nombre);
            cargarCombobox();
        });
        comboBox_nom_proy.addActionListener(e -> {
            proyecto = (ProyectosEntity) comboBox_nom_proy.getSelectedItem();
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
                listaProyectosNom.add(listaProyectos.get(i));
            }
        }
        byte[] nom_bytes = nombre.getBytes();
        byte[] nom_prov;
        if (listaProyectosNom == null) {
            for (int i = 0; i < listaProyectos.size(); i++) {
                nom_prov = listaProyectos.get(i).getNombre().getBytes();
                for (int j = 0; j < nom_prov.length; j++) {
                    if (nom_bytes[j] == nom_prov[j]) {
                        listaProyectosNom.add(listaProyectos.get(i));
                    }
                }
            }
        }
    }

    public void cargarCombobox() {
        DefaultComboBoxModel<ProyectosEntity> proveedoresModel = new DefaultComboBoxModel<>();

        for (ProyectosEntity proveedor : listaProyectosNom) {
            proveedoresModel.addElement(proveedor);
        }
        comboBox_nom_proy.setModel(proveedoresModel);
    }
    public JPanel getVPanelConsNombProyectos() {
        return VPanelConsNombProyectos;
    }
}
