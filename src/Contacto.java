// Archivo: Contacto.java
public class Contacto {
    private String nombre;
    private String apellido;
    private String celular;

    public Contacto(String nombre, String apellido, String celular) {
        this.nombre = nombre;
        this.apellido = apellido;
        this.celular = validarFormatoCelular(celular);
    }

    public String getNombre() { return nombre; }
    public void setNombre(String nombre) { this.nombre = nombre; }

    public String getApellido() { return apellido; }
    public void setApellido(String apellido) { this.apellido = apellido; }

    public String getCelular() { return celular; }
    public void setCelular(String celular) { this.celular = validarFormatoCelular(celular); }

    // Método helper para obtener nombre completo para la UI
    public String getNombreCompleto() {
        return nombre + " " + apellido;
    }

    private String validarFormatoCelular(String celular) {
        if (celular == null) return "Error";
        // Validación básica basada en tu lógica
        if (celular.length() == 10 && celular.startsWith("3")) {
            return celular;
        } else {
            return "Error";
        }
    }

    @Override
    public String toString() {
        return nombre + " " + apellido + " - " + celular;
    }
}