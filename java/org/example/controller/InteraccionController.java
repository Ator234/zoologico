package org.example.controller;

import org.example.dao.AnimalDAO;
import org.example.dao.EmpleadoDAO;
import org.example.dao.InteraccionDAO;
import org.example.model.Animal;
import org.example.model.Empleado;
import org.example.model.Interaccion;
import org.example.model.Motivo;
import org.example.view.PanelInteracciones;

import javax.swing.*;
import java.time.LocalDate;
import java.util.List;

public class InteraccionController {

    private final InteraccionDAO dao;
    private final AnimalDAO animalDao;
    private final EmpleadoDAO empleadoDao;
    private final PanelInteracciones vista;

    public InteraccionController(PanelInteracciones vista) {
        this.dao = new InteraccionDAO();
        this.animalDao = new AnimalDAO();
        this.empleadoDao = new EmpleadoDAO();
        this.vista = vista;
        initController();
        cargarCombos();
        cargarDatos();
    }

    private void initController() {
        vista.btnInsertar.addActionListener(e -> insertar());
        vista.btnActualizar.addActionListener(e -> actualizar());
        vista.btnEliminar.addActionListener(e -> eliminar());

        vista.tabla.getSelectionModel().addListSelectionListener(e -> {
            int fila = vista.tabla.getSelectedRow();
            if (fila >= 0) {
                vista.txtFecha.setText(vista.tabla.getValueAt(fila, 1).toString());
                vista.comboMotivo.setSelectedItem(Motivo.valueOf(vista.tabla.getValueAt(fila, 2).toString()));

                int chip = Integer.parseInt(vista.tabla.getValueAt(fila, 3).toString());
                for (int i = 0; i < vista.comboAnimal.getItemCount(); i++) {
                    if (vista.comboAnimal.getItemAt(i).getNumeroChip() == chip) {
                        vista.comboAnimal.setSelectedIndex(i);
                        break;
                    }
                }

                String dni = vista.tabla.getValueAt(fila, 4).toString();
                for (int i = 0; i < vista.comboEmpleado.getItemCount(); i++) {
                    if (vista.comboEmpleado.getItemAt(i).getDni().equals(dni)) {
                        vista.comboEmpleado.setSelectedIndex(i);
                        break;
                    }
                }
            }
        });
    }

    private void cargarCombos() {
        vista.comboAnimal.removeAllItems();
        for (Animal a : animalDao.listar()) vista.comboAnimal.addItem(a);

        vista.comboEmpleado.removeAllItems();
        for (Empleado e : empleadoDao.listar()) vista.comboEmpleado.addItem(e);
    }

    private void insertar() {
        try {
            Interaccion i = new Interaccion();
            i.setFecha(LocalDate.parse(vista.txtFecha.getText().trim()));
            i.setMotivo((Motivo) vista.comboMotivo.getSelectedItem());

            Animal animal = (Animal) vista.comboAnimal.getSelectedItem();
            Empleado empleado = (Empleado) vista.comboEmpleado.getSelectedItem();

            i.setAnimal(animal);
            i.setEmpleado(empleado);

            dao.insertar(i);
            cargarDatos();
        } catch (Exception ex) {
            JOptionPane.showMessageDialog(vista, "Error al insertar: " + ex.getMessage());
        }
    }

    private void actualizar() {
        try {
            int fila = vista.tabla.getSelectedRow();
            if (fila < 0) { JOptionPane.showMessageDialog(vista,"Selecciona una fila"); return; }

            Long id = Long.parseLong(vista.tabla.getValueAt(fila, 0).toString());
            Interaccion i = dao.buscarPorId(id);

            i.setFecha(LocalDate.parse(vista.txtFecha.getText().trim()));
            i.setMotivo((Motivo) vista.comboMotivo.getSelectedItem());
            i.setAnimal((Animal) vista.comboAnimal.getSelectedItem());
            i.setEmpleado((Empleado) vista.comboEmpleado.getSelectedItem());

            dao.actualizar(i);
            cargarDatos();
        } catch (Exception ex) {
            JOptionPane.showMessageDialog(vista, "Error al actualizar: " + ex.getMessage());
        }
    }

    private void eliminar() {
        try {
            int fila = vista.tabla.getSelectedRow();
            if (fila < 0) { JOptionPane.showMessageDialog(vista,"Selecciona una fila"); return; }

            Long id = Long.parseLong(vista.tabla.getValueAt(fila, 0).toString());
            dao.eliminar(id);
            cargarDatos();
        } catch (Exception ex) {
            JOptionPane.showMessageDialog(vista, "Error al eliminar: " + ex.getMessage());
        }
    }

    private void cargarDatos() {
        vista.modeloTabla.setRowCount(0);
        List<Interaccion> interacciones = dao.listar();
        for (Interaccion i : interacciones) {
            vista.modeloTabla.addRow(new Object[]{
                    i.getId(),
                    i.getFecha(),
                    i.getMotivo(),
                    i.getAnimal().getNumeroChip(),
                    i.getEmpleado().getDni()
            });
        }
    }
}
