import java.util.Scanner;
import java.util.List; // Import necesario si listarContactos devuelve una lista

public class PruebasAgenda {
    private Agenda agenda;
    private Scanner sc;

    public PruebasAgenda(Agenda agenda) {
        this.agenda = agenda;
        this.sc = new Scanner(System.in); // Instanciamos el Scanner una sola vez
    }

    void iniciar() {
        System.out.println("--- Bienvenido al menú de pruebas Agenda ---");
        probarFunciones();
    }

    void probarFunciones() {
        int opcion = 0;

        do {
            System.out.println("\n--- MENÚ ---");
            System.out.println("1. Añadir Contacto.");
            System.out.println("2. Validar si existe contacto.");
            System.out.println("3. Listar Contactos.");
            System.out.println("4. Buscar Contacto por nombre.");
            System.out.println("5. Eliminar Contacto.");
            System.out.println("6. Consultar si la agenda está llena.");
            System.out.println("7. Consultar espacios restantes.");
            System.out.println("9. Salir.");
            System.out.print("Elija una opción: ");

            opcion = sc.nextInt();
            sc.nextLine(); // IMPORTANTE: Limpiar el buffer después de leer un número

            switch (opcion) {
                case 1: // Añadir
                    System.out.println("--- Añadir Contacto ---");
                    System.out.print("Ingrese nombre: ");
                    String nombre = sc.nextLine();
                    System.out.print("Ingrese apellido: ");
                    String apellido = sc.nextLine();
                    System.out.print("Ingrese celular: ");
                    String celular = sc.nextLine();

                    Contacto nuevo = new Contacto(nombre, apellido, celular);
                    // Asumiendo que añadirContacto devuelve boolean
                    if(agenda.añadirContacto(nuevo)){
                        System.out.println("✅ Contacto añadido.");
                    } else {
                        System.out.println("❌ Error: Agenda llena o contacto duplicado.");
                    }
                    break;

                case 2: // Existe Contacto
                    System.out.print("Ingrese nombre del contacto a verificar: ");
                    String numBusq = sc.nextLine();
                    // Generalmente se busca por nombre, o se crea un contacto temporal
                    if (agenda.buscarContacto(numBusq) != null) {
                        System.out.println("✅ El contacto SÍ existe.");
                    } else {
                        System.out.println("ℹ️ El contacto NO existe.");
                    }
                    break;

                case 3: // Listar
                    System.out.println("--- Lista de Contactos ---");
                    // Opción A: Si tu método imprime directamente:
                    //agenda.listarContactos();

                    // Opción B: Si devuelve una lista (recomendado):
                    List<Contacto> lista = agenda.listarContactos();
                    if(lista.isEmpty()){
                        System.out.println("La agenda está vacía.");
                    } else {
                        for(Contacto c : lista){
                            System.out.println(c); // Usa el toString() de Contacto
                        }
                    }
                    break;

                case 4: // Buscar
                    System.out.print("Ingrese el nombre a buscar: ");
                    String nombreB = sc.nextLine();
                    Contacto encontrado = agenda.buscarContacto(nombreB);
                    if(encontrado != null){
                        System.out.println("Datos del contacto: " + encontrado);
                        System.out.println("Teléfono: " + encontrado.getCelular());
                    } else {
                        System.out.println("❌ No encontrado.");
                    }
                    break;

                case 5: // Eliminar
                    System.out.print("Ingrese el nombre del contacto a eliminar: ");
                    String nomElim = sc.nextLine();
                    // Creamos un contacto temporal o buscamos para eliminar
                    Contacto aEliminar = agenda.buscarContacto(nomElim);

                    if (aEliminar != null) {
                        agenda.eliminarContacto(aEliminar);
                        System.out.println("🗑️ Contacto eliminado exitosamente.");
                    } else {
                        System.out.println("❌ No se pudo eliminar (no existe).");
                    }
                    break;

                case 6: // Agenda Llena
                    if (agenda.agendaLlena()) {
                        System.out.println("⚠️ La agenda está LLENA.");
                    } else {
                        System.out.println("✅ Aún hay espacio en la agenda.");
                    }
                    break;

                case 7: // Espacios Libres
                    //System.out.println("Espacios libres disponibles: " + agenda.espaciosLibres());
                    break;

                case 9:
                    System.out.println("Saliendo...");
                    break;

                default:
                    System.out.println("Opción no válida.");
            }
        } while (opcion != 9);
        sc.close();
    }
}