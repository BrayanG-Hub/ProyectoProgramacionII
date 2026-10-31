package Validacion;
import com.mycompany.sistemaventas.modelo.Venta;
import java.time.LocalDate;
import java.time.format.DateTimeParseException;
import java.time.format.ResolverStyle;

public class ValidadorVenta {
   public String validar(Venta venta) {

        if (venta == null) {
            return "El registro está vacío";
        }

        if (venta.getIdVenta() <= 0) {
            return "Identificador de venta inválido";
        }

        if (venta.getFecha() == null || venta.getFecha().isBlank()) {
            return "La fecha está vacía";
        }

        try {
            LocalDate.parse(
                venta.getFecha().trim(),
                java.time.format.DateTimeFormatter
                    .ofPattern("uuuu-MM-dd")
                    .withResolverStyle(ResolverStyle.STRICT)
            );
        } catch (DateTimeParseException e) {
            return "La fecha no tiene un formato válido (AAAA-MM-DD)";
        }

        if (venta.getSucursal() == null || venta.getSucursal().isBlank()) {
            return "La sucursal está vacía";
        }

        if (venta.getCodigoProducto() == null || venta.getCodigoProducto().isBlank()) {
            return "El código del producto está vacío";
        }

        if (venta.getProducto() == null || venta.getProducto().isBlank()) {
            return "El nombre del producto está vacío";
        }

        if (venta.getCantidad() <= 0) {
            return "La cantidad debe ser mayor que cero";
        }

        if (!Double.isFinite(venta.getPrecioUnitario())
                || venta.getPrecioUnitario() <= 0) {
            return "El precio unitario debe ser mayor que cero";
        }

        if (venta.getVendedor() == null || venta.getVendedor().isBlank()) {
            return "El vendedor está vacío";
        }

        return null;
    } 
}
