package Lectura;
import com.mycompany.sistemaventas.modelo.Venta;
import java.io.File;
import java.util.ArrayList;
import java.util.List;
import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.NodeList;

public class LectorXML {
   public List<Venta> leer(String ruta) throws Exception {
        List<Venta> ventas = new ArrayList<>();
        DocumentBuilderFactory factory =
                DocumentBuilderFactory.newInstance();
        DocumentBuilder builder = factory.newDocumentBuilder();
        Document documento = builder.parse(new File(ruta));
        documento.getDocumentElement().normalize();
        NodeList registros = documento.getElementsByTagName("venta");

        for (int i = 0; i < registros.getLength(); i++) {
            Element elemento = (Element) registros.item(i);
            Venta venta = new Venta(
                Integer.parseInt(obtenerTexto(elemento, "idVenta")),
                obtenerTexto(elemento, "fecha"),
                obtenerTexto(elemento, "sucursal"),
                obtenerTexto(elemento, "codigoProducto"),
                obtenerTexto(elemento, "producto"),
                Integer.parseInt(obtenerTexto(elemento, "cantidad")),
                Double.parseDouble(obtenerTexto(elemento, "precioUnitario")),
                obtenerTexto(elemento, "vendedor")
            );

            ventas.add(venta);
        }

        return ventas;
    }

    private String obtenerTexto(Element elemento, String etiqueta) {
        return elemento.getElementsByTagName(etiqueta)
                .item(0).getTextContent();
    } 
}
