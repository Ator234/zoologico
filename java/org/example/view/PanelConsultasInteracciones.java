package org.example.view;

import org.example.model.Profesion;
import org.example.model.Animal;
import org.example.model.Empleado;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.awt.*;

public class PanelConsultasInteracciones extends JPanel {

    // Controles
    public JComboBox<Animal> comboAnimal;            // selecciona animal (o "Todos" como null)
    public JComboBox<Empleado> comboEmpleado;        // selecciona empleado (o "Todos" como null)
    public JComboBox<Profesion> comboProfesion;      // selecciona profesion (o "Todos" como null)
    public JTextField txtFechaBuscar;

    // Botones
    public JButton btnInteraccionesAnimal;
    public JButton btnInteraccionesEmpleado;
    public JButton btnSaludAnimal;
    public JButton btnEmpleadosPorProfesion;
    public JButton btnInteraccionesPorFecha;
    public JButton btnAnimalesSinAlimentacion;

    // Tabla
    public JTable tabla;
    public DefaultTableModel modeloTabla;

    public PanelConsultasInteracciones() {
        setLayout(new BorderLayout(10, 10));
        setBorder(BorderFactory.createEmptyBorder(10, 10, 10, 10));

        JPanel filtros = new JPanel(new GridBagLayout());
        filtros.setBorder(BorderFactory.createTitledBorder("Consultas"));
        GridBagConstraints gbc = new GridBagConstraints();
        gbc.insets = new Insets(6, 6, 6, 6);
        gbc.fill = GridBagConstraints.HORIZONTAL;

        // Fila 0: Animal (combo) + botones Interacciones animal / Salud animal
        gbc.gridx = 0; gbc.gridy = 0;
        filtros.add(new JLabel("Animal (Chip):"), gbc);
        comboAnimal = new JComboBox<>(); // se rellena desde el controller
        gbc.gridx = 1; filtros.add(comboAnimal, gbc);

        btnInteraccionesAnimal = new JButton("Interacciones del Animal");
        btnSaludAnimal = new JButton("Controles de Salud (Animal)");
        JPanel pRow0Btns = new JPanel(new FlowLayout(FlowLayout.LEFT, 8, 0));
        pRow0Btns.add(btnInteraccionesAnimal);
        pRow0Btns.add(btnSaludAnimal);
        gbc.gridx = 2; gbc.gridwidth = 2; filtros.add(pRow0Btns, gbc);
        gbc.gridwidth = 1;

        // Fila 1: Empleado (combo) + boton interacciones empleado
        gbc.gridx = 0; gbc.gridy = 1;
        filtros.add(new JLabel("Empleado (DNI):"), gbc);
        comboEmpleado = new JComboBox<>(); // se rellena desde el controller
        gbc.gridx = 1; filtros.add(comboEmpleado, gbc);

        btnInteraccionesEmpleado = new JButton("Interacciones del Empleado");
        gbc.gridx = 2; gbc.gridwidth = 2; filtros.add(btnInteraccionesEmpleado, gbc);
        gbc.gridwidth = 1;

        // Fila 2: Profesion (combo) + boton empleados por profesion
        gbc.gridx = 0; gbc.gridy = 2;
        filtros.add(new JLabel("Profesión:"), gbc);
        comboProfesion = new JComboBox<>(); // se rellena desde el controller (Profesion.values)
        gbc.gridx = 1; filtros.add(comboProfesion, gbc);

        btnEmpleadosPorProfesion = new JButton("Empleados por Profesión");
        gbc.gridx = 2; gbc.gridwidth = 2; filtros.add(btnEmpleadosPorProfesion, gbc);
        gbc.gridwidth = 1;

        // Fila 3: Fecha + botones por fecha y animales sin alimentación
        gbc.gridx = 0; gbc.gridy = 3;
        filtros.add(new JLabel("Fecha (YYYY-MM-DD):"), gbc);
        txtFechaBuscar = new JTextField();
        gbc.gridx = 1; filtros.add(txtFechaBuscar, gbc);

        btnInteraccionesPorFecha = new JButton("Interacciones por Fecha");
        btnAnimalesSinAlimentacion = new JButton("Animales sin Alimentación (día)");
        JPanel pRow3Btns = new JPanel(new FlowLayout(FlowLayout.LEFT, 8, 0));
        pRow3Btns.add(btnInteraccionesPorFecha);
        pRow3Btns.add(btnAnimalesSinAlimentacion);
        gbc.gridx = 2; gbc.gridwidth = 2; filtros.add(pRow3Btns, gbc);
        gbc.gridwidth = 1;

        add(filtros, BorderLayout.NORTH);

        // Tabla de resultados (modelo inicial por defecto)
        modeloTabla = new DefaultTableModel(new String[]{"ID", "Fecha", "Motivo", "Animal", "Empleado"}, 0);
        tabla = new JTable(modeloTabla);
        tabla.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
        add(new JScrollPane(tabla), BorderLayout.CENTER);

        // Renderer por defecto para mostrar texto útil cuando combos tengan null (-> "-- Todos --")
        comboAnimal.setRenderer(new DefaultListCellRenderer() {
            @Override
            public Component getListCellRendererComponent(JList<?> list, Object value, int index,
                                                          boolean isSelected, boolean cellHasFocus) {
                String text = (value == null) ? "-- Todos --" : value.toString();
                return super.getListCellRendererComponent(list, text, index, isSelected, cellHasFocus);
            }
        });
        comboEmpleado.setRenderer(new DefaultListCellRenderer() {
            @Override
            public Component getListCellRendererComponent(JList<?> list, Object value, int index,
                                                          boolean isSelected, boolean cellHasFocus) {
                String text = (value == null) ? "-- Todos --" : value.toString();
                return super.getListCellRendererComponent(list, text, index, isSelected, cellHasFocus);
            }
        });
        comboProfesion.setRenderer(new DefaultListCellRenderer() {
            @Override
            public Component getListCellRendererComponent(JList<?> list, Object value, int index,
                                                          boolean isSelected, boolean cellHasFocus) {
                String text = (value == null) ? "-- Todos --" : value.toString();
                return super.getListCellRendererComponent(list, text, index, isSelected, cellHasFocus);
            }
        });
    }
}
