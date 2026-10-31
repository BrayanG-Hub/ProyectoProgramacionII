package Validacion;
import com.mycompany.sistemaventas.modelo.Venta;

public class NormalizarVenta {
   public Venta normalizar(Venta venta) {

        if (venta == null) {
            return null;
        }
        venta.setFecha(limpiar(venta.getFecha()));
        venta.setSucursal(normalizarTexto(venta.getSucursal()));
        String codigo = limpiar(venta.getCodigoProducto());
        venta.setCodigoProducto(codigo == null ? null : codigo.toUpperCase());
        venta.setProducto(normalizarTexto(venta.getProducto()));
        venta.setVendedor(normalizarTexto(venta.getVendedor()));

        return venta;
    }
    private String limpiar(String texto) {
        if (texto == null) {
            return null;
        }

        return texto.trim().replaceAll("\\s+", " ");
    }
    private String normalizarTexto(String texto) {
        if (texto == null) {
            return null;
        }

        return limpiar(texto);
    } 
}
