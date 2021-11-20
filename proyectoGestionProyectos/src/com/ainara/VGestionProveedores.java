package com.ainara;

import javax.swing.*;

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

    public VGestionProveedores(){

    }

    public JPanel getVPanelGestionProveedores() {
        return VPanelGestionProveedores;
    }
}
