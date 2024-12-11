package utils;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class Conexion {
    //Declaramos 3 variables para el pool de conexiones
    private static String url = "jdbc:mysql://localhost:3306/ejemplocarro?useTimezone=true&serverTimezone=UTC";
    //declaramos una variable para el usuario
    private static String username = "root";
    //declaramso una variable para guardar la contraseña
    private static String password = "";
    //implementamos un método para obtener la conexión
    public static Connection getConnection() throws SQLException {
        return DriverManager.getConnection(url,username,password);
    }


}
