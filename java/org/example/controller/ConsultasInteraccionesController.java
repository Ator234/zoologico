package org.example.controller;

import org.example.dao.AnimalDAO;
import org.example.dao.EmpleadoDAO;
import org.example.dao.InteraccionDAO;
import org.example.model.Animal;
import org.example.model.Empleado;
import org.example.model.Interaccion;
import org.example.model.Motivo;
import org.example.model.Profesion;
import org.example.view.PanelConsultasInteracciones;

import javax.swing.*;
import java.awt.*;
import java.time.LocalDate;
import java.util.List;
import java.util.stream.Collectors;

public class ConsultasInteraccionesController {

    private final InteraccionDAO dao;
    private final AnimalDAO animalDao;
    private final EmpleadoDAO empleadoDao;
    private final PanelConsultasInteracciones vista;

    public ConsultasInteraccionesController(PanelConsultasInteracciones vista) {
        this.dao = new InteraccionDAO();
        this.animalDao = new AnimalDAO();
        this.empleadoDao = new EmpleadoDAO();
        this.vista = vista;
        initController();
        cargarCombos();
    }

    private void initController() {
        vista.btnInteraccionesAnimal.addActionListener(e -> interaccionesDeAnimal());
        vista.btnInteraccionesEmpleado.addActionListener(e -> interaccionesDeEmpleado());
        vista.btnSaludAnimal.addActionListener(e -> interaccionesSaludAnimal());
        vista.btnEmpleadosPorProfesion.addActionListener(e -> empleadosPorProfesion());
        vista.btnInteraccionesPorFecha.addActionListener(e -> interaccionesPorFecha());
        vista.btnAnimalesSinAlimentacion.addActionListener(e -> animalesSinAlimentacion());
    }

    private void cargarCombos() {
        // Animales: añadimos un item null como "Todos", luego todos los animales
        vista.comboAnimal.removeAllItems();
        vista.comboAnimal.addItem(null); // representa "Todos"
        for (Animal a : animalDao.listar()) vista.comboAnimal.addItem(a);

        // Empleados: null + empleados
        vista.comboEmpleado.removeAllItems();
        vista.comboEmpleado.addItem(null);
        for (Empleado e : empleadoDao.listar()) vista.comboEmpleado.addItem(e);

        // Profesiones: null + enum values
        vista.comboProfesion.removeAllItems();
        vista.comboProfesion.addItem(null);
        for (Profesion p : Profesion.values()) vista.comboProfesion.addItem(p);
    }

    // Util: establece columnas para mostrar interacciones
    private void configurarTablaInteracciones() {
        vista.modeloTabla.setRowCount(0);
        vista.modeloTabla.setColumnIdentifiers(new Object[]{"ID", "Fecha", "Motivo", "Animal", "Empleado"});
    }

    private void configurarTablaAnimales() {
        vista.modeloTabla.setRowCount(0);
        vista.modeloTabla.setColumnIdentifiers(new Object[]{"Chip", "Apodo", "Raza", "Parcela"});
    }

    private void configurarTablaEmpleados() {
        vista.modeloTabla.setRowCount(0);
        vista.modeloTabla.setColumnIdentifiers(new Object[]{"DNI", "Nombre", "Profesión", "Experiencia"});
    }

    private void mostrarInteracciones(List<Interaccion> lista) {
        configurarTablaInteracciones();
        for (Interaccion i : lista) {
            vista.modeloTabla.addRow(new Object[]{
                    i.getId(),
                    i.getFecha(),
                    i.getMotivo(),
                    i.getAnimal() != null ? i.getAnimal().getNumeroChip() : null,
                    i.getEmpleado() != null ? i.getEmpleado().getDni() : null
            });
        }
    }

