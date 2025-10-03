package org.example.controller;

import org.example.dao.EmpleadoDAO;
import org.example.model.Empleado;
import org.example.model.Profesion;
import org.example.view.PanelEmpleados;

import javax.swing.*;
import java.util.List;

public class EmpleadoController {

    private final EmpleadoDAO dao;
    private final PanelEmpleados vista;

    public EmpleadoController(PanelEmpleados vista) {
        this.dao = new EmpleadoDAO();
        this.vista = vista;
        initController();
        cargarDatos();
    }

    private void initController() {
        vista.btnInsertar.addActionListener(e -> insertar());
        vista.btnActualizar.addActionListener(e -> actualizar());
        vista.btnEliminar.addActionListener(e -> eliminar());

        vista.tabla.getSelectionModel().addListSelectionListener(e -> {
            int fila = vista.tabla.getSelectedRow();
            if (fila >= 0) {
                vista.txtDni.setText(vista.tabla.getValueAt(fila,0).toString());
                vista.txtNombre.setText(vista.tabla.getValueAt(fila,1).toString());
                vista.comboProfesion.setSelectedItem(Profesion.valueOf(vista.tabla.getValueAt(fila,2).toString()));
                vista.txtExperiencia.setText(vista.tabla.getValueAt(fila,3).toString());
            }
        });
    }

    private void insertar() {
        try {
            Empleado e = new Empleado();
            e.setDni(vista.txtDni.getText().trim());
            e.setNombre(vista.txtNombre.getText().trim());
            e.setProfesion((Profesion) vista.comboProfesion.getSelectedItem());
            e.setExperiencia(Integer.parseInt(vista.txtExperiencia.getText().trim()));

            dao.insertar(e);
            cargarDatos();
        } catch (Exception ex) {
            JOptionPane.showMessageDialog(vista, "Error al insertar: " + ex.getMessage());
        }
    }

    private void actualizar() {
        try {
            Empleado e = dao.buscarPorDni(vista.txtDni.getText().trim());
            if (e == null) { JOptionPane.showMessageDialog(vista,"Empleado no encontrado"); return; }

            e.setNombre(vista.txtNombre.getText().trim());
            e.setProfesion((Profesion) vista.comboProfesion.getSelectedItem());
            e.setExperiencia(Integer.parseInt(vista.txtExperiencia.getText().trim()));

            dao.actualizar(e);
            cargarDatos();
        } catch (Exception ex) {
            JOptionPane.showMessageDialog(vista, "Error al actualizar: " + ex.getMessage());
        }
    }

    private void eliminar() {
        try {
            String dni = vista.txtDni.getText().trim();
            dao.eliminar(dni);
            cargarDatos();
        } catch (Exception ex) {
            JOptionPane.showMessageDialog(vista,"Error al eliminar: "+ex.getMessage());
        }
    }

    private void cargarDatos() {
        vista.modeloTabla.setRowCount(0);
        List<Empleado> empleados = dao.listar();
        for (Empleado e : empleados) {
            vista.modeloTabla.addRow(new Object[]{e.getDni(), e.getNombre(), e.getProfesion(), e.getExperiencia()});
        }
    }
}
