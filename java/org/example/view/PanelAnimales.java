package org.example.view;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.awt.*;

public class PanelAnimales extends JPanel {
    public JTextField txtChip, txtApodo, txtParcela, txtRaza;
    public JButton btnInsertar, btnActualizar, btnEliminar;
    public JTable tabla;
    public DefaultTableModel modeloTabla;

    public PanelAnimales() {
        setLayout(new BorderLayout(10, 10));

        JPanel panelFormulario = new JPanel(new GridBagLayout());
        panelFormulario.setBorder(BorderFactory.createTitledBorder("Datos del Animal"));
        GridBagConstraints gbc = new GridBagConstraints();
        gbc.insets = new Insets(5, 5, 5, 5);
        gbc.fill = GridBagConstraints.HORIZONTAL;

        // Numero Chip
        gbc.gridx = 0; gbc.gridy = 0;
        panelFormulario.add(new JLabel("Número Chip:"), gbc);
        txtChip = new JTextField(15);
        gbc.gridx = 1;
        panelFormulario.add(txtChip, gbc);

        // Apodo
        gbc.gridx = 0; gbc.gridy = 1;
        panelFormulario.add(new JLabel("Apodo:"), gbc);
        txtApodo = new JTextField(15);
        gbc.gridx = 1;
        panelFormulario.add(txtApodo, gbc);

        // Parcela
        gbc.gridx = 0; gbc.gridy = 2;
        panelFormulario.add(new JLabel("Parcela:"), gbc);
        txtParcela = new JTextField(15);
        gbc.gridx = 1;
        panelFormulario.add(txtParcela, gbc);

        // Raza
        gbc.gridx = 0; gbc.gridy = 3;
        panelFormulario.add(new JLabel("Raza:"), gbc);
        txtRaza = new JTextField(15);
        gbc.gridx = 1;
        panelFormulario.add(txtRaza, gbc);

        // Botones
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

        modeloTabla = new DefaultTableModel(new String[]{"Chip", "Apodo", "Parcela", "Raza"}, 0);
        tabla = new JTable(modeloTabla);
        add(new JScrollPane(tabla), BorderLayout.CENTER);
    }
}
