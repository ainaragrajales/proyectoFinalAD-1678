package com.ainara;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;

public class VConsultaNombrePiezas {
    private JPanel VPanelConsNombPiezas;
    private JTextField et_nom_pieza;
    private JButton buscarPiezaButton;
    private JComboBox<PiezasEntity> comboBox_nom_pieza;
    private JTextArea textArea_pieza;

    private ArrayList<PiezasEntity> listaPiezas = new ArrayList<>();
    private ArrayList<PiezasEntity> listaPiezasNom = new ArrayList<>();

    private PiezasEntity pieza;

    public VConsultaNombrePiezas() {
        et_nom_pieza.addActionListener(e -> {
            comboBox_nom_pieza.removeAllItems();
            textArea_pieza.setText("");
            listaPiezasNom.clear();
        });
        buscarPiezaButton.addActionListener(e -> {
            String nombre = et_nom_pieza.getText();
            buscarPorCod(nombre);
            cargarCombobox();
        });
        comboBox_nom_pieza.addActionListener(e -> {
            pieza = (PiezasEntity) comboBox_nom_pieza.getSelectedItem();
            if (pieza != null){
                String piezaString =
                        "\nCOD:                    " + pieza.getIdPieza() + "\n" +
                                "\nNOMBRE:               " + pieza.getNombre() + "\n" +
                                "\nPRECIO:           " + pieza.getPrecio() + "\n" +
                                "\nDESCRIPCION:               " + pieza.getDescripcion() + "\n";
                textArea_pieza.setText(piezaString);
            }
        });
    }

    public void buscarPorCod(String nombre){
        listaPiezas = new Cargas().listaPiezas();

        for (int i = 0; i < listaPiezas.size(); i++) {
            if (listaPiezas.get(i).getNombre().equalsIgnoreCase(nombre)){
                listaPiezasNom.add(listaPiezas.get(i));
            }
        }
        byte[] nom_bytes = nombre.getBytes();
        byte[] nom_pieza;
        if (listaPiezasNom == null){
            for (int i = 0; i < listaPiezas.size(); i++) {
                nom_pieza = String.valueOf(listaPiezas.get(i).getNombre()).getBytes();
                for (int j = 0; j < nom_pieza.length; j++) {
                    if (nom_bytes[j] == nom_pieza[j]){
                        listaPiezasNom.add(listaPiezas.get(i));
                    }
                }
            }
        }

    }

    public void cargarCombobox(){
        DefaultComboBoxModel<PiezasEntity> piezasModel = new DefaultComboBoxModel<>();

        for (PiezasEntity pieza: listaPiezasNom) {
            piezasModel.addElement(pieza);
        }
        comboBox_nom_pieza.setModel(piezasModel);
    }


    public JPanel getVPanelConsNombPiezas() {
        return VPanelConsNombPiezas;
    }
}
