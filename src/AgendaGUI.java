package com.agenda;// VA EL PACKAGE

import javax.swing.*;
import java.awt.*;
import java.util.List;

public class AgendaGUI extends JFrame {
    private JTextField txtNombre;
    private JTextField txtApellido;
    private JTextField txtTelefono;
    private JButton btnAgregar;
    private JButton btnBuscar;
    private JButton btnListar;
    private JButton btnEliminar;
    private JButton btnModificar;
    private JTextArea areaResultados;

    private Agenda agenda;

    public AgendaGUI() {
        agenda = new Agenda(); // tamaño por defecto

        setTitle("Agenda Telefónica");
        setSize(600, 500);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLocationRelativeTo(null);

        initComponents();
        setVisible(true);
    }

    private void initComponents() {
        // Panel principal con BorderLayout
        JPanel panel = new JPanel(new BorderLayout(10,10));

        // --- Panel de formulario ---
        JPanel panelForm = new JPanel(new GridLayout(3,2,10,10));
        txtNombre = new JTextField(15);
        txtApellido = new JTextField(15);
        txtTelefono = new JTextField(10);

        panelForm.add(new JLabel("Nombre:"));
        panelForm.add(txtNombre);
        panelForm.add(new JLabel("Apellido:"));
        panelForm.add(txtApellido);
        panelForm.add(new JLabel("Teléfono:"));
        panelForm.add(txtTelefono);

        // --- Panel de botones ---
        JPanel panelBotones = new JPanel(new GridLayout(2,3,10,10));
        btnAgregar = new JButton("Agregar");
        btnBuscar = new JButton("Buscar");
        btnListar = new JButton("Listar");
        btnEliminar = new JButton("Eliminar");
        btnModificar = new JButton("Modificar Teléfono");

        panelBotones.add(btnAgregar);
        panelBotones.add(btnBuscar);
        panelBotones.add(btnListar);
        panelBotones.add(btnEliminar);
        panelBotones.add(btnModificar);

        // --- Área de resultados ---
        areaResultados = new JTextArea(12, 40);
        areaResultados.setEditable(false);
        areaResultados.setFont(new Font("Monospaced", Font.PLAIN, 14));
        JScrollPane scroll = new JScrollPane(areaResultados);

        // --- Añadir al panel principal ---
        panel.add(panelForm, BorderLayout.NORTH);
        panel.add(panelBotones, BorderLayout.CENTER);
        panel.add(scroll, BorderLayout.SOUTH);

        add(panel);

        // Eventos
        btnAgregar.addActionListener(e -> agregarContacto());
        btnBuscar.addActionListener(e -> buscarContacto());
        btnListar.addActionListener(e -> listarContactos());
        btnEliminar.addActionListener(e -> eliminarContacto());
        btnModificar.addActionListener(e -> modificarTelefono());
    }

    // Métodos de acción (igual que antes)
    private void agregarContacto() {
        try {
            Contacto c = new Contacto(
                    txtNombre.getText(),
                    txtApellido.getText(),
                    txtTelefono.getText()
            );
            if (agenda.añadirContacto(c)) {
                areaResultados.setText("✅ Contacto agregado correctamente.");
            } else {
                areaResultados.setText("⚠️ No se pudo agregar (duplicado o agenda llena).");
            }
        } catch (Exception ex) {
            areaResultados.setText("Error: " + ex.getMessage());
        }
    }

    private void buscarContacto() {
        Contacto c = agenda.buscaContacto(txtNombre.getText(), txtApellido.getText());
        if (c != null) {
            areaResultados.setText("📞 Teléfono: " + c.getTelefono());
        } else {
            areaResultados.setText("❌ Contacto no encontrado.");
        }
    }

    private void listarContactos() {
        List<Contacto> lista = agenda.listarContactos();
        StringBuilder sb = new StringBuilder("📋 Contactos:\n");
        for (Contacto c : lista) {
            sb.append("• ").append(c).append("\n");
        }
        areaResultados.setText(sb.toString());
    }

    private void eliminarContacto() {
        Contacto c = new Contacto(txtNombre.getText(), txtApellido.getText(), "0000000000");
        if (agenda.eliminarContacto(c)) {
            areaResultados.setText("🗑️ Contacto eliminado.");
        } else {
            areaResultados.setText("❌ No se encontró el contacto.");
        }
    }

    private void modificarTelefono() {
        if (agenda.modificarTelefono(txtNombre.getText(), txtApellido.getText(), txtTelefono.getText())) {
            areaResultados.setText("✏️ Teléfono actualizado.");
        } else {
            areaResultados.setText("❌ No se encontró el contacto.");
        }
    }

    public static void main(String[] args) {
        SwingUtilities.invokeLater(AgendaGUI::new);
    }
}

