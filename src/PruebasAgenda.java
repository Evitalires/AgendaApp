import java.util.Scanner;

public class PruebasAgenda {
    Agenda agenda;

    public PruebasAgenda(Agenda agenda) {
        this.agenda = agenda;
    }
    void inicio(){
        System.out.println("--- Bienvenido al menu de pruebas Agenda ---");
        probarFunciones();

    }
    void probarFunciones() {
        int opcion = 0;
        Scanner sc = new Scanner(System.in);

        do {
            System.out.println("Escriba el numero de la funcion para testear:");
            System.out.println("1. Añadir Contacto.");
            System.out.println("2. Validar si existe contacto.");
            System.out.println("3. Listar Contactos.");
            System.out.println("4. Buscar Contacto por nombre.");
            System.out.println("5. Eliminar Contacto.");
            System.out.println("6. Consultar estado agenda.");
            System.out.println("7. Consultar espacios restante agenda.");
            System.out.println("9. Salir del menu de pruebas.");
            opcion = sc.nextInt();

            switch (opcion) {
                case 1:
                    agenda.anadirContacto();
                    break;
                case 2:
                    agenda.existeContacto();
                    break;
                case 3:
                    agenda.listarContactos();
                    break;
                case 4:
                    agenda.buscarContacto();
                    break;
                case 5:
                    agenda.eliminarContacto();
                    break;
                case 6:
                    agenda.agendaLLena();
                    break;
                case 7:
                    agenda.espacioLibres();
                    break;
            }
        } while (opcion != 9);
        sc.close();

    }
}
