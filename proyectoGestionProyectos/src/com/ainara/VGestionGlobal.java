package com.ainara;

import javax.swing.*;
import java.awt.*;
import java.util.ArrayList;

public class VGestionGlobal {
    private JPanel VPanelGestionGlobal;
    private JButton button_insertar;
    private JButton button_modificar;
    private JButton button_eliminar;
    private JButton button_listado;
    private JComboBox<ProveedoresEntity> comboBox_prov;
    private JTextField et_prov_nom;
    private JTextField et_prov_ape;
    private JComboBox<PiezasEntity> comboBox_pieza;
    private JTextField et_pieza_nom;
    private JTextField et_pieza_desc;
    private JComboBox<ProyectosEntity> comboBox_proy;
    private JTextField et_proy_nom;
    private JTextField et_proy_ciud;
    private JTextField et_cant;
    private JTextField et_id_gest;

    private ArrayList<ProveedoresEntity> listaProveedores = new ArrayList<>();
    private ArrayList<PiezasEntity> listaPiezas = new ArrayList<>();
    private ArrayList<ProyectosEntity> listaProyectos = new ArrayList<>();
    private ArrayList<GestionEntity> listaGestiones = new ArrayList<>();

    private ProveedoresEntity proveedor;
    private PiezasEntity pieza;
    private ProyectosEntity proyecto;
    private GestionEntity gestion;

    public VGestionGlobal() {

        et_prov_nom.setEnabled(false);
        et_prov_ape.setEnabled(false);
        et_pieza_nom.setEnabled(false);
        et_pieza_desc.setEnabled(false);
        et_proy_nom.setEnabled(false);
        et_proy_ciud.setEnabled(false);

        CargarTodo();

        button_insertar.addActionListener(e -> {
            if (!ComprobarCamposVacios()){
                JOptionPane.showMessageDialog(null, CamposVacios(), "Campos vacios", JOptionPane.ERROR_MESSAGE);
            } else {
                proveedor = (ProveedoresEntity) comboBox_prov.getSelectedItem();
                pieza = (PiezasEntity) comboBox_pieza.getSelectedItem();
                proyecto = (ProyectosEntity) comboBox_proy.getSelectedItem();
                if (!numeroCorrecto(et_cant.getText())){
                    JOptionPane.showMessageDialog(null, "Introduce un valor válido en la cantidad.", "Campos erroneos", JOptionPane.ERROR_MESSAGE);
                    et_cant.setText("");
                } else {
                    if (proveedor != null && pieza != null && proyecto != null){
                        gestion = new GestionEntity( Double.parseDouble(et_cant.getText()), proveedor, pieza, proyecto);
                        new Cargas().insertarGestion(gestion);
                        CargarTodo();
                    } else {
                        JOptionPane.showMessageDialog(null, "Selecciona en los combobox", "Campos vacios", JOptionPane.ERROR_MESSAGE);
                    }
                }

            }
        });
        button_modificar.addActionListener(e -> {
            if (!ComprobarCamposVacios()){
                JOptionPane.showMessageDialog(null, CamposVacios(), "Campos vacios", JOptionPane.ERROR_MESSAGE);
            } else {
                int id = Integer.parseInt(et_id_gest.getText());
                proveedor = (ProveedoresEntity) comboBox_prov.getSelectedItem();
                pieza = (PiezasEntity) comboBox_pieza.getSelectedItem();
                proyecto = (ProyectosEntity) comboBox_proy.getSelectedItem();
                if (!numeroCorrecto(et_cant.getText())){
                    JOptionPane.showMessageDialog(null, "Introduce un numero entero en la cantidad.", "Campos erroneos", JOptionPane.ERROR_MESSAGE);
                    et_cant.setText("");
                } else {
                    if (proveedor != null && pieza != null && proyecto != null){
                        gestion = new GestionEntity(id, Double.parseDouble(et_cant.getText()), proveedor, pieza, proyecto);
                        new Cargas().modificarGestion(gestion);
                        CargarTodo();
                    } else {
                        JOptionPane.showMessageDialog(null, "Selecciona en los combobox", "Campos vacios", JOptionPane.ERROR_MESSAGE);

                    }
                }

            }
        });
        button_eliminar.addActionListener(e -> {
            int opcion = JOptionPane.showConfirmDialog(null, "Seguro que lo quieres eliminar?", "Eliminar", JOptionPane.YES_NO_OPTION);

            if (opcion == JOptionPane.YES_OPTION){
                if (et_id_gest != null){
                    int id = Integer.parseInt(et_id_gest.getText());
                    proveedor = (ProveedoresEntity) comboBox_prov.getSelectedItem();
                    pieza = (PiezasEntity) comboBox_pieza.getSelectedItem();
                    proyecto = (ProyectosEntity) comboBox_proy.getSelectedItem();
                    gestion = new GestionEntity(id, Double.parseDouble(et_cant.getText()), proveedor, pieza, proyecto);
                    new Cargas().eliminarGestion(gestion);
                    CargarTodo();
                }

            }
        });
        button_listado.addActionListener(e -> {
            JFrame frame_listado_gestiones = new JFrame("Listado de Gestiones");
            frame_listado_gestiones.setContentPane(new VGestionGlobalListado().getVPanelGestionGlobalListado());
            frame_listado_gestiones.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE); // Cierra la ventana pero no el programa
            frame_listado_gestiones.setMinimumSize(new Dimension(400, 200)); // Lo ajusto a un tamaño para que se vea bien
            frame_listado_gestiones.setLocationRelativeTo(null); // Saca la ventana al centro
            frame_listado_gestiones.pack();
            frame_listado_gestiones.setVisible(true);
        });
        comboBox_prov.addActionListener(e -> {
            proveedor = (ProveedoresEntity) comboBox_prov.getSelectedItem();

            if (proveedor != null) {
                et_prov_nom.setText(proveedor.getNombre());
                et_prov_ape.setText(proveedor.getApellidos());
            }
        });

