package com.ainara;

import javax.swing.*;
import java.util.ArrayList;

public class VConsultaCodigoProyectos {
    private JPanel VPanelConsCodProyectos;
    private JTextField et_cod_proy;
    private JButton buscarProyectoButton;
    private JComboBox<ProyectosEntity> comboBox_cod_proy;
    private JTextArea textArea_proy;

    private ArrayList<ProyectosEntity> listaProyectos = new ArrayList<>();
    private ArrayList<ProyectosEntity> listaProyectosCod = new ArrayList<>();

    private ProyectosEntity proyecto;

    public VConsultaCodigoProyectos() {
        et_cod_proy.addActionListener(e -> {
            comboBox_cod_proy.removeAllItems();
            textArea_proy.setText("");
            listaProyectosCod.clear();
        });
        buscarProyectoButton.addActionListener(e -> {
            int cod = Integer.parseInt(et_cod_proy.getText());
            buscarPorCod(cod);
            cargarCombobox();
        });
        comboBox_cod_proy.addActionListener(e -> {
            proyecto = (ProyectosEntity) comboBox_cod_proy.getSelectedItem();
            if (proyecto != null){
                String proveedorString =
                        "\nCOD:                    " + proyecto.getIdProyecto() + "\n" +
                                "\nNOMBRE:               " + proyecto.getNombre() + "\n" +
                                "\nCIUDAD:           " + proyecto.getCiudad() + "\n";

                textArea_proy.setText(proveedorString);
            }
        });
    }

    public void buscarPorCod(int cod){
        listaProyectos = new Cargas().listaProyectos();

        for (int i = 0; i < listaProyectos.size(); i++) {
            if (listaProyectos.get(i).getIdProyecto() == cod){
                listaProyectosCod.add(listaProyectos.get(i));
            }
        }
        byte[] cod_bytes = String.valueOf(cod).getBytes();
        byte[] cod_prov;
        if (listaProyectosCod == null){
            for (int i = 0; i < listaProyectos.size(); i++) {
                cod_prov = String.valueOf(listaProyectos.get(i).getIdProyecto()).getBytes();
                for (int j = 0; j < cod_prov.length; j++) {
                    if (cod_bytes[j] == cod_prov[j]){
                        listaProyectosCod.add(listaProyectos.get(i));
                    }
                }
            }
        }

    }

    public void cargarCombobox(){
        DefaultComboBoxModel<ProyectosEntity> proyectosModel = new DefaultComboBoxModel<>();

        for (ProyectosEntity proveedor: listaProyectosCod) {
            proyectosModel.addElement(proveedor);
        }
        comboBox_cod_proy.setModel(proyectosModel);
    }

    public JPanel getVPanelConsCodProyectos() {
        return VPanelConsCodProyectos;
    }
}
