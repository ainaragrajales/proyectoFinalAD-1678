package com.ainara;

import javax.swing.*;
import java.util.ArrayList;

public class VGestionProveedores {
    private JTabbedPane panel_proveedores;
    private JPanel VPanelGestionProveedores;
    private JPanel pestana_gestionProveedores;
    private JPanel pestana_listadoProveedores;
    private JButton b_limpiar_proveedor;
    private JButton b_insertar_proveedor;
    private JButton b_modificar_proveedor;
    private JButton b_eliminar_proveedor;
    private JTextField et_cod_proveedor;
    private JTextField et_nom_proveedor;
    private JTextField et_ape_proveedor;
    private JTextField et_dir_proveedor;
    private JTextField et_codList_proveedor;
    private JTextField et_nomList_proveedor;
    private JTextField et_apeList_proveedor;
    private JTextField et_dirList_proveedor;
    private JButton b_ejecutarConsulta_proveedor;
    private JButton b_first_proveedor;
    private JButton b_prev_proveedor;
    private JButton b_next_proveedor;
    private JButton b_last_proveedor;
    private JButton b_baja_proveedor;

    private ArrayList<ProveedoresEntity> listaProveedores = new ArrayList<>();
    /*private ArrayList<PiezasEntity> listaPiezas = new ArrayList<>();
    private ArrayList<ProyectosEntity> listaProyectos = new ArrayList<>();
    private ArrayList<GestionEntity> listaGestiones = new ArrayList<>();*/

    private ProveedoresEntity proveedor;
    /*private PiezasEntity pieza;
    private ProyectosEntity proyecto;
    private GestionEntity gestion;*/
    private int indice = 0;

