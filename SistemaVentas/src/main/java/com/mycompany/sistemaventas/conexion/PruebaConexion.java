package com.mycompany.sistemaventas.conexion;
import java.sql.Connection;
import java.sql.SQLException;

public class PruebaConexion {
    public static void main(String[] args) {

        try (Connection conexion = Conexion.conectar()) {
            System.out.println("Conexion exitosa con PanaderiaG");
            System.out.println("Base de datos: " + conexion.getCatalog());

        } catch (SQLException e) {
            System.out.println("Error al conectar con PostgreSQL:");
            System.out.println(e.getMessage());

        }
    } 
}
