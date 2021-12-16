package com.ainara;

import javax.swing.*;
import java.util.ArrayList;

public class VConsultaDireccionProveedores {
    private JPanel VPanelConsDirProveedores;
    private JTextField et_dir_prov;
    private JButton buscarProveedorButton;
    private JComboBox<ProveedoresEntity> comboBox_dir_prov;
    private JTextArea textArea_prov;

    private ArrayList<ProveedoresEntity> listaProveedores = new ArrayList<>();
    private ArrayList<ProveedoresEntity> listaProveedoresDir = new ArrayList<>();

    private ProveedoresEntity proveedor;

    public VConsultaDireccionProveedores() {
        et_dir_prov.addActionListener(e -> {
            comboBox_dir_prov.removeAllItems();
            textArea_prov.setText("");
            listaProveedoresDir.clear();
        });
        buscarProveedorButton.addActionListener(e -> {
            String dir = et_dir_prov.getText();
            buscarPorDir(dir);
            cargarCombobox();
        });
        comboBox_dir_prov.addActionListener(e -> {
            proveedor = (ProveedoresEntity) comboBox_dir_prov.getSelectedItem();
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

    public void buscarPorDir(String dir){
        listaProveedores = new Cargas().listaProveedores();

        for (int i = 0; i < listaProveedores.size(); i++) {
            if (listaProveedores.get(i).getDir().equalsIgnoreCase(dir)){
                listaProveedoresDir.add(listaProveedores.get(i));
            }
        }
        byte[] dir_bytes = et_dir_prov.getText().getBytes();
        byte[] dir_prov;

        if (listaProveedoresDir.size()== 0){
            for (int i = 0; i < listaProveedores.size(); i++) {
                dir_prov = listaProveedores.get(i).getDir().getBytes();
                for (int j = 0; j < dir_prov.length; j++) {
                    if (dir_bytes[j] == dir_prov[j]){
                        listaProveedoresDir.add(listaProveedores.get(i));
                    }
                }
            }
        }
    }
    public void cargarCombobox(){
        DefaultComboBoxModel<ProveedoresEntity> proveedoresModel = new DefaultComboBoxModel<>();

        for (ProveedoresEntity proveedor: listaProveedoresDir) {
            proveedoresModel.addElement(proveedor);
        }
        comboBox_dir_prov.setModel(proveedoresModel);
    }
    public JPanel getVPanelConsDirProveedores() {
        return VPanelConsDirProveedores;
    }
}
