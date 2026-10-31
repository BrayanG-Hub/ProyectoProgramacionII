package Validacion;
import com.mycompany.sistemaventas.modelo.Venta;
import java.util.ArrayList;
import java.util.List;

public class ProcesadorVentas {
   private final NormalizarVenta normalizador = new NormalizarVenta();
    private final ValidadorVenta validador = new ValidadorVenta();
    private final List<Venta> ventasValidas = new ArrayList<>();
    private final List<String> errores = new ArrayList<>();

    public void procesar(List<Venta> ventas, String archivo) {
        for (int i = 0; i < ventas.size(); i++) {

            Venta venta = ventas.get(i);

            try {
                if (venta == null) {
                    errores.add("Archivo: " + archivo
                            + " | Registro: " + (i + 1)
                            + " | Motivo: El registro está vacío");
                    continue;
                }

                normalizador.normalizar(venta);

                String motivo = validador.validar(venta);

                if (motivo == null) {
                    ventasValidas.add(venta);
                } else {
                    errores.add("Archivo: " + archivo
                            + " | Registro: " + (i + 1)
                            + " | ID: " + venta.getIdVenta()
                            + " | Motivo: " + motivo);
                }

            } catch (RuntimeException e) {
                errores.add("Archivo: " + archivo
                        + " | Registro: " + (i + 1)
                        + " | Motivo: Error al procesar: "
                        + e.getMessage());
            }
        }
    }

    public List<Venta> getVentasValidas() {
        return new ArrayList<>(ventasValidas);
    }

    public List<String> getErrores() {
        return new ArrayList<>(errores);
    } 
}
