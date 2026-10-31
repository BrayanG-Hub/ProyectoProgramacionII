package Lectura;
import com.mycompany.sistemaventas.modelo.Venta;
import java.util.List;


public class PruebaJSON {
    public static void main(String[] args) {
        LectorJSON lector = new LectorJSON();
        try {
            List<Venta> ventas = lector.leer(
                "C:\\Users\\intel\\Desktop\\ProyectoProgramacionII\\datos\\ventas.json"
            );
            System.out.println("Ventas JSON leidas correctamente:");
            for (Venta venta : ventas) {
                System.out.println(
                    venta.getIdVenta() + " - " +
                    venta.getProducto() + " - " +
                    venta.getCantidad() + " - " +
                    venta.getVendedor()
                );
            }
        } catch (Exception e) {
            System.out.println("Error al leer JSON: " + e.getMessage());
        }
    }
}
