package peaksoft.util;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class Util {
    // реализуйте настройку соеденения с БД
    private  static  final String URL = "jdbc:postgresql://localhost:5432/postgres";
    private  static  final String USER = "postgres";
    private  static  final String PASSWORD = "oandsandn312";

    public static Connection connect(){
        Connection conn = null;

        try {
            conn = DriverManager.getConnection(URL, USER,PASSWORD);
            System.out.println("Соединение с БД установленно ");
        } catch (SQLException ex) {
            ex.printStackTrace();
        }
        return conn;
    }
}
