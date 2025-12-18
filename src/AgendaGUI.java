// Archivo: AgendaGUI.java
import javax.swing.*;
import javax.swing.border.EmptyBorder;
import java.awt.*;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.awt.geom.RoundRectangle2D;
import java.util.List;

public class AgendaGUI extends JFrame {

    // Lógica
    private Agenda agenda;

    // Componentes UI globales
    private JPanel cardsPanel;
    private JTextField searchField;
    private JLabel subtitleLabel;

    // Colores del diseño
    private static final Color BG_COLOR = new Color(248, 249, 250);
    private static final Color PRIMARY_BLUE = new Color(26, 115, 232);
    private static final Color TEXT_DARK = new Color(32, 33, 36);
    private static final Color TEXT_GREY = new Color(95, 99, 104);

    public AgendaGUI() {
        agenda = new Agenda(); // Instancia tu lógica

        setTitle("Agenda Telefónica Moderna");
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setSize(520, 750);
        setLocationRelativeTo(null);
        getContentPane().setBackground(BG_COLOR);
        setLayout(new BorderLayout());

        // --- Panel Principal Scrollable ---
        JPanel mainContainer = new JPanel();
        mainContainer.setLayout(new BoxLayout(mainContainer, BoxLayout.Y_AXIS));
        mainContainer.setBackground(BG_COLOR);
        mainContainer.setBorder(new EmptyBorder(20, 20, 20, 20));

        // 1. Header
        mainContainer.add(createHeader());
        mainContainer.add(Box.createRigidArea(new Dimension(0, 20)));

        // 2. Buscador
        mainContainer.add(createSearchBar());
        mainContainer.add(Box.createRigidArea(new Dimension(0, 20)));

        // 3. Contenedor de Tarjetas (Donde se listan los contactos)
        cardsPanel = new JPanel();
        cardsPanel.setLayout(new BoxLayout(cardsPanel, BoxLayout.Y_AXIS));
        cardsPanel.setBackground(BG_COLOR);

        mainContainer.add(cardsPanel);

        // ScrollPane
        JScrollPane scrollPane = new JScrollPane(mainContainer);
        scrollPane.setBorder(null);
        scrollPane.getVerticalScrollBar().setUnitIncrement(16);
        add(scrollPane, BorderLayout.CENTER);

        // Cargar contactos iniciales
        refreshContactList(agenda.listarContactos());
    }

    // --- LÓGICA: Refrescar la lista visual ---
    private void refreshContactList(List<Contacto> lista) {
        cardsPanel.removeAll();

        if (lista.isEmpty()) {
            JLabel emptyLabel = new JLabel("No se encontraron contactos.");
            emptyLabel.setFont(new Font("Segoe UI", Font.ITALIC, 14));
            emptyLabel.setForeground(TEXT_GREY);
            emptyLabel.setAlignmentX(Component.CENTER_ALIGNMENT);
            cardsPanel.add(emptyLabel);
        } else {
            for (Contacto c : lista) {
                cardsPanel.add(createContactCard(c));
                cardsPanel.add(Box.createRigidArea(new Dimension(0, 15)));
            }
        }

        // Actualizar contador
        subtitleLabel.setText(lista.size() + " contactos");

        cardsPanel.revalidate();
        cardsPanel.repaint();
    }

    // --- LÓGICA: Diálogo para Agregar ---
    private void mostrarDialogoAgregar() {
        JDialog dialog = new JDialog(this, "Nuevo Contacto", true);
        dialog.setSize(350, 300);
        dialog.setLocationRelativeTo(this);
        dialog.setLayout(new GridBagLayout());
        dialog.getContentPane().setBackground(Color.WHITE);

        GridBagConstraints gbc = new GridBagConstraints();
        gbc.insets = new Insets(5, 5, 5, 5);
        gbc.fill = GridBagConstraints.HORIZONTAL;

        JTextField txtNombre = new JTextField(15);
        JTextField txtApellido = new JTextField(15);
        JTextField txtCelular = new JTextField(15);

        // Componentes del formulario
        gbc.gridx=0; gbc.gridy=0; dialog.add(new JLabel("Nombre:"), gbc);
        gbc.gridx=1; gbc.gridy=0; dialog.add(txtNombre, gbc);

        gbc.gridx=0; gbc.gridy=1; dialog.add(new JLabel("Apellido:"), gbc);
        gbc.gridx=1; gbc.gridy=1; dialog.add(txtApellido, gbc);

        gbc.gridx=0; gbc.gridy=2; dialog.add(new JLabel("Celular (3xxxxxxxxx):"), gbc);
        gbc.gridx=1; gbc.gridy=2; dialog.add(txtCelular, gbc);

        JButton btnGuardar = new JButton("Guardar");
        btnGuardar.setBackground(PRIMARY_BLUE);
        btnGuardar.setForeground(Color.WHITE);

        btnGuardar.addActionListener(e -> {
            Contacto nuevo = new Contacto(txtNombre.getText(), txtApellido.getText(), txtCelular.getText());

            if (nuevo.getCelular().equals("Error")) {
                JOptionPane.showMessageDialog(dialog, "Formato de celular incorrecto.\nDebe iniciar con 3 y tener 10 dígitos.", "Error", JOptionPane.ERROR_MESSAGE);
            } else {
                if(agenda.añadirContacto(nuevo)){
                    refreshContactList(agenda.listarContactos());
                    dialog.dispose();
                } else {
                    JOptionPane.showMessageDialog(dialog, "El contacto ya existe o hubo un error.", "Error", JOptionPane.ERROR_MESSAGE);
                }
            }
        });

        gbc.gridx=0; gbc.gridy=3; gbc.gridwidth=2;
        gbc.insets = new Insets(20, 5, 5, 5);
        dialog.add(btnGuardar, gbc);

        dialog.setVisible(true);
    }

