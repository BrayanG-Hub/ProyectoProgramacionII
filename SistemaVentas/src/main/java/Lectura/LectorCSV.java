package Lectura;
import com.mycompany.sistemaventas.modelo.Venta;
import com.opencsv.CSVReader;
import java.io.FileReader;
import java.util.ArrayList;
import java.util.List;

public class LectorCSV {
    public List<Venta> leer(String ruta) throws Exception {
        List<Venta> ventas = new ArrayList<>();
        try (CSVReader reader = new CSVReader(new FileReader(ruta))) {
            String[] fila;
            reader.readNext();

            while ((fila = reader.readNext()) != null) {

                Venta venta = new Venta(
                    Integer.parseInt(fila[0]),
                    fila[1],
                    fila[2],
                    fila[3],
                    fila[4],
                    Integer.parseInt(fila[5]),
                    Double.parseDouble(fila[6]),
                    fila[7]
                );

                ventas.add(venta);
            }
        }

        return ventas;
    }
}
