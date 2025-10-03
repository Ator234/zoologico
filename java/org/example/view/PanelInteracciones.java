package org.example.view;

import org.example.model.Animal;
import org.example.model.Empleado;
import org.example.model.Motivo;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.awt.*;

public class PanelInteracciones extends JPanel {
    public JTextField txtFecha;
    public JComboBox<Motivo> comboMotivo;
    public JComboBox<Animal> comboAnimal;
    public JComboBox<Empleado> comboEmpleado;

    public JButton btnInsertar;
    public JButton btnActualizar;
    public JButton btnEliminar;

    public JTable tabla;
    public DefaultTableModel modeloTabla;

    public PanelInteracciones() {
        setLayout(new BorderLayout(10, 10));
        setBorder(BorderFactory.createEmptyBorder(10, 10, 10, 10));

        // ---------- Panel de formulario ----------
        JPanel form = new JPanel(new GridBagLayout());
        GridBagConstraints gbc = new GridBagConstraints();
        gbc.insets = new Insets(5, 5, 5, 5);
        gbc.fill = GridBagConstraints.HORIZONTAL;

        JLabel lblFecha = new JLabel("Fecha (YYYY-MM-DD):");
        JLabel lblMotivo = new JLabel("Motivo:");
        JLabel lblAnimal = new JLabel("Animal (Chip):");
        JLabel lblEmpleado = new JLabel("Empleado (DNI):");

        txtFecha = new JTextField(15);
        comboMotivo = new JComboBox<>(Motivo.values());
        comboAnimal = new JComboBox<>();
        comboEmpleado = new JComboBox<>();

        gbc.gridx = 0; gbc.gridy = 0; form.add(lblFecha, gbc);
        gbc.gridx = 1; gbc.gridy = 0; form.add(txtFecha, gbc);

        gbc.gridx = 0; gbc.gridy = 1; form.add(lblMotivo, gbc);
        gbc.gridx = 1; gbc.gridy = 1; form.add(comboMotivo, gbc);

        gbc.gridx = 0; gbc.gridy = 2; form.add(lblAnimal, gbc);
        gbc.gridx = 1; gbc.gridy = 2; form.add(comboAnimal, gbc);

        gbc.gridx = 0; gbc.gridy = 3; form.add(lblEmpleado, gbc);
        gbc.gridx = 1; gbc.gridy = 3; form.add(comboEmpleado, gbc);

        // ---------- Panel de botones ----------
        JPanel panelBotones = new JPanel(new FlowLayout(FlowLayout.CENTER, 10, 10));
        btnInsertar = new JButton("Insertar");
        btnActualizar = new JButton("Actualizar");
        btnEliminar = new JButton("Eliminar");
        panelBotones.add(btnInsertar);
        panelBotones.add(btnActualizar);
        panelBotones.add(btnEliminar);

        gbc.gridx = 0; gbc.gridy = 4; gbc.gridwidth = 2;
        form.add(panelBotones, gbc);

        add(form, BorderLayout.NORTH);

        // ---------- Tabla ----------
        modeloTabla = new DefaultTableModel(new String[]{"ID", "Fecha", "Motivo", "Animal", "Empleado"}, 0);
        tabla = new JTable(modeloTabla);
        tabla.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
        JScrollPane scroll = new JScrollPane(tabla);

        add(scroll, BorderLayout.CENTER);
    }
}
