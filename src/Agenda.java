// Archivo: Agenda.java
import java.util.ArrayList;
import java.util.List;

public class Agenda {
    private List<Contacto> contactos;

    public Agenda() {
        this.contactos = new ArrayList<>();
        // Datos de prueba para que se vea bonito al iniciar
        contactos.add(new Contacto("Sarah", "Johnson", "3001234567"));
        contactos.add(new Contacto("Michael", "Chen", "3109876543"));
    }

    public boolean añadirContacto(Contacto c) {
        if (buscarContacto(c.getNombre()) == null && !c.getCelular().equals("Error")) {
            contactos.add(c);
            return true;
        }
        return false;
    }

    public Contacto buscarContacto(String nombre) {
        for (Contacto c : contactos) {
            if (c.getNombre().equalsIgnoreCase(nombre)) {
                return c;
            }
        }
        return null; // No encontrado
    }

    public List<Contacto> listarContactos() {
        return contactos;
    }

    public boolean eliminarContacto(Contacto c) {
        // Buscamos por nombre para eliminar (simplificado)
        Contacto aEliminar = buscarContacto(c.getNombre());
        if (aEliminar != null) {
            contactos.remove(aEliminar);
            return true;
        }
        return false;
    }

    public boolean modificarTelefono(String nombre, String apellido, String nuevoCelular) {
        Contacto c = buscarContacto(nombre);
        if (c != null) {
            c.setCelular(nuevoCelular);
            return true; // Asumimos éxito si existe
        }
        return false;
    }
}