package Validacion;
import com.mycompany.sistemaventas.modelo.Venta;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

public class DetectorDuplicados {
   private final Set<Integer> identificadores = new HashSet<>();
    private final List<Venta> ventasUnicas = new ArrayList<>();
    private final List<String> duplicados = new ArrayList<>();
    public void procesar(List<Venta> ventas, String archivo) {

        for (Venta venta : ventas) {

            if (venta == null) {
                continue;
            }

            int id = venta.getIdVenta();

            if (identificadores.add(id)) {
                ventasUnicas.add(venta);
            } else {
                duplicados.add(
                    "Archivo: " + archivo
                    + " | ID de venta duplicado: " + id
                );
            }
        }
    }

    public List<Venta> getVentasUnicas() {
        return new ArrayList<>(ventasUnicas);
    }
    public List<String> getDuplicados() {
        return new ArrayList<>(duplicados);
    } 
}