        comboBox_pieza.addActionListener(e -> {
            pieza = (PiezasEntity) comboBox_pieza.getSelectedItem();

            if (pieza != null){
                et_pieza_nom.setText(pieza.getNombre());
                et_pieza_desc.setText(pieza.getDescripcion());
            }
        });
        comboBox_proy.addActionListener(e -> {
            proyecto = (ProyectosEntity) comboBox_proy.getSelectedItem();

            if (proyecto != null){
                et_proy_nom.setText(proyecto.getNombre());
                et_proy_ciud.setText(proyecto.getCiudad());
            }
        });
    }

    public void CargarTodo(){
        listaProveedores = new Cargas().listaProveedores();
        listaPiezas = new Cargas().listaPiezas();
        listaProyectos = new Cargas().listaProyectos();
        cargarComboboxProveedores();
        cargarComboboxPiezas();
        cargarComboboxProyectos();
    }

    private boolean ComprobarCamposVacios(){
        boolean hayDato = true;
        ArrayList<JTextField> campos = new ArrayList<>();
        campos.add(et_cant);
        campos.add(et_id_gest);


        for (JTextField campo : campos) {
            if (campo.getText().equalsIgnoreCase("")) {
                hayDato = false;
            }
        }
        return hayDato;
    }

    private String CamposVacios(){
        String vacios = "Los siguientes campos están vacios:\n";

        if (et_cant.getText().equalsIgnoreCase("")){
            vacios += "     Cantidad \n";
        }
        if (et_id_gest.getText().equalsIgnoreCase("")){
            vacios += "     ID Gestión \n";
        }

        vacios += "\nIntroduce los datos de los campos anteriores\n";
        return vacios;
    }

    public void cargarComboboxProveedores(){
        DefaultComboBoxModel<ProveedoresEntity> proveedoresModel = new DefaultComboBoxModel<>();

        for (ProveedoresEntity proveedor: listaProveedores) {
            proveedoresModel.addElement(proveedor);
        }
        comboBox_prov.setModel(proveedoresModel);
    }
    public void cargarComboboxPiezas(){
        DefaultComboBoxModel<PiezasEntity> piezasModel = new DefaultComboBoxModel<>();

        for (PiezasEntity proveedor: listaPiezas) {
            piezasModel.addElement(proveedor);
        }
        comboBox_pieza.setModel(piezasModel);
    }
    public void cargarComboboxProyectos(){
        DefaultComboBoxModel<ProyectosEntity> proyectosModel = new DefaultComboBoxModel<>();

        for (ProyectosEntity proveedor: listaProyectos) {
            proyectosModel.addElement(proyecto);
        }
        comboBox_proy.setModel(proyectosModel);
    }

    private boolean numeroCorrecto(String numero) {
        boolean correcto = true;
        try {
            Double comprobaNumero = Double.parseDouble(numero);
        } catch (NumberFormatException e) {
            //e.printStackTrace();
            System.out.println("Error !!, número incorrecto");
            correcto = false;
        }


        return correcto;
    }

    public JPanel getVPanelGestionGlobal() {
        return VPanelGestionGlobal;
    }
}
