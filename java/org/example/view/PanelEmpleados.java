package org.example.view;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.awt.*;

public class PanelEmpleados extends JPanel {
    public JTextField txtDni, txtNombre, txtExperiencia;
    public JComboBox<org.example.model.Profesion> comboProfesion;
    public JButton btnInsertar, btnActualizar, btnEliminar;
    public JTable tabla;
    public DefaultTableModel modeloTabla;

    public PanelEmpleados() {
        setLayout(new BorderLayout(10, 10));

        // --- Panel de formulario ---
        JPanel panelFormulario = new JPanel(new GridBagLayout());
        panelFormulario.setBorder(BorderFactory.createTitledBorder("Datos del Empleado"));
        GridBagConstraints gbc = new GridBagConstraints();
        gbc.insets = new Insets(5, 5, 5, 5); // márgenes
        gbc.fill = GridBagConstraints.HORIZONTAL;

        // Fila 1: DNI
        gbc.gridx = 0; gbc.gridy = 0;
        panelFormulario.add(new JLabel("DNI:"), gbc);
        txtDni = new JTextField(15);
        gbc.gridx = 1;
        panelFormulario.add(txtDni, gbc);

        // Fila 2: Nombre
        gbc.gridx = 0; gbc.gridy = 1;
        panelFormulario.add(new JLabel("Nombre:"), gbc);
        txtNombre = new JTextField(15);
        gbc.gridx = 1;
        panelFormulario.add(txtNombre, gbc);

        // Fila 3: Profesión
        gbc.gridx = 0; gbc.gridy = 2;
        panelFormulario.add(new JLabel("Profesión:"), gbc);
        comboProfesion = new JComboBox<>(org.example.model.Profesion.values());
        gbc.gridx = 1;
        panelFormulario.add(comboProfesion, gbc);

        // Fila 4: Experiencia
        gbc.gridx = 0; gbc.gridy = 3;
        panelFormulario.add(new JLabel("Experiencia:"), gbc);
        txtExperiencia = new JTextField(15);
        gbc.gridx = 1;
        panelFormulario.add(txtExperiencia, gbc);

        // Fila 5: Botones
        JPanel panelBotones = new JPanel(new FlowLayout(FlowLayout.CENTER, 15, 5));
        btnInsertar = new JButton("Insertar");
        btnActualizar = new JButton("Actualizar");
        btnEliminar = new JButton("Eliminar");
        panelBotones.add(btnInsertar);
        panelBotones.add(btnActualizar);
        panelBotones.add(btnEliminar);

        gbc.gridx = 0; gbc.gridy = 4; gbc.gridwidth = 2;
        panelFormulario.add(panelBotones, gbc);

        add(panelFormulario, BorderLayout.NORTH);

        // --- Tabla ---
        modeloTabla = new DefaultTableModel(new String[]{"DNI", "Nombre", "Profesión", "Experiencia"}, 0);
        tabla = new JTable(modeloTabla);
        add(new JScrollPane(tabla), BorderLayout.CENTER);
    }
}
