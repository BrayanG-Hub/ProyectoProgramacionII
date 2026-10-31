package Lectura;
import com.mycompany.sistemaventas.modelo.Venta;
import com.opencsv.CSVReader;
import java.io.FileReader;
import java.util.ArrayList;
import java.util.List;

public class LectorCSV {
    private final List<String> errores = new ArrayList<>();
    public List<Venta> leer(String ruta) throws Exception {
        List<Venta> ventas = new ArrayList<>();
        errores.clear();
        try (CSVReader reader = new CSVReader(new FileReader(ruta))) {
            String[] fila;
            reader.readNext();
           int numeroFila = 1;
            while ((fila = reader.readNext()) != null) {
                numeroFila++;

                try {
                    if (fila.length != 8) {
                        throw new IllegalArgumentException(
                                "La fila debe tener 8 columnas");
                    }

                    Venta venta = new Venta(
                        Integer.parseInt(fila[0].trim()),
                        fila[1].trim(),
                        fila[2].trim(),
                        fila[3].trim(),
                        fila[4].trim(),
                        Integer.parseInt(fila[5].trim()),
                        Double.parseDouble(fila[6].trim()),
                        fila[7].trim()
                    );

                    ventas.add(venta);

                } catch (RuntimeException e) {
                    errores.add("Archivo: " + ruta
                            + " | Fila: " + numeroFila
                            + " | Motivo: " + e.getMessage());
                }
            }
        }

        return ventas;
    }
    public List<String> getErrores() {
        return new ArrayList<>(errores);
    }
}
