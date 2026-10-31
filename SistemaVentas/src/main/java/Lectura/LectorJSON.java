package Lectura;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.mycompany.sistemaventas.modelo.Venta;
import java.io.File;
import java.util.ArrayList;
import java.util.List;

public class LectorJSON {
    private final List<String> errores = new ArrayList<>();
    public List<Venta> leer(String ruta) throws Exception {
        List<Venta> ventas = new ArrayList<>();
        errores.clear();
        ObjectMapper mapper = new ObjectMapper();
        JsonNode registros = mapper.readTree(new File(ruta));

        if (!registros.isArray()) {
            throw new IllegalArgumentException(
                    "El archivo JSON debe contener un arreglo");
        }
        int numeroRegistro = 0;

        for (JsonNode registro : registros) {
            numeroRegistro++;

            try {
                Venta venta = mapper.treeToValue(registro, Venta.class);
                ventas.add(venta);
            } catch (RuntimeException e) {
                errores.add("Archivo: " + ruta
                        + " | Registro: " + numeroRegistro
                        + " | Motivo: " + e.getMessage());
            }
        }

        return ventas;
    }

    public List<String> getErrores() {
        return new ArrayList<>(errores);
    }
}
