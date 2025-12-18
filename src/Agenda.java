import java.util.ArrayList;

public class Agenda {

    public ArrayList<Contacto> contactos;
    public int tamanioMaximo;

    public Agenda() {
        this.tamanioMaximo = 10;
        this.contactos = new ArrayList<>(tamanioMaximo);
    }

    public Agenda(int tamanioMaximo) {
        this.tamanioMaximo = tamanioMaximo;
        this.contactos = new ArrayList<>(tamanioMaximo);
    }


    //=====  metodos =======


    public void añadirContacto(Contacto c) {

        if (contactos.size() >= tamanioMaximo) {
            System.out.println("La agenda está llena");
            return;
        }

        for (Contacto contacto : contactos) {
            if (contacto.getNombre().equalsIgnoreCase(c.getNombre())) {
                System.out.println("El contacto ya existe");
                return;
            }
        }

        contactos.add(c);
        System.out.println("Contacto agregado correctamente");
    }

    public void existeContacto(String nuevoContacto) {
        for (Contacto contacto : contactos) {
            if (contacto.getNombre().equalsIgnoreCase(nuevoContacto)) {
                System.out.println("El contacto existe");
                return;
            }
        }
        System.out.println("El contacto NO existe");
    }

    public void listarContactos() {

        if (contactos.isEmpty()) {
            System.out.println("La agenda está vacía");
            return;
        }

        System.out.println(" ===== Lista de contactos: =====");

        for (Contacto contacto : contactos) {
            System.out.println("Nombre: " + contacto.getNombre() + contacto.getApellido()+ " Celular: " + contacto.getCelular()
            );
        }
    }

    public void buscarContacto(String nombreContacto) {
        for (Contacto contacto : contactos) {
            if (contacto.getNombre().equalsIgnoreCase(nombreContacto)) {
                System.out.println("Contacto encontrado: " + contacto.getNombre());
                return;
            }
        }
        System.out.println("El contacto no existe");
    }

    public void eliminarContacto(String nombre) {
        for (int i = 0; i < contactos.size(); i++) {
            if (contactos.get(i).getNombre().equalsIgnoreCase(nombre)) {
                contactos.remove(i);
                System.out.println("Contacto eliminado correctamente");
                return;
            }
        }
        System.out.println("El contacto no existe");
    }

    public void modificarTelefono(String nombre, String nuevoTelefono) {
        for (Contacto contacto : contactos) {
            if (contacto.getNombre().equalsIgnoreCase(nombre)) {
                contacto.setCelular(nuevoTelefono);
                System.out.println("Teléfono actualizado correctamente");
                return;
            }
        }
        System.out.println("El contacto no existe");
    }

    public void agendaLlena() {
        if (contactos.size() >= tamanioMaximo) {
            System.out.println("La agenda está llena");
        } else {
            System.out.println("La agenda NO está llena");
        }
    }

    public void espaciosLibres() {
        int espacios = tamanioMaximo - contactos.size();
        System.out.println("Espacios libres: " + espacios);
    }





















}