    // --- LÓGICA: Diálogo para Modificar Teléfono ---
    private void mostrarDialogoModificar(Contacto c) {
        String nuevoTel = JOptionPane.showInputDialog(this, "Nuevo teléfono para " + c.getNombre(), c.getCelular());
        if (nuevoTel != null && !nuevoTel.isEmpty()) {
            // Validamos manualmente usando un objeto temporal
            Contacto temp = new Contacto("Temp", "Temp", nuevoTel);
            if (temp.getCelular().equals("Error")) {
                JOptionPane.showMessageDialog(this, "Formato inválido.");
            } else {
                agenda.modificarTelefono(c.getNombre(), c.getApellido(), nuevoTel);
                refreshContactList(agenda.listarContactos());
            }
        }
    }

    // --- UI: Header ---
    private JPanel createHeader() {
        JPanel header = new JPanel(new BorderLayout());
        header.setBackground(BG_COLOR);
        header.setMaximumSize(new Dimension(Integer.MAX_VALUE, 60)); // Limita la altura del header

        // Panel Izquierdo: Títulos
        JPanel titlePanel = new JPanel(new GridLayout(2, 1));
        titlePanel.setBackground(BG_COLOR);
        JLabel title = new JLabel("Contacts Agenda");
        title.setFont(new Font("Segoe UI", Font.BOLD, 22));

        subtitleLabel = new JLabel("0 contactos");
        titlePanel.add(title);
        titlePanel.add(subtitleLabel);

        // PANEL PARA EL BOTÓN (La solución)
        // Usamos FlowLayout.RIGHT para que el botón flote a la derecha y mantenga su tamaño
        JPanel buttonContainer = new JPanel(new FlowLayout(FlowLayout.RIGHT, 0, 10));
        buttonContainer.setBackground(BG_COLOR);

        JButton addButton = new RoundedButton("+ Add Contact");
        addButton.setBackground(PRIMARY_BLUE);
        addButton.setForeground(Color.WHITE);
        addButton.setPreferredSize(new Dimension(150, 40)); // Fijamos un tamaño lógico
        addButton.addActionListener(e -> mostrarDialogoAgregar());

        buttonContainer.add(addButton);

        header.add(titlePanel, BorderLayout.WEST);
        header.add(buttonContainer, BorderLayout.EAST); // Agregamos el contenedor, no el botón directo

        return header;
    }

    // --- UI: Search Bar ---
    private JPanel createSearchBar() {
        JPanel panel = new JPanel(new BorderLayout());
        panel.setBackground(Color.WHITE);
        panel.setBorder(BorderFactory.createCompoundBorder(
                BorderFactory.createLineBorder(new Color(218, 220, 224), 1, true),
                new EmptyBorder(5, 10, 5, 10)
        ));
        panel.setMaximumSize(new Dimension(Integer.MAX_VALUE, 45));

        searchField = new JTextField();
        searchField.setBorder(null);
        searchField.setFont(new Font("Segoe UI", Font.PLAIN, 14));

        // ACCIÓN DE BÚSQUEDA (Al escribir o presionar enter)
        searchField.addKeyListener(new KeyAdapter() {
            @Override
            public void keyReleased(KeyEvent e) {
                String text = searchField.getText();
                if (text.isEmpty()) {
                    refreshContactList(agenda.listarContactos());
                } else {
                    // Busca exacto o filtra (aquí usaremos buscarContacto de Agenda)
                    Contacto encontrado = agenda.buscarContacto(text);
                    if (encontrado != null) {
                        refreshContactList(List.of(encontrado));
                    } else {
                        // Si quieres filtrado parcial, deberías implementarlo en Agenda
                        // Por ahora mostramos lista vacía si no hay coincidencia exacta
                        refreshContactList(List.of());
                    }
                }
            }
        });

        JLabel icon = new JLabel("🔍 ");
        icon.setForeground(TEXT_GREY);
        panel.add(icon, BorderLayout.WEST);
        panel.add(searchField, BorderLayout.CENTER);

        return panel;
    }

