package Analisis;
import com.mycompany.sistemaventas.modelo.Venta;
import java.util.List;
import java.util.Map;
import java.util.HashMap;
import java.util.LinkedHashMap;
import java.util.stream.Collectors;

public class AnalizadorVentas {
    public double calcularTotalVentas(List<Venta> ventas) {
        double total = 0;

        for (Venta venta : ventas) {
            total += venta.getCantidad() * venta.getPrecioUnitario();
        }
        return total;
    }
    public double calcularPromedioVenta(List<Venta> ventas) {
        if (ventas.isEmpty()) return 0;
        return calcularTotalVentas(ventas) / ventas.size();
    }
    public double calcularVentaMayor(List<Venta> ventas) {
        double mayor = 0;

        for (Venta venta : ventas) {
            double subtotal = venta.getCantidad()
                    * venta.getPrecioUnitario();
            if (subtotal > mayor) mayor = subtotal;
        }

        return mayor;
    }
    public double calcularVentaMenor(List<Venta> ventas) {
        if (ventas.isEmpty()) return 0;

        double menor = Double.MAX_VALUE;

        for (Venta venta : ventas) {
            double subtotal = venta.getCantidad()
                    * venta.getPrecioUnitario();
            if (subtotal < menor) menor = subtotal;
        }

        return menor;
    }
    public Map<String, Double> calcularVentasPorSucursal(
            List<Venta> ventas) {
        Map<String, Double> resultados = new HashMap<>();

        for (Venta venta : ventas) {
            double subtotal = venta.getCantidad()
                    * venta.getPrecioUnitario();

            resultados.put(
                    venta.getSucursal(),
                    resultados.getOrDefault(venta.getSucursal(), 0.0)
                            + subtotal
            );
        }

        return resultados;
    }
    public Map<String, Double> calcularVentasPorProducto(
            List<Venta> ventas) {
        Map<String, Double> resultados = new HashMap<>();

        for (Venta venta : ventas) {
            double subtotal = venta.getCantidad()
                    * venta.getPrecioUnitario();

            resultados.put(
                    venta.getProducto(),
                    resultados.getOrDefault(venta.getProducto(), 0.0)
                            + subtotal
            );
        }

        return resultados;
    }
    public Map<String, Double> calcularVentasPorVendedor(
            List<Venta> ventas) {
        Map<String, Double> resultados = new HashMap<>();

        for (Venta venta : ventas) {
            double subtotal = venta.getCantidad()
                    * venta.getPrecioUnitario();

            resultados.put(
                    venta.getVendedor(),
                    resultados.getOrDefault(venta.getVendedor(), 0.0)
                            + subtotal
            );
        }

        return resultados;
    }
    public Map<String, Double> calcularVentasPorMes(
            List<Venta> ventas) {
        Map<String, Double> resultados = new HashMap<>();

        for (Venta venta : ventas) {
            String fecha = venta.getFecha();

            if (fecha == null || fecha.length() < 7) continue;

            String mes = fecha.substring(0, 7);

            double subtotal = venta.getCantidad()
                    * venta.getPrecioUnitario();

            resultados.put(
                    mes,
                    resultados.getOrDefault(mes, 0.0) + subtotal
            );
        }

        return resultados;
    }
    public Map<String, Double> obtenerTopTresVendedores(
            List<Venta> ventas) {

        Map<String, Double> ventasPorVendedor =
                calcularVentasPorVendedor(ventas);

        return ventasPorVendedor.entrySet()
                .stream()
                .sorted(Map.Entry.<String, Double>comparingByValue()
                        .reversed())
                .limit(3)
                .collect(Collectors.toMap(
                        Map.Entry::getKey,
                        Map.Entry::getValue,
                        (a, b) -> a,
                        LinkedHashMap::new
                ));
    }
    public String obtenerProductoMayorVenta(List<Venta> ventas) {
    return calcularVentasPorProducto(ventas)
            .entrySet()
            .stream()
            .max(Map.Entry.comparingByValue())
            .map(Map.Entry::getKey)
            .orElse("Sin datos");
}

    public String obtenerProductoMenorVenta(List<Venta> ventas) {
    return calcularVentasPorProducto(ventas)
            .entrySet()
            .stream()
            .min(Map.Entry.comparingByValue())
            .map(Map.Entry::getKey)
            .orElse("Sin datos");
}
    public Venta obtenerTransaccionMayor(List<Venta> ventas) {
    return ventas.stream()
            .max((v1, v2) -> Double.compare(
                    v1.getCantidad() * v1.getPrecioUnitario(),
                    v2.getCantidad() * v2.getPrecioUnitario()
            ))
            .orElse(null);
}
    public Venta obtenerTransaccionMenor(List<Venta> ventas) {
    return ventas.stream()
            .min((v1, v2) -> Double.compare(
                    v1.getCantidad() * v1.getPrecioUnitario(),
                    v2.getCantidad() * v2.getPrecioUnitario()
            ))
            .orElse(null);
}
}
