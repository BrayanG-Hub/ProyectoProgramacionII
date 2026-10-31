package Lectura;
import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.mycompany.sistemaventas.modelo.Venta;
import java.io.File;
import java.util.List;

public class LectorJSON {
    public List<Venta> leer(String ruta) throws Exception {
        ObjectMapper mapper = new ObjectMapper();
        return mapper.readValue(
            new File(ruta),
            new TypeReference<List<Venta>>() {}
        );
    }
}
