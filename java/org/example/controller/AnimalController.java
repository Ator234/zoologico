package org.example.controller;

import org.example.dao.AnimalDAO;
import org.example.model.Animal;
import org.example.view.PanelAnimales;

import javax.swing.*;
import javax.swing.event.ListSelectionEvent;
import javax.swing.event.ListSelectionListener;
import java.util.List;

public class AnimalController {

    private final AnimalDAO dao;
    private final PanelAnimales vista;

    public AnimalController(PanelAnimales vista) {
        this.dao = new AnimalDAO();
        this.vista = vista;
        initController();
        cargarDatos();
    }

    private void initController() {
        vista.btnInsertar.addActionListener(e -> insertar());
        vista.btnActualizar.addActionListener(e -> actualizar());
        vista.btnEliminar.addActionListener(e -> eliminar());

        vista.tabla.getSelectionModel().addListSelectionListener(new ListSelectionListener() {
            @Override
            public void valueChanged(ListSelectionEvent e) {
                if (e.getValueIsAdjusting()) return;
                int fila = vista.tabla.getSelectedRow();
                if (fila >= 0) {
                    vista.txtChip.setText(vista.tabla.getValueAt(fila, 0).toString());
                    vista.txtApodo.setText(vista.tabla.getValueAt(fila, 1).toString());
                    vista.txtParcela.setText(vista.tabla.getValueAt(fila, 2).toString());
                    vista.txtRaza.setText(vista.tabla.getValueAt(fila, 3).toString());
                    vista.txtChip.setEnabled(false);
                }
            }
        });
    }

    private void insertar() {
        try {
            String chipText = vista.txtChip.getText().trim();
            String apodo = vista.txtApodo.getText().trim();
            String parcelaText = vista.txtParcela.getText().trim();
            String raza = vista.txtRaza.getText().trim();

            if (chipText.isEmpty() || apodo.isEmpty() || parcelaText.isEmpty() || raza.isEmpty()) {
                JOptionPane.showMessageDialog(vista, "Rellena todos los campos.", "Atención", JOptionPane.WARNING_MESSAGE);
                return;
            }

            int chip = Integer.parseInt(chipText);
            int parcela = Integer.parseInt(parcelaText);

            Animal a = new Animal(chip, apodo, parcela, raza);
            dao.insertar(a);
            cargarDatos();
            limpiarCampos();
            JOptionPane.showMessageDialog(vista, "Animal insertado correctamente.");
        } catch (NumberFormatException nfe) {
            JOptionPane.showMessageDialog(vista, "Chip y Parcela deben ser números.", "Error", JOptionPane.ERROR_MESSAGE);
        } catch (Exception ex) {
            JOptionPane.showMessageDialog(vista, "Error al insertar: " + ex.getMessage(), "Error", JOptionPane.ERROR_MESSAGE);
        }
    }

    private void actualizar() {
        try {
            int fila = vista.tabla.getSelectedRow();
            if (fila < 0) {
                JOptionPane.showMessageDialog(vista, "Selecciona una fila para actualizar.", "Atención", JOptionPane.WARNING_MESSAGE);
                return;
            }

            String chipText = vista.txtChip.getText().trim();
            String apodo = vista.txtApodo.getText().trim();
            String parcelaText = vista.txtParcela.getText().trim();
            String raza = vista.txtRaza.getText().trim();

            if (chipText.isEmpty()) {
                JOptionPane.showMessageDialog(vista, "El número de chip es obligatorio.", "Atención", JOptionPane.WARNING_MESSAGE);
                return;
            }

            int chip = Integer.parseInt(chipText);
            Animal a = dao.buscarPorChip(chip);
            if (a == null) {
                JOptionPane.showMessageDialog(vista, "Animal no encontrado (chip=" + chip + ").", "Error", JOptionPane.ERROR_MESSAGE);
                return;
            }

            a.setApodo(apodo);
            a.setParcela(Integer.parseInt(parcelaText));
            a.setRaza(raza);

            dao.actualizar(a);
            cargarDatos();
            limpiarCampos();
            JOptionPane.showMessageDialog(vista, "Animal actualizado correctamente.");
        } catch (NumberFormatException nfe) {
            JOptionPane.showMessageDialog(vista, "Chip y Parcela deben ser números.", "Error", JOptionPane.ERROR_MESSAGE);
        } catch (Exception ex) {
            JOptionPane.showMessageDialog(vista, "Error al actualizar: " + ex.getMessage(), "Error", JOptionPane.ERROR_MESSAGE);
        }
    }

    private void eliminar() {
        try {
            int fila = vista.tabla.getSelectedRow();
            if (fila < 0) {
                JOptionPane.showMessageDialog(vista, "Selecciona una fila para eliminar.", "Atención", JOptionPane.WARNING_MESSAGE);
                return;
            }

            int chip = Integer.parseInt(vista.tabla.getValueAt(fila, 0).toString());
            int confirmar = JOptionPane.showConfirmDialog(vista,
                    "¿Seguro que deseas eliminar el animal con chip " + chip + "?",
                    "Confirmar eliminación", JOptionPane.YES_NO_OPTION);
            if (confirmar != JOptionPane.YES_OPTION) return;

            dao.eliminar(chip);
            cargarDatos();
            limpiarCampos();
            JOptionPane.showMessageDialog(vista, "Animal eliminado correctamente.");
        } catch (NumberFormatException nfe) {
            JOptionPane.showMessageDialog(vista, "Chip inválido.", "Error", JOptionPane.ERROR_MESSAGE);
        } catch (Exception ex) {
            JOptionPane.showMessageDialog(vista, "Error al eliminar: " + ex.getMessage(), "Error", JOptionPane.ERROR_MESSAGE);
        }
    }

    private void cargarDatos() {
        vista.modeloTabla.setRowCount(0);
        List<Animal> animales = dao.listar();
        for (Animal a : animales) {
            vista.modeloTabla.addRow(new Object[]{
                    a.getNumeroChip(),
                    a.getApodo(),
                    a.getParcela(),
                    a.getRaza()
            });
        }
    }

    private void limpiarCampos() {
        vista.txtChip.setText("");
        vista.txtApodo.setText("");
        vista.txtParcela.setText("");
        vista.txtRaza.setText("");
        vista.txtChip.setEnabled(true);
        vista.tabla.clearSelection();
    }
}
