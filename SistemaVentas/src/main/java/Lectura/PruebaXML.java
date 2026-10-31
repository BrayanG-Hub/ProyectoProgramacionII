package Lectura;
import com.mycompany.sistemaventas.modelo.Venta;
import java.util.List;

public class PruebaXML {
    public static void main(String[] args) {

        LectorXML lector = new LectorXML();

        try {

            List<Venta> ventas = lector.leer(
                "C:\\Users\\intel\\Desktop\\ProyectoProgramacionII\\datos\\ventas.xml"
            );

            System.out.println("Ventas XML leidas correctamente:");

            for (Venta venta : ventas) {
                System.out.println(
                    venta.getIdVenta() + " - " +
                    venta.getProducto() + " - " +
                    venta.getCantidad() + " - " +
                    venta.getVendedor()
                );
            }

        } catch (Exception e) {
            System.out.println("Error al leer XML: " + e.getMessage());
        }
    }
}
