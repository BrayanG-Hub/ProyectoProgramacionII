package Lectura;
import com.mycompany.sistemaventas.modelo.Venta;
import java.util.List;

public class PruebaCSV {
     public static void main(String[] args) {
        LectorCSV lector = new LectorCSV();
        try {

            List<Venta> ventas = lector.leer(
                "C:\\Users\\intel\\Desktop\\ProyectoProgramacionII\\datos\\ventas.csv"
            );

            System.out.println("Ventas leidas correctamente:");
            for (Venta venta : ventas) {
                System.out.println(
                    venta.getIdVenta() + " - " +
                    venta.getProducto() + " - " +
                    venta.getCantidad() + " - " +
                    venta.getVendedor()
                );
            }
        } catch (Exception e) {
            System.out.println("Error al leer el CSV: " + e.getMessage());
        }
    }
}
