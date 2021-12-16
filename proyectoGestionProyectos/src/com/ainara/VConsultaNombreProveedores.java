package com.ainara;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.beans.PropertyChangeEvent;
import java.beans.PropertyChangeListener;
import java.util.ArrayList;

public class VConsultaNombreProveedores {
    private JPanel VPanelConsNombProveedores;
    private JTextField et_nom_prov;
    private JButton buscarProveedorButton;
    private JComboBox<ProveedoresEntity> comboBox_nom_prov;
    private JTextArea textArea_prov;

    private ArrayList<ProveedoresEntity> listaProveedores = new ArrayList<>();
    private ArrayList<ProveedoresEntity> listaProveedoresNom = new ArrayList<>();

    private ProveedoresEntity proveedor;

    public VConsultaNombreProveedores() {
        buscarProveedorButton.addActionListener(e -> {
            String nombre = et_nom_prov.getText();
            buscarPorNombre(nombre);
            cargarCombobox();
        });
        comboBox_nom_prov.addActionListener(e -> {
            proveedor = (ProveedoresEntity) comboBox_nom_prov.getSelectedItem();
            if (proveedor != null) {
                String proveedorString =
                        "\nCOD:                    " + proveedor.getIdProv() + "\n" +
                                "\nNOMBRE:               " + proveedor.getNombre() + "\n" +
                                "\nAPELLIDOS:           " + proveedor.getApellidos() + "\n" +
                                "\nDIRECCIÓN:          " + proveedor.getDir();

                textArea_prov.setText(proveedorString);
            }
        });

        et_nom_prov.addActionListener(e -> {
            comboBox_nom_prov.removeAllItems();
            textArea_prov.setText("");
            listaProveedoresNom.clear();
        });
    }

    public void buscarPorNombre(String nombre) {
        listaProveedores = new Cargas().listaProveedores();
        for (int i = 0; i < listaProveedores.size(); i++) {
            if (listaProveedores.get(i).getNombre().equalsIgnoreCase(nombre)) {
                listaProveedoresNom.add(listaProveedores.get(i));
            }
        }
        byte[] nom_bytes = nombre.getBytes();
        byte[] nom_prov;
        if (listaProveedoresNom == null) {
            for (int i = 0; i < listaProveedores.size(); i++) {
                nom_prov = listaProveedores.get(i).getNombre().getBytes();
                for (int j = 0; j < nom_prov.length; j++) {
                    if (nom_bytes[j] == nom_prov[j]) {
                        listaProveedoresNom.add(listaProveedores.get(i));
                    }
                }
            }
        }
    }

    public void cargarCombobox() {
        DefaultComboBoxModel<ProveedoresEntity> proveedoresModel = new DefaultComboBoxModel<>();

        for (ProveedoresEntity proveedor : listaProveedoresNom) {
            proveedoresModel.addElement(proveedor);
        }
        comboBox_nom_prov.setModel(proveedoresModel);
    }

    public JPanel getVPanelConsNombProveedores() {
        return VPanelConsNombProveedores;
    }
}
