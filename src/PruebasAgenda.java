import java.util.Scanner;

public class PruebasAgenda {
    Agenda agenda;

    public PruebasAgenda(Agenda agenda) {
        this.agenda = agenda;
    }
    void inicio(){
        int opcion = 0;
        Scanner sc = new Scanner(System.in);
        System.out.println("--- Bienvenido al menu de pruebas Agenda ---");
        System.out.println("Escriba el numero de la funcion que va testear hoy:");
        opcion = sc.nextInt();
        mostrarFunciones(opcion);
        sc.close();
    }
    void mostrarFunciones(int opcion) {
        do {
            System.out.println("1. Añadir Contacto.");
            System.out.println("2. Validar si existe contacto.");
            System.out.println("3. Listar Contactos.");
            System.out.println("4. Buscar Contacto por nombre.");
            System.out.println("5. Eliminar Contacto.");
            System.out.println("6. Consultar estado agenda.");
            System.out.println("7. Consultar espacios restante agenda.");
            System.out.println("9. Salir del menu de pruebas.");
        } while (opcion != 9);






    }
}
