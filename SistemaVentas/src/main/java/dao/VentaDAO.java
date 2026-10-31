package dao;
import com.mycompany.sistemaventas.conexion.Conexion;
import com.mycompany.sistemaventas.modelo.Venta;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.List;

public class VentaDAO {

    public void insertarVentas(List<Venta> ventas) throws SQLException {

        String sql = "INSERT INTO ventas "
                + "(id_venta, fecha, sucursal, codigo_producto, producto, "
                + "cantidad, precio_unitario, vendedor) "
                + "VALUES (?, ?, ?, ?, ?, ?, ?, ?) "
                + "ON CONFLICT (id_venta) DO NOTHING";

        try (Connection conexion = Conexion.conectar();
             PreparedStatement ps = conexion.prepareStatement(sql)) {

            for (Venta venta : ventas) {
                ps.setInt(1, venta.getIdVenta());
                ps.setDate(2, java.sql.Date.valueOf(venta.getFecha()));
                ps.setString(3, venta.getSucursal());
                ps.setString(4, venta.getCodigoProducto());
                ps.setString(5, venta.getProducto());
                ps.setInt(6, venta.getCantidad());
                ps.setDouble(7, venta.getPrecioUnitario());
                ps.setString(8, venta.getVendedor());

                ps.addBatch();
            }

            ps.executeBatch();
        }
    }
}
