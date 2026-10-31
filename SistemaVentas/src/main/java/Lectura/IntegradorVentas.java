package Lectura;
import com.mycompany.sistemaventas.modelo.Venta;
import Validacion.ProcesadorVentas;
import Validacion.DetectorDuplicados;
import java.util.List;
import java.io.FileWriter;
import java.io.PrintWriter;
import dao.VentaDAO;

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
            List<String> erroresLectura = new java.util.ArrayList<>();
            erroresLectura.addAll(lectorCSV.getErrores());
            erroresLectura.addAll(lectorJSON.getErrores());
            erroresLectura.addAll(lectorXML.getErrores());
            
            ProcesadorVentas procesador = new ProcesadorVentas();
            procesador.procesar(ventasCSV, "ventas.csv");
            procesador.procesar(ventasJSON, "ventas.json");
            procesador.procesar(ventasXML, "ventas.xml");

            DetectorDuplicados detector = new DetectorDuplicados();
            detector.procesar(procesador.getVentasValidas(), "archivos integrados");
            VentaDAO ventaDAO = new VentaDAO();
            ventaDAO.insertarVentas(detector.getVentasUnicas());

            System.out.println("Ventas enviadas a la base de datos: "
            + detector.getVentasUnicas().size());
            
            try (PrintWriter writer = new PrintWriter(
            new FileWriter(carpeta + "errores_procesamiento.txt"))) {

            writer.println("===== ERRORES DE PROCESAMIENTO =====");
            writer.println("\n--- Errores de lectura ---");
            for (String error : erroresLectura) {
            writer.println(error);
            }
            writer.println("\n--- Errores de validación ---");
            for (String error : procesador.getErrores()) {
            writer.println(error);
             }

            writer.println("\n--- Registros duplicados ---");
            for (String duplicado : detector.getDuplicados()) {
            writer.println(duplicado);
            }
              }           
            System.out.println("===== RESULTADO DE INTEGRACIoN =====");
            System.out.println("Registros recibidos: "
                    + (ventasCSV.size() + ventasJSON.size() + ventasXML.size()));

            System.out.println("Ventas validas: "
                    + detector.getVentasUnicas().size());

            System.out.println("Duplicados: "
                    + detector.getDuplicados().size());

            System.out.println("Registros rechazados: "
                    + procesador.getErrores().size());
            System.out.println("Errores de lectura: " + erroresLectura.size());

            for (String error : erroresLectura) {
            System.out.println(error);
            
            }
        } catch (Exception e) {
            System.out.println("Error al integrar los archivos: " + e.getMessage());
        }
    } 
}
