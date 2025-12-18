public class Contacto {
    String nombre;
    String apellido;
    String celular;

    public Contacto(String nombre, String apellido, String celular) {
        this.nombre = nombre;
        this.apellido = apellido;
        this.celular = validarFormatoCelular(celular);
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public String getApellido() {
        return apellido;
    }

    public void setApellido(String apellido) {
        this.apellido = apellido;
    }

    public String getCelular() {
        return celular;
    }

    public void setCelular(String celular) {
        this.celular = validarFormatoCelular(celular);
    }

    String validarFormatoCelular(String celular){
        //Celular
        String primerNumero = celular.substring(0,1);
        if(celular.length() == 10 && primerNumero.equals("3")) {
            //Formato correcto
            return celular;
        } else {
            //Formato Incorrecto
            System.out.println("Error, el formato de celular es incorecto");
            return "Error";
        }
    }
}
