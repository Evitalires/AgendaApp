public class Contacto {
    String nombre;
    String celular;

    public Contacto(String nombre, String celular) {
        this.nombre = nombre;
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