    public VGestionProveedores(){

        CargarLista();

        //Pestaña Gestión Proveedores

        b_limpiar_proveedor.addActionListener(e -> {
            et_cod_proveedor.setText("");
            et_nom_proveedor.setText("");
            et_ape_proveedor.setText("");
            et_dir_proveedor.setText("");
        });
        b_insertar_proveedor.addActionListener(e -> {
            if (!ComprobarCamposVacios()){
                JOptionPane.showMessageDialog(null, CamposVacios(), "Campos vacios", JOptionPane.WARNING_MESSAGE);
            } else {
                proveedor = new ProveedoresEntity(et_nom_proveedor.getText(), et_ape_proveedor.getText(), et_dir_proveedor.getText());
                listaProveedores.add(proveedor);
                new Cargas().insertarProveedor(proveedor);
                CargarLista();
            }
        });
        b_modificar_proveedor.addActionListener(e -> {
            if (!ComprobarCamposVacios()){
                JOptionPane.showMessageDialog(null, CamposVacios(), "Campos vacios", JOptionPane.WARNING_MESSAGE);
            } else {
                int cod_prov = Integer.parseInt(et_cod_proveedor.getText());
                proveedor = new ProveedoresEntity(cod_prov, et_nom_proveedor.getText(), et_ape_proveedor.getText(), et_dir_proveedor.getText());
                listaProveedores.add(proveedor);
                new Cargas().modificarProveedor(proveedor);
                CargarLista();
            }
        });
        b_eliminar_proveedor.addActionListener(e -> {
            int opcion = JOptionPane.showConfirmDialog(null, "Seguro que lo quieres eliminar?", "Eliminar", JOptionPane.YES_NO_OPTION);

            if (opcion == JOptionPane.YES_OPTION){
                int cod_prov = Integer.parseInt(et_cod_proveedor.getText());
                proveedor = new ProveedoresEntity(cod_prov, et_nom_proveedor.getText(), et_ape_proveedor.getText(), et_dir_proveedor.getText());
                listaProveedores.remove(proveedor);
                new Cargas().eliminarProveedor(cod_prov);
                CargarLista();
                et_cod_proveedor.setText("");
                et_nom_proveedor.setText("");
                et_ape_proveedor.setText("");
                et_dir_proveedor.setText("");
            }
        });

        //Pestaña Listado de Proveedores
        et_codList_proveedor.setEnabled(false);
        et_nomList_proveedor.setEnabled(false);
        et_apeList_proveedor.setEnabled(false);
        et_dirList_proveedor.setEnabled(false);

        b_ejecutarConsulta_proveedor.addActionListener(e -> {

            et_codList_proveedor.setText(String.valueOf(listaProveedores.get(0).getIdProv()));
            et_nomList_proveedor.setText(listaProveedores.get(0).getNombre());
            et_apeList_proveedor.setText(listaProveedores.get(0).getApellidos());
            et_dirList_proveedor.setText(listaProveedores.get(0).getDir());
            indice = 0;
        });
        b_first_proveedor.addActionListener(e -> {
            proveedor = listaProveedores.get(0);
            et_codList_proveedor.setText(String.valueOf(proveedor.getIdProv()));
            et_nomList_proveedor.setText(proveedor.getNombre());
            et_apeList_proveedor.setText(proveedor.getApellidos());
            et_dirList_proveedor.setText(proveedor.getDir());
            indice = 0;
        });
        b_prev_proveedor.addActionListener(e -> {
            int total = listaProveedores.size() - 1;
            if (indice > 0 && indice <= total){
                int prev_indice = indice - 1;
                ProveedoresEntity provNuevo = listaProveedores.get(prev_indice);

                et_codList_proveedor.setText(String.valueOf(provNuevo.getIdProv()));
                et_nomList_proveedor.setText(provNuevo.getNombre());
                et_apeList_proveedor.setText(provNuevo.getApellidos());
                et_dirList_proveedor.setText(provNuevo.getDir());
                indice = prev_indice;
            }


        });
        b_next_proveedor.addActionListener(e -> {
            int total = listaProveedores.size() - 1;
            if (indice >= 0 && indice < total){
                int next_indice = indice + 1;
                ProveedoresEntity provNuevo = listaProveedores.get(next_indice);

                et_codList_proveedor.setText(String.valueOf(provNuevo.getIdProv()));
                et_nomList_proveedor.setText(provNuevo.getNombre());
                et_apeList_proveedor.setText(provNuevo.getApellidos());
                et_dirList_proveedor.setText(provNuevo.getDir());
                indice = next_indice;
            }

        });
        b_last_proveedor.addActionListener(e -> {

            int total = listaProveedores.size();
            proveedor = listaProveedores.get(total-1);
            et_codList_proveedor.setText(String.valueOf(proveedor.getIdProv()));
            et_nomList_proveedor.setText(proveedor.getNombre());
            et_apeList_proveedor.setText(proveedor.getApellidos());
            et_dirList_proveedor.setText(proveedor.getDir());
            indice = total - 1;
        });
        b_baja_proveedor.addActionListener(e -> {
            int opcion = JOptionPane.showConfirmDialog(null, "Seguro que lo quieres eliminar?", "Eliminar", JOptionPane.YES_NO_OPTION);

            if (opcion == JOptionPane.YES_OPTION){
                int cod_prov = Integer.parseInt(et_codList_proveedor.getText());
                proveedor = new ProveedoresEntity(cod_prov, et_nomList_proveedor.getText(), et_apeList_proveedor.getText(), et_dirList_proveedor.getText());
                listaProveedores.remove(proveedor);
                new Cargas().eliminarProveedor(cod_prov);
                CargarLista();
                et_codList_proveedor.setText("");
                et_nomList_proveedor.setText("");
                et_apeList_proveedor.setText("");
                et_dirList_proveedor.setText("");
            }
        });
    }

    public void CargarLista(){
        listaProveedores = new Cargas().listaProveedores();

    }

    private boolean ComprobarCamposVacios(){
        boolean hayDato = true;
        ArrayList<JTextField> campos = new ArrayList<>();
        campos.add(et_cod_proveedor);
        campos.add(et_nom_proveedor);
        campos.add(et_ape_proveedor);
        campos.add(et_dir_proveedor);

        for (JTextField campo : campos) {
            if (campo.getText().equalsIgnoreCase("")) {
                hayDato = false;
            }
        }
        return hayDato;
    }

    private String CamposVacios(){
        String vacios = "Los siguientes campos están vacios:\n";

        if (et_cod_proveedor.getText().equalsIgnoreCase("")){
            vacios += "     Cod proveedor\n";
        }
        if (et_nom_proveedor.getText().equalsIgnoreCase("")){
            vacios += "     Nombre proveedor\n";
        }
        if (et_ape_proveedor.getText().equalsIgnoreCase("")){
            vacios += "     Apellidos proveedor\n";
        }
        if (et_dir_proveedor.getText().equalsIgnoreCase("")){
            vacios += "     Dirección proveedor\n";
        }
        vacios += "\nIntroduce los datos de los campos anteriores\n";
        return vacios;
    }

    public JPanel getVPanelGestionProveedores() {
        return VPanelGestionProveedores;
    }
}