    private void interaccionesDeAnimal() {
        try {
            Animal seleccionado = (Animal) vista.comboAnimal.getSelectedItem();
            List<Interaccion> lista;
            if (seleccionado != null) {
                lista = dao.interaccionesDeAnimal(seleccionado.getNumeroChip());
            } else {
                // Si no hay selección, devolvemos todas las interacciones del DAO
                lista = dao.listar();
            }
            mostrarInteracciones(lista);
        } catch (Exception ex) {
            mostrarError(ex, "Error al obtener interacciones del animal");
        }
    }

    private void interaccionesDeEmpleado() {
        try {
            Empleado seleccionado = (Empleado) vista.comboEmpleado.getSelectedItem();
            List<Interaccion> lista;
            if (seleccionado != null) {
                lista = dao.interaccionesDeEmpleado(seleccionado.getDni());
            } else {
                lista = dao.listar();
            }
            mostrarInteracciones(lista);
        } catch (Exception ex) {
            mostrarError(ex, "Error al obtener interacciones del empleado");
        }
    }

    private void interaccionesSaludAnimal() {
        try {
            Animal seleccionado = (Animal) vista.comboAnimal.getSelectedItem();
            List<Interaccion> lista;
            if (seleccionado != null) {
                // si hay animal seleccionado -> usamos método específico
                lista = dao.interaccionesSaludDeAnimal(seleccionado.getNumeroChip());
            } else {
                // si no hay selección -> devolvemos todas las interacciones y filtramos por motivo
                lista = dao.listar()
                        .stream()
                        .filter(i -> i.getMotivo() == Motivo.CONTROLSALUD)
                        .collect(Collectors.toList());
            }
            mostrarInteracciones(lista);
        } catch (Exception ex) {
            mostrarError(ex, "Error al obtener controles de salud");
        }
    }

    private void empleadosPorProfesion() {
        try {
            Profesion p = (Profesion) vista.comboProfesion.getSelectedItem();
            List<Empleado> empleados;
            if (p != null) {
                empleados = dao.empleadosPorProfesion(p);
            } else {
                // si no hay profesion seleccionada -> devolvemos todos los empleados
                empleados = empleadoDao.listar();
            }

            configurarTablaEmpleados();
            for (Empleado e : empleados) {
                vista.modeloTabla.addRow(new Object[]{e.getDni(), e.getNombre(), e.getProfesion(), e.getExperiencia()});
            }
        } catch (Exception ex) {
            mostrarError(ex, "Error al obtener empleados por profesión");
        }
    }

    private void interaccionesPorFecha() {
        try {
            String texto = vista.txtFechaBuscar.getText().trim();
            LocalDate fecha;
            if (texto.isEmpty()) {
                fecha = LocalDate.now();
            } else {
                fecha = LocalDate.parse(texto); // String -> LocalDate
            }
            List<Interaccion> lista = dao.interaccionesPorFecha(fecha);
            mostrarInteracciones(lista);
        } catch (Exception ex) {
            mostrarError(ex, "Error al consultar interacciones por fecha (usa YYYY-MM-DD)");
        }
    }

    private void animalesSinAlimentacion() {
        try {
            String texto = vista.txtFechaBuscar.getText().trim();
            LocalDate fecha;
            if (texto.isEmpty()) {
                fecha = LocalDate.now();
            } else {
                fecha = LocalDate.parse(texto); // String -> LocalDate
            }
            List<Animal> animales = dao.animalesSinAlimentacionHoy(fecha);
            configurarTablaAnimales();
            for (Animal a : animales) {
                vista.modeloTabla.addRow(new Object[]{
                        a.getNumeroChip(),
                        a.getApodo(),
                        a.getRaza(),
                        a.getParcela()
                });
            }
        } catch (Exception ex) {
            mostrarError(ex, "Error al obtener animales sin alimentación (usa YYYY-MM-DD)");
        }
    }

    private void mostrarError(Exception ex, String titulo) {
        ex.printStackTrace();
        JOptionPane.showMessageDialog(vista, titulo + ": " + ex.getMessage(), "Error", JOptionPane.ERROR_MESSAGE);
    }
}
