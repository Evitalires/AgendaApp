//TIP To <b>Run</b> code, press <shortcut actionId="Run"/> or
// click the <icon src="AllIcons.Actions.Execute"/> icon in the gutter.
public class Main {
    public static void main(String[] args) {

        Agenda agenda1 = new Agenda();
        Contacto contacto1 = new Contacto("sebastian", "perez", "3132132121");
        agenda1.añadirContacto(contacto1);

        // Le pasamos 'agenda1' para que la GUI trabaje sobre ella
        javax.swing.SwingUtilities.invokeLater(() -> {
            AgendaGUI gui = new AgendaGUI(agenda1);
            gui.setVisible(true);
        });

        PruebasAgenda pruebasAgenda1 = new PruebasAgenda(agenda1);
        pruebasAgenda1.iniciar();

    }
}