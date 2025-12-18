// Archivo: Agenda.java
import java.util.ArrayList;
import java.util.List;

public class Agenda {
    private List<Contacto> contactos;
    private int tamanioMaximo;


    public Agenda() {
        this.tamanioMaximo = 10;
        this.contactos = new ArrayList<>();

        // Datos de prueba
        contactos.add(new Contacto("Sarah", "Johnson", "3001234567"));
        contactos.add(new Contacto("Michael", "Chen", "3109876543"));
    }

    public Agenda(int tamanioMaximo) {
        this.tamanioMaximo = tamanioMaximo;
        this.contactos = new ArrayList<>();
    }

    // metodos

    public int sizeContactos() {
        return  this.contactos.size();
    }
    public int tamanioMaximo() {
        return  this.tamanioMaximo;
    }

    public boolean añadirContacto(Contacto c) {

        if (c.getNombre().isEmpty() || c.getApellido().isEmpty()) {
            System.out.println("Nombre o apellido no pueden estar vacíos");
            return false;
        }

        if (agendaLlena()) {
            System.out.println("La agenda está llena");
            return false;
        }

        if (existeContacto(c)) {
            System.out.println("El contacto ya existe");
            return false;
        }

        if (c.getCelular().equals("Error")) {
            System.out.println("Número de celular inválido");
            return false;
        }

        contactos.add(c);
        System.out.println("Contacto añadido correctamente");
        return true;
    }


    public boolean existeContacto(Contacto c) {
        for (Contacto contacto : contactos) {
            if (contacto.getNombre().equalsIgnoreCase(c.getNombre()) &&
                    contacto.getApellido().equalsIgnoreCase(c.getApellido())) {
                return true;
            }
        }
        return false;
    }


    public List<Contacto> listarContactos() {

        if (contactos.isEmpty()) {
            System.out.println("La agenda está vacía");
            return null;
        }

        System.out.println("\n===== LISTA DE CONTACTOS =====");
        for (Contacto c : contactos) {
            System.out.println(c.getNombre() + " " + c.getApellido() + " - " + c.getCelular()
            );
        }
        System.out.println("Tamano contactos: " + contactos.size());
        return contactos;
    }


    public Contacto buscarContacto(String nombre) {
        for (Contacto c : contactos) {
            //(c.getNombre().equalsIgnoreCase(nombre) && c.getApellido().equalsIgnoreCase(apellido))
            if (c.getNombre().equalsIgnoreCase(nombre)) {
                System.out.println("Teléfono: " + c.getCelular());
                return c;
            }
        }
        System.out.println("Contacto no encontrado");
        return null;
    }


    public void eliminarContacto(Contacto c) {
        for (int i = 0; i < contactos.size(); i++) {
            Contacto actual = contactos.get(i);
            if (actual.getNombre().equalsIgnoreCase(c.getNombre()) &&
                    actual.getApellido().equalsIgnoreCase(c.getApellido())) {
                contactos.remove(i);
                System.out.println("Contacto eliminado correctamente");
                return;
            }
        }
        System.out.println("El contacto no existe");
    }


    public void modificarTelefono(String nombre, String apellido, String nuevoTelefono) {
        for (Contacto c : contactos) {
            if (c.getNombre().equalsIgnoreCase(nombre) &&
                    c.getApellido().equalsIgnoreCase(apellido)) {
                c.setCelular(nuevoTelefono);
                System.out.println("Teléfono actualizado correctamente");
                return;
            }
        }
        System.out.println("El contacto no existe");
    }


    public boolean agendaLlena() {
        return contactos.size() >= tamanioMaximo;
    }


    public int espaciosLibres() {
        return tamanioMaximo - contactos.size();
    }

}

