package org.example.view;

import javax.swing.*;
import java.awt.*;

public class MainMenu extends JFrame {

    public JButton btnAnimales;
    public JButton btnEmpleados;
    public JButton btnInteracciones;
    public JButton btnConsultasInteracciones;  // ✅ Nuevo botón
    public JButton btnSalir;

    public MainMenu() {
        setTitle("Zoológico - Menú Principal");
        setSize(800, 450);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLocationRelativeTo(null);

        JPanel root = new JPanel(new BorderLayout(10, 10));
        root.setBorder(BorderFactory.createEmptyBorder(20, 20, 20, 20));

        JLabel lblTitle = new JLabel("GESTIÓN DE ZOOLÓGICO", SwingConstants.CENTER);
        lblTitle.setFont(new Font("Arial", Font.BOLD, 28));
        root.add(lblTitle, BorderLayout.NORTH);

        JPanel center = new JPanel(new GridLayout(1, 2, 20, 20));

        // Panel botones
        JPanel left = new JPanel();
        left.setLayout(new BoxLayout(left, BoxLayout.Y_AXIS));
        left.setBorder(BorderFactory.createEmptyBorder(10, 10, 10, 10));

        btnAnimales = new JButton("Gestión de Animales");
        btnAnimales.setMaximumSize(new Dimension(Integer.MAX_VALUE, 40));

        btnEmpleados = new JButton("Gestión de Empleados");
        btnEmpleados.setMaximumSize(new Dimension(Integer.MAX_VALUE, 40));

        btnInteracciones = new JButton("Gestión de Interacciones");
        btnInteracciones.setMaximumSize(new Dimension(Integer.MAX_VALUE, 40));

        btnConsultasInteracciones = new JButton("Consultas de Interacciones"); // ✅ Nuevo
        btnConsultasInteracciones.setMaximumSize(new Dimension(Integer.MAX_VALUE, 40));

        btnSalir = new JButton("Salir");
        btnSalir.setMaximumSize(new Dimension(Integer.MAX_VALUE, 40));

        left.add(btnAnimales);
        left.add(Box.createVerticalStrut(10));
        left.add(btnEmpleados);
        left.add(Box.createVerticalStrut(10));
        left.add(btnInteracciones);
        left.add(Box.createVerticalStrut(10));
        left.add(btnConsultasInteracciones); // ✅ Se agrega al menú
        left.add(Box.createVerticalStrut(10));
        left.add(btnSalir);

        // Panel derecho logo
        JPanel right = new JPanel(new BorderLayout());
        JLabel logo = new JLabel();
        logo.setHorizontalAlignment(SwingConstants.CENTER);
        java.net.URL imgURL = getClass().getResource("/images/cabarceno.jpeg");
        if (imgURL != null) {
            ImageIcon icon = new ImageIcon(imgURL);
            Image scaled = icon.getImage().getScaledInstance(400, 300, Image.SCALE_SMOOTH);
            logo.setIcon(new ImageIcon(scaled));
        }
        right.add(logo, BorderLayout.CENTER);

        center.add(left);
        center.add(right);
        root.add(center, BorderLayout.CENTER);

        add(root);

        // --- ActionListeners ---
        btnAnimales.addActionListener(e -> abrirPanelAnimales());
        btnEmpleados.addActionListener(e -> abrirPanelEmpleados());
        btnInteracciones.addActionListener(e -> abrirPanelInteracciones());
        btnConsultasInteracciones.addActionListener(e -> abrirPanelConsultasInteracciones()); // ✅ Nuevo
        btnSalir.addActionListener(e -> salirAplicacion());
    }

    private void abrirPanelAnimales() {
        JFrame frame = new JFrame("Gestión de Animales");
        PanelAnimales panel = new PanelAnimales();

        // 🔹 Inicializamos el controlador de animales
        new org.example.controller.AnimalController(panel);

        frame.setContentPane(panel);
        frame.setSize(700, 500);
        frame.setLocationRelativeTo(null);
        frame.setVisible(true);
    }

    private void abrirPanelEmpleados() {
        JFrame frame = new JFrame("Gestión de Empleados");
        PanelEmpleados panel = new PanelEmpleados();

        // 🔹 Inicializamos el controlador de empleados
        new org.example.controller.EmpleadoController(panel);

        frame.setContentPane(panel);
        frame.setSize(700, 500);
        frame.setLocationRelativeTo(null);
        frame.setVisible(true);
    }

    private void abrirPanelInteracciones() {
        JFrame frame = new JFrame("Gestión de Interacciones");
        PanelInteracciones panel = new PanelInteracciones();

        // 🔹 Inicializamos el controlador de interacciones
        new org.example.controller.InteraccionController(panel);

        frame.setContentPane(panel);
        frame.setSize(700, 500);
        frame.setLocationRelativeTo(null);
        frame.setVisible(true);
    }

    private void abrirPanelConsultasInteracciones() { // ✅ Nuevo método
        JFrame frame = new JFrame("Consultas de Interacciones");
        PanelConsultasInteracciones panel = new PanelConsultasInteracciones();

        // 🔹 Inicializamos el controlador de consultas
        new org.example.controller.ConsultasInteraccionesController(panel);

        frame.setContentPane(panel);
        frame.setSize(800, 500);
        frame.setLocationRelativeTo(null);
        frame.setVisible(true);
    }

    private void salirAplicacion() {
        int confirmar = JOptionPane.showConfirmDialog(this, "¿Desea salir de la aplicación?", "Salir", JOptionPane.YES_NO_OPTION);
        if (confirmar == JOptionPane.YES_OPTION) {
            dispose();
            System.exit(0);
        }
    }
}
