package Lectura;
import com.mycompany.sistemaventas.modelo.Venta;
import Validacion.ProcesadorVentas;
import Validacion.DetectorDuplicados;
import java.util.List;


public class IntegradorVentas {
   public static void main(String[] args) {

        String carpeta = "C:\\Users\\intel\\Desktop\\ProyectoProgramacionII\\datos\\";
        try {
            LectorCSV lectorCSV = new LectorCSV();
            LectorJSON lectorJSON = new LectorJSON();
            LectorXML lectorXML = new LectorXML();

            List<Venta> ventasCSV = lectorCSV.leer(carpeta + "ventas.csv");
            List<Venta> ventasJSON = lectorJSON.leer(carpeta + "ventas.json");
            List<Venta> ventasXML = lectorXML.leer(carpeta + "ventas.xml");

            ProcesadorVentas procesador = new ProcesadorVentas();
            procesador.procesar(ventasCSV, "ventas.csv");
            procesador.procesar(ventasJSON, "ventas.json");
            procesador.procesar(ventasXML, "ventas.xml");

            DetectorDuplicados detector = new DetectorDuplicados();
            detector.procesar(procesador.getVentasValidas(), "archivos integrados");

            System.out.println("===== RESULTADO DE INTEGRACIÓN =====");
            System.out.println("Registros recibidos: "
                    + (ventasCSV.size() + ventasJSON.size() + ventasXML.size()));

            System.out.println("Ventas validas: "
                    + detector.getVentasUnicas().size());

            System.out.println("Duplicados: "
                    + detector.getDuplicados().size());

            System.out.println("Registros rechazados: "
                    + procesador.getErrores().size());

        } catch (Exception e) {
            System.out.println("Error al integrar los archivos: " + e.getMessage());
        }
    } 
}
