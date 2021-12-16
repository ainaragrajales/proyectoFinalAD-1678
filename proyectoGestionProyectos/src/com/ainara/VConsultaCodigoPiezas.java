package com.ainara;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;

public class VConsultaCodigoPiezas {
    private JPanel VPanelConsCodPiezas;
    private JTextField et_cod_pieza;
    private JButton buscarPiezaButton;
    private JComboBox<PiezasEntity> comboBox_cod_pieza;
    private JTextArea textArea_pieza;

    private ArrayList<PiezasEntity> listaPiezas = new ArrayList<>();
    private ArrayList<PiezasEntity> listaPiezasCod = new ArrayList<>();

    private PiezasEntity pieza;

    public VConsultaCodigoPiezas() {
        et_cod_pieza.addActionListener(e -> {
            comboBox_cod_pieza.removeAllItems();
            textArea_pieza.setText("");
            listaPiezasCod.clear();
        });
        buscarPiezaButton.addActionListener(e -> {
            int cod = Integer.parseInt(et_cod_pieza.getText());
            buscarPorCod(cod);
            cargarCombobox();
        });
        comboBox_cod_pieza.addActionListener(e -> {
            pieza = (PiezasEntity) comboBox_cod_pieza.getSelectedItem();
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

    public void buscarPorCod(int cod){
        listaPiezas = new Cargas().listaPiezas();

        for (int i = 0; i < listaPiezas.size(); i++) {
            if (listaPiezas.get(i).getIdPieza() == cod){
                listaPiezasCod.add(listaPiezas.get(i));
            }
        }
        byte[] cod_bytes = String.valueOf(cod).getBytes();
        byte[] cod_pieza;
        if (listaPiezasCod == null){
            for (int i = 0; i < listaPiezas.size(); i++) {
                cod_pieza = String.valueOf(listaPiezas.get(i).getIdPieza()).getBytes();
                for (int j = 0; j < cod_pieza.length; j++) {
                    if (cod_bytes[j] == cod_pieza[j]){
                        listaPiezasCod.add(listaPiezas.get(i));
                    }
                }
            }
        }

    }

    public void cargarCombobox(){
        DefaultComboBoxModel<PiezasEntity> piezasModel = new DefaultComboBoxModel<>();

        for (PiezasEntity pieza: listaPiezasCod) {
            piezasModel.addElement(pieza);
        }
        comboBox_cod_pieza.setModel(piezasModel);
    }


    public JPanel getVPanelConsCodPiezas() {
        return VPanelConsCodPiezas;
    }
}
