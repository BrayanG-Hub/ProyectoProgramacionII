package Lectura;
import com.mycompany.sistemaventas.modelo.Venta;
import Validacion.ProcesadorVentas;
import Validacion.DetectorDuplicados;
import dao.VentaDAO;
import Analisis.AnalizadorVentas;
import java.util.List;
import java.util.Map;
import java.io.FileWriter;
import java.io.PrintWriter;

public class IntegradorVentas {
    public static void main(String[] args) {

        String carpeta = "C:\\Users\\intel\\Desktop\\ProyectoProgramacionII\\datos\\";
        try {
            LectorCSV lectorCSV = new LectorCSV();
            LectorJSON lectorJSON = new LectorJSON();
            LectorXML lectorXML = new LectorXML();

            List<Venta> ventasCSV =
                    lectorCSV.leer(carpeta + "ventas.csv");
            List<Venta> ventasJSON =
                    lectorJSON.leer(carpeta + "ventas.json");
            List<Venta> ventasXML =
                    lectorXML.leer(carpeta + "ventas.xml");

            List<String> erroresLectura = new java.util.ArrayList<>();
            erroresLectura.addAll(lectorCSV.getErrores());
            erroresLectura.addAll(lectorJSON.getErrores());
            erroresLectura.addAll(lectorXML.getErrores());

            ProcesadorVentas procesador = new ProcesadorVentas();
            procesador.procesar(ventasCSV, "ventas.csv");
            procesador.procesar(ventasJSON, "ventas.json");
            procesador.procesar(ventasXML, "ventas.xml");

            DetectorDuplicados detector = new DetectorDuplicados();
            detector.procesar(
                    procesador.getVentasValidas(),
                    "archivos integrados"
            );

            VentaDAO ventaDAO = new VentaDAO();
            ventaDAO.insertarVentas(detector.getVentasUnicas());
            System.out.println("Ventas procesadas para guardar: "
                    + detector.getVentasUnicas().size());

            AnalizadorVentas analizador = new AnalizadorVentas();

            List<Venta> ventasAnalizadas = detector.getVentasUnicas();

            double totalVentas =
                    analizador.calcularTotalVentas(ventasAnalizadas);
            double promedio =
                    analizador.calcularPromedioVenta(ventasAnalizadas);
            double ventaMayor =
                    analizador.calcularVentaMayor(ventasAnalizadas);
            double ventaMenor =
                    analizador.calcularVentaMenor(ventasAnalizadas);
            String productoMayor =
                    analizador.obtenerProductoMayorVenta(ventasAnalizadas);
            String productoMenor =
                    analizador.obtenerProductoMenorVenta(ventasAnalizadas);
            Venta transaccionMayor =
                    analizador.obtenerTransaccionMayor(ventasAnalizadas);
            Venta transaccionMenor =
                    analizador.obtenerTransaccionMenor(ventasAnalizadas);

            int totalRecibidos = ventasCSV.size()
                    + ventasJSON.size()
                    + ventasXML.size()
                    + erroresLectura.size();

            int totalRechazados = erroresLectura.size()
                    + procesador.getErrores().size();

            double porcentajeRechazados = totalRecibidos == 0
                    ? 0
                    : totalRechazados * 100.0 / totalRecibidos;

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

            // 8. Reporte de análisis
            try (PrintWriter writer = new PrintWriter(
                    new FileWriter(carpeta + "reporte_analisis.txt"))) {

                writer.println("===== REPORTE DE ANALISIS DE VENTAS =====");

                writer.println("\nTotal de registros recibidos: "
                        + totalRecibidos);

                writer.println("Ventas validas y sin duplicados: "
                        + ventasAnalizadas.size());

                writer.println("Registros rechazados: "
                        + totalRechazados);

                writer.println("Registros duplicados: "
                        + detector.getDuplicados().size());

                writer.printf("Porcentaje de registros rechazados: %.2f%%%n",
                        porcentajeRechazados);

                writer.printf("Total de ingresos: Q%.2f%n", totalVentas);

                writer.printf("Promedio por transaccion: Q%.2f%n", promedio);

                writer.printf("Venta de mayor valor: Q%.2f%n", ventaMayor);

                writer.printf("Venta de menor valor: Q%.2f%n", ventaMenor);

                writer.println("Producto con mayores ingresos: "
                        + productoMayor);

                writer.println("Producto con menores ingresos: "
                        + productoMenor);

                if (transaccionMayor != null) {
                    writer.printf(
                            "Transaccion mayor: ID %d - Q%.2f%n",
                            transaccionMayor.getIdVenta(),
                            transaccionMayor.getCantidad()
                                    * transaccionMayor.getPrecioUnitario()
                    );
                }

                if (transaccionMenor != null) {
                    writer.printf(
                            "Transaccion menor: ID %d - Q%.2f%n",
                            transaccionMenor.getIdVenta(),
                            transaccionMenor.getCantidad()
                                    * transaccionMenor.getPrecioUnitario()
                    );
                }

                writer.println("\n--- VENTAS POR SUCURSAL ---");

                for (Map.Entry<String, Double> entrada
                        : analizador.calcularVentasPorSucursal(
                                ventasAnalizadas).entrySet()) {

                    writer.printf("%s: Q%.2f%n",
                            entrada.getKey(), entrada.getValue());
                }

                writer.println("\n--- VENTAS POR PRODUCTO ---");

                for (Map.Entry<String, Double> entrada
                        : analizador.calcularVentasPorProducto(
                                ventasAnalizadas).entrySet()) {

                    writer.printf("%s: Q%.2f%n",
                            entrada.getKey(), entrada.getValue());
                }

                writer.println("\n--- VENTAS POR VENDEDOR ---");

                for (Map.Entry<String, Double> entrada
                        : analizador.calcularVentasPorVendedor(
                                ventasAnalizadas).entrySet()) {

                    writer.printf("%s: Q%.2f%n",
                            entrada.getKey(), entrada.getValue());
                }

                writer.println("\n--- VENTAS POR MES ---");

                for (Map.Entry<String, Double> entrada
                        : analizador.calcularVentasPorMes(
                                ventasAnalizadas).entrySet()) {

                    writer.printf("%s: Q%.2f%n",
                            entrada.getKey(), entrada.getValue());
                }

                writer.println("\n--- TOP 3 VENDEDORES ---");

                for (Map.Entry<String, Double> entrada
                        : analizador.obtenerTopTresVendedores(
                                ventasAnalizadas).entrySet()) {

                    writer.printf("%s: Q%.2f%n",
                            entrada.getKey(), entrada.getValue());
                }
            }
            System.out.println("\n===== RESULTADO DE INTEGRACION =====");

            System.out.println("Registros recibidos: " + totalRecibidos);

            System.out.println("Ventas validas y sin duplicados: "
                    + ventasAnalizadas.size());

            System.out.println("Duplicados: "
                    + detector.getDuplicados().size());

            System.out.println("Registros rechazados: "
                    + totalRechazados);

            System.out.printf("Porcentaje rechazado: %.2f%%%n",
                    porcentajeRechazados);

            System.out.println("Errores de lectura: "
                    + erroresLectura.size());

            System.out.println("Reporte generado correctamente.");

        } catch (Exception e) {

            System.out.println(
                    "Error al integrar los archivos: " + e.getMessage()
            );

            e.printStackTrace();
        }
    }
}