    // --- UI: Tarjeta de Contacto Individual ---
    private JPanel createContactCard(Contacto contacto) {
        JPanel card = new JPanel(new BorderLayout()) {
            @Override // Pintar bordes redondeados y fondo blanco
            protected void paintComponent(Graphics g) {
                super.paintComponent(g);
                Graphics2D g2 = (Graphics2D) g;
                g2.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);
                g2.setColor(Color.WHITE);
                g2.fillRoundRect(0, 0, getWidth()-1, getHeight()-1, 20, 20);
                g2.setColor(new Color(230, 230, 230));
                g2.drawRoundRect(0, 0, getWidth()-1, getHeight()-1, 20, 20);
            }
        };
        card.setBackground(BG_COLOR);
        card.setBorder(new EmptyBorder(15, 15, 15, 15));
        card.setMaximumSize(new Dimension(Integer.MAX_VALUE, 100));

        // Info Izquierda
        JPanel infoPanel = new JPanel(new GridLayout(2, 1));
        infoPanel.setOpaque(false);

        JLabel nameLabel = new JLabel(contacto.getNombreCompleto());
        nameLabel.setFont(new Font("Segoe UI", Font.BOLD, 16));
        nameLabel.setForeground(TEXT_DARK);

        JLabel phoneLabel = new JLabel("📞 " + contacto.getCelular());
        phoneLabel.setFont(new Font("Segoe UI", Font.PLAIN, 14));
        phoneLabel.setForeground(TEXT_GREY);

        infoPanel.add(nameLabel);
        infoPanel.add(phoneLabel);

        // Botones Derecha
        JPanel actionsPanel = new JPanel(new FlowLayout(FlowLayout.RIGHT));
        actionsPanel.setOpaque(false);

        JButton btnEdit = new JButton("✎");
        styleActionButton(btnEdit);
        btnEdit.setToolTipText("Modificar Teléfono");
        // ACCIÓN MODIFICAR
        btnEdit.addActionListener(e -> mostrarDialogoModificar(contacto));

        JButton btnDelete = new JButton("🗑");
        styleActionButton(btnDelete);
        btnDelete.setToolTipText("Eliminar Contacto");
        btnDelete.setForeground(new Color(217, 48, 37)); // Rojo para borrar
        // ACCIÓN ELIMINAR
        btnDelete.addActionListener(e -> {
            int confirm = JOptionPane.showConfirmDialog(this, "¿Eliminar a " + contacto.getNombre() + "?", "Confirmar", JOptionPane.YES_NO_OPTION);
            if(confirm == JOptionPane.YES_OPTION) {
                agenda.eliminarContacto(contacto);
                refreshContactList(agenda.listarContactos());
            }
        });

        actionsPanel.add(btnEdit);
        actionsPanel.add(btnDelete);

        card.add(infoPanel, BorderLayout.CENTER);
        card.add(actionsPanel, BorderLayout.EAST);

        return card;
    }

    private void styleActionButton(JButton btn) {
        btn.setBorderPainted(false);
        btn.setContentAreaFilled(false);
        btn.setFocusPainted(false);
        btn.setFont(new Font("SansSerif", Font.BOLD, 18));
        btn.setCursor(new Cursor(Cursor.HAND_CURSOR));
    }

    // --- Helper UI: Botón Redondeado ---
    class RoundedButton extends JButton {
        public RoundedButton(String text) {
            super(text);
            setContentAreaFilled(false);
            setCursor(new Cursor(Cursor.HAND_CURSOR));
        }
        @Override
        protected void paintComponent(Graphics g) {
            Graphics2D g2 = (Graphics2D) g.create();
            g2.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);
            g2.setColor(getBackground());
            g2.fill(new RoundRectangle2D.Float(0, 0, getWidth(), getHeight(), 20, 20));
            g2.setColor(getForeground());
            FontMetrics fm = g2.getFontMetrics();
            int x = (getWidth() - fm.stringWidth(getText())) / 2;
            int y = (getHeight() + fm.getAscent() - fm.getDescent()) / 2;
            g2.drawString(getText(), x, y);
            g2.dispose();
        }
    }

    public static void main(String[] args) {
        SwingUtilities.invokeLater(() -> new AgendaGUI().setVisible(true));
    }
}