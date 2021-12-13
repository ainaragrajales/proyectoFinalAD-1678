package com.ainara;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;

public class VConsultaCodigoProveedores {
    private JTextArea textArea_prov;
    private JTextField et_cod_prov;
    private JButton buscarProveedorButton;
    private JComboBox<ProveedoresEntity> comboBox_cod_prov;
    private JPanel VPanelConsCodProveedores;

    private ArrayList<ProveedoresEntity> listaProveedores = new ArrayList<>();
    private ArrayList<ProveedoresEntity> listaProveedoresCod = new ArrayList<>();

    private ProveedoresEntity proveedor;

    public VConsultaCodigoProveedores() {

        buscarProveedorButton.addActionListener(e -> {
            int cod = Integer.parseInt(et_cod_prov.getText());
            buscarPorCod(cod);
            cargarCombobox();
        });
        comboBox_cod_prov.addActionListener(e -> {
            proveedor = (ProveedoresEntity) comboBox_cod_prov.getSelectedItem();
            if (proveedor != null){
                String proveedorString =
                        "\nCOD:                    " + proveedor.getIdProv() + "\n" +
                        "\nNOMBRE:               " + proveedor.getNombre() + "\n" +
                        "\nAPELLIDOS:           " + proveedor.getApellidos() + "\n" +
                        "\nDIRECCIÓN:          " + proveedor.getDir();

                textArea_prov.setText(proveedorString);
            }
        });
    }

    public void buscarPorCod(int cod){
        listaProveedores = new Cargas().listaProveedores();

        for (int i = 0; i < listaProveedores.size(); i++) {
            if (listaProveedores.get(i).getIdProv() == cod){
                listaProveedoresCod.add(listaProveedores.get(i));
            }
        }
        byte[] cod_bytes = String.valueOf(cod).getBytes();
        byte[] cod_prov;
        if (listaProveedoresCod == null){
            for (int i = 0; i < listaProveedores.size(); i++) {
                cod_prov = String.valueOf(listaProveedores.get(i).getIdProv()).getBytes();
                for (int j = 0; j < cod_prov.length; j++) {
                    if (cod_bytes[j] == cod_prov[j]){
                        listaProveedoresCod.add(listaProveedores.get(i));
                    }
                }
            }
        }

    }

    public void cargarCombobox(){
        DefaultComboBoxModel<ProveedoresEntity> proveedoresModel = new DefaultComboBoxModel<>();

        for (ProveedoresEntity proveedor: listaProveedoresCod) {
            proveedoresModel.addElement(proveedor);
        }
        comboBox_cod_prov.setModel(proveedoresModel);
    }


    public JPanel getVPanelConsCodProveedores() {
        return VPanelConsCodProveedores;
    }
}
