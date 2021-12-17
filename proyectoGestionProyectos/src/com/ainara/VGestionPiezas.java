package com.ainara;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;

public class VGestionPiezas {

    private JPanel VPanelGestionPiezas;
    private JTabbedPane panel_piezas;
    private JPanel pestana_gestionPiezas;
    private JTextField et_cod_pieza;
    private JTextField et_nom_pieza;
    private JTextField et_precio_pieza;
    private JTextField et_desc_pieza;
    private JButton b_limpiar_pieza;
    private JButton b_insertar_pieza;
    private JButton b_modificar_pieza;
    private JButton b_eliminar_pieza;
    private JPanel pestana_listadoPiezas;
    private JTextField et_codList_pieza;
    private JTextField et_nomList_pieza;
    private JTextField et_precioList_pieza;
    private JTextField et_descList_pieza;
    private JButton b_first_pieza;
    private JButton b_prev_pieza;
    private JButton b_next_pieza;
    private JButton b_last_pieza;
    private JButton b_ejecutarConsulta_pieza;
    private JButton b_baja_pieza;

    private ArrayList<PiezasEntity> listaPiezas = new ArrayList<>();

    private PiezasEntity pieza;

    private int indice = 0;

    public VGestionPiezas() {
        CargarLista();

        //Pestaña Gestión Piezas
        b_limpiar_pieza.addActionListener(e -> {
            et_cod_pieza.setText("");
            et_nom_pieza.setText("");
            et_precio_pieza.setText("");
            et_desc_pieza.setText("");
        });
        b_insertar_pieza.addActionListener(e -> {
            if (!ComprobarCamposVacios()) {
                JOptionPane.showMessageDialog(null, CamposVacios(), "Campos vacios", JOptionPane.WARNING_MESSAGE);
            } else {
                if (!numeroCorrecto(et_precio_pieza.getText())){
                    JOptionPane.showMessageDialog(null, "Introduce un valor válido en el precio.", "Campos erroneos", JOptionPane.ERROR_MESSAGE);
                    et_precio_pieza.setText("");
                } else {
                    if (!longitudString(20, et_nom_pieza.getText())){
                        JOptionPane.showMessageDialog(null, "EL nombre de la pieza es muy largo, tiene que ser menor de 40 carácteres", "Error longitud String", JOptionPane.WARNING_MESSAGE);
                        et_nom_pieza.setText("");
                    }
                    pieza = new PiezasEntity(et_nom_pieza.getText(), Double.parseDouble(et_precio_pieza.getText()), et_desc_pieza.getText());
                    listaPiezas.add(pieza);
                    new Cargas().insertarPieza(pieza);
                    CargarLista();
                }

            }
        });
        b_modificar_pieza.addActionListener(e -> {
            if (!ComprobarCamposVacios()) {
                JOptionPane.showMessageDialog(null, CamposVacios(), "Campos vacios", JOptionPane.WARNING_MESSAGE);
            } else {
                if (!numeroCorrecto(et_precio_pieza.getText())){
                    JOptionPane.showMessageDialog(null, "Introduce un valor válido en el precio.", "Campos erroneos", JOptionPane.ERROR_MESSAGE);
                    et_precio_pieza.setText("");
                } else {
                    if (!longitudString(20, et_nom_pieza.getText())){
                        JOptionPane.showMessageDialog(null, "EL nombre de la ciudad es muy larga, tiene que ser menor de 40 carácteres", "Error longitud String", JOptionPane.WARNING_MESSAGE);
                        et_nom_pieza.setText("");
                    }
                    int cod_prov = Integer.parseInt(et_cod_pieza.getText());
                    pieza = new PiezasEntity(cod_prov, et_nom_pieza.getText(), Double.parseDouble(et_precio_pieza.getText()), et_desc_pieza.getText());
                    listaPiezas.add(pieza);
                    new Cargas().modificarPieza(pieza);
                    CargarLista();
                }

            }
        });
        b_eliminar_pieza.addActionListener(e -> {
            if (et_cod_pieza.getText() != null) {
                int opcion = JOptionPane.showConfirmDialog(null, "Seguro que lo quieres eliminar?", "Eliminar", JOptionPane.YES_NO_OPTION);

                if (opcion == JOptionPane.YES_OPTION) {
                    int cod_pieza = Integer.parseInt(et_cod_pieza.getText());
                    pieza = new PiezasEntity(cod_pieza);
                    listaPiezas.remove(pieza);
                    new Cargas().eliminarPieza(cod_pieza);
                    CargarLista();
                    et_cod_pieza.setText("");
                    et_nom_pieza.setText("");
                    et_precio_pieza.setText("");
                    et_desc_pieza.setText("");
                }
            }
        });

        //Pestaña Listado de Piezas
        et_codList_pieza.setEnabled(false);
        et_nomList_pieza.setEnabled(false);
        et_precioList_pieza.setEnabled(false);
        et_descList_pieza.setEnabled(false);

        b_ejecutarConsulta_pieza.addActionListener(e -> {
            et_codList_pieza.setText(String.valueOf(listaPiezas.get(0).getIdPieza()));
            et_nomList_pieza.setText(listaPiezas.get(0).getNombre());
            et_precioList_pieza.setText(String.valueOf(listaPiezas.get(0).getPrecio()));
            et_descList_pieza.setText(listaPiezas.get(0).getDescripcion());
            indice = 0;
        });
        b_first_pieza.addActionListener(e -> {
            pieza = listaPiezas.get(0);
            et_codList_pieza.setText(String.valueOf(listaPiezas.get(0).getIdPieza()));
            et_nomList_pieza.setText(listaPiezas.get(0).getNombre());
            et_precioList_pieza.setText(String.valueOf(listaPiezas.get(0).getPrecio()));
            et_descList_pieza.setText(listaPiezas.get(0).getDescripcion());
            indice = 0;
        });
        b_prev_pieza.addActionListener(e -> {
            int total = listaPiezas.size() - 1;
            if (indice > 0 && indice <= total) {
                int prev_indice = indice - 1;
                PiezasEntity piezaNueva = listaPiezas.get(prev_indice);
                et_codList_pieza.setText(String.valueOf(piezaNueva.getIdPieza()));
                et_nomList_pieza.setText(piezaNueva.getNombre());
                et_precioList_pieza.setText(String.valueOf(piezaNueva.getPrecio()));
                et_descList_pieza.setText(piezaNueva.getDescripcion());
                indice = prev_indice;
            }
        });
        b_next_pieza.addActionListener(e -> {
            int total = listaPiezas.size() - 1;
            if (indice > 0 && indice <= total) {
                int next_indice = indice + 1;
                PiezasEntity piezaNueva = listaPiezas.get(next_indice);
                et_codList_pieza.setText(String.valueOf(piezaNueva.getIdPieza()));
                et_nomList_pieza.setText(piezaNueva.getNombre());
                et_precioList_pieza.setText(String.valueOf(piezaNueva.getPrecio()));
                et_descList_pieza.setText(piezaNueva.getDescripcion());
                indice = next_indice;
            }
        });
        b_last_pieza.addActionListener(e -> {
            int total = listaPiezas.size();

            PiezasEntity piezaNueva = listaPiezas.get(total - 1);
            et_codList_pieza.setText(String.valueOf(piezaNueva.getIdPieza()));
            et_nomList_pieza.setText(piezaNueva.getNombre());
            et_precioList_pieza.setText(String.valueOf(piezaNueva.getPrecio()));
            et_descList_pieza.setText(piezaNueva.getDescripcion());
            indice = total - 1;

        });
        b_baja_pieza.addActionListener(e -> {
            if (et_codList_pieza.getText() != null) {
                int opcion = JOptionPane.showConfirmDialog(null, "Seguro que lo quieres eliminar?", "Eliminar", JOptionPane.YES_NO_OPTION);

                if (opcion == JOptionPane.YES_OPTION){
                    int cod_pieza = Integer.parseInt(et_codList_pieza.getText());
                    pieza = new PiezasEntity(cod_pieza);
                    listaPiezas.remove(pieza);
                    new Cargas().eliminarProveedor(cod_pieza);
                    CargarLista();
                    et_codList_pieza.setText("");
                    et_nomList_pieza.setText("");
                    et_precioList_pieza.setText("");
                    et_descList_pieza.setText("");
                }
            }
        });
    }

