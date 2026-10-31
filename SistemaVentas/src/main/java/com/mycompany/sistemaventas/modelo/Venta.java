package com.mycompany.sistemaventas.modelo;


public class Venta {
        public Venta() {
    }
    private int idVenta;
private String fecha;
private String sucursal;
private String codigoProducto;
private String producto;
private int cantidad;
private double precioUnitario;
private String vendedor;

public Venta(int idVenta, String fecha, String sucursal, String codigoProducto, String producto,
 int cantidad, double precioUnitario, String vendedor) {

    this.idVenta = idVenta;
    this.fecha = fecha;
    this.sucursal = sucursal;
    this.codigoProducto = codigoProducto;
    this.producto = producto;
    this.cantidad = cantidad;
    this.precioUnitario = precioUnitario;
    this.vendedor = vendedor;
}
 public int getIdVenta() {
    return idVenta;
}

public void setIdVenta(int idVenta) {
    this.idVenta = idVenta;
}

public String getFecha() {
    return fecha;
}

public void setFecha(String fecha) {
    this.fecha = fecha;
}

public String getSucursal() {
    return sucursal;
}

public void setSucursal(String sucursal) {
    this.sucursal = sucursal;
}

public String getCodigoProducto() {
    return codigoProducto;
}

public void setCodigoProducto(String codigoProducto) {
    this.codigoProducto = codigoProducto;
}

public String getProducto() {
    return producto;
}

public void setProducto(String producto) {
    this.producto = producto;
}

public int getCantidad() {
    return cantidad;
}

public void setCantidad(int cantidad) {
    this.cantidad = cantidad;
}

public double getPrecioUnitario() {
    return precioUnitario;
}

public void setPrecioUnitario(double precioUnitario) {
    this.precioUnitario = precioUnitario;
}

public String getVendedor() {
    return vendedor;
}

public void setVendedor(String vendedor) {
    this.vendedor = vendedor;
}

}
