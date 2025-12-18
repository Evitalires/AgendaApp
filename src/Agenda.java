import java.util.ArrayList;

public class Agenda {

     ArrayList<Contacto> contactos;
     int tamanioMaximo;

    public Agenda() {
        this.tamanioMaximo = 10;
        this.contactos = new ArrayList<>(tamanioMaximo);
    }

    public Agenda(int tamanioMaximo) {
        this.tamanioMaximo = tamanioMaximo;
        this.contactos = new ArrayList<>(tamanioMaximo);
    }





}
