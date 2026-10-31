package Validacion;
import java.util.ArrayList;
import java.util.List;

public class RegistroErrores {
   private final List<String> errores = new ArrayList<>();

    public void agregarError(String archivo, int numeroRegistro, String motivo) {
        String error = "Archivo: " + archivo
                + " | Registro: " + numeroRegistro
                + " | Motivo: " + motivo;

        errores.add(error);
    }

    public List<String> obtenerErrores() {
        return new ArrayList<>(errores);
    }

    public int cantidadErrores() {
        return errores.size();
    }

    public void limpiar() {
        errores.clear();
    }  
}