    public void CargarLista() {
        listaPiezas = new Cargas().listaPiezas();

    }
    private boolean longitudString(int longitud, String texto){
        boolean correcto = true;
        byte[] texto_byte = texto.getBytes();
        if (texto_byte.length > longitud){
            correcto = false;
        }
        return correcto;
    }

    private boolean ComprobarCamposVacios() {
        boolean hayDato = true;
        ArrayList<JTextField> campos = new ArrayList<>();
        campos.add(et_cod_pieza);
        campos.add(et_nom_pieza);
        campos.add(et_precio_pieza);
        campos.add(et_desc_pieza);

        for (JTextField campo : campos) {
            if (campo.getText().equalsIgnoreCase("")) {
                hayDato = false;
            }
        }
        return hayDato;
    }

    private String CamposVacios() {
        String vacios = "Los siguientes campos están vacios:\n";

        if (et_cod_pieza.getText().equalsIgnoreCase("")) {
            vacios += "     Cod proveedor\n";
        }
        if (et_nom_pieza.getText().equalsIgnoreCase("")) {
            vacios += "     Nombre proveedor\n";
        }
        if (et_precio_pieza.getText().equalsIgnoreCase("")) {
            vacios += "     Apellidos proveedor\n";
        }
        if (et_desc_pieza.getText().equalsIgnoreCase("")) {
            vacios += "     Dirección proveedor\n";
        }
        vacios += "\nIntroduce los datos de los campos anteriores\n";
        return vacios;
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

    public JPanel getVPanelGestionPiezas() {
        return VPanelGestionPiezas;
    }
}
