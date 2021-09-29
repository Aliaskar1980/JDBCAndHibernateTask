package peaksoft.dao;

import peaksoft.model.User;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

import static peaksoft.util.Util.connect;

public class UserDaoJdbcImpl implements UserDao {
    Connection conn = connect();
    String tableName = "users";
    boolean exists = conn.getMetaData().getTables(null, null, tableName, null).next();

    public UserDaoJdbcImpl() throws SQLException {

    }

    public void createUsersTable() {
        String sql = "CREATE TABLE  USERS " +
                "(id SERIAL PRIMARY KEY, " +
                " name VARCHAR(255), " +
                " lastName VARCHAR(255), " +
                " age INTEGER)";
        try {
            if (!exists) {
                Statement statement = conn.createStatement();
                statement.executeUpdate(sql);
                System.out.println("Создано таблица Users...");
            }
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }
    }

    public void dropUsersTable() {
        String sql = "DROP TABLE USERS";
        try {
            if (exists) {
                Statement statement = conn.createStatement();
                statement.executeUpdate(sql);
                System.out.println("Таблица Users успешно удалено...");
            }
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }
    }

    public void saveUser(String name, String lastName, byte age) {
        String sql = "INSERT INTO USERS(name, lastName, age) VALUES (?,?,?)";
        try {
            PreparedStatement stm = conn.prepareStatement(sql);
            stm.setString(1, name);
            stm.setString(2, lastName);
            stm.setInt(3, age);
            stm.executeUpdate();
            System.out.println(name + " " + lastName + " " + age + "Успешно добавлено.");
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }
    }

    public void removeUserById(long id) {
        String sql = "DELETE  FROM USERS WHERE id=?";
        try {
            PreparedStatement stm = conn.prepareStatement(sql);
            stm.setInt(1, (int) id);
            stm.executeUpdate();
            System.out.println(" Запись успешно удалено.");
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }
    }

    public List<User> getAllUsers() {
        List<User> users = new ArrayList<>();
        String sql = "SELECT * FROM USERS ";
        try {
            Statement statement = conn.createStatement();
            ResultSet resultSet = statement.executeQuery(sql);
            while (resultSet.next()) {
                int id = resultSet.getInt(1);
                String name = resultSet.getString(2);
                String lastName = resultSet.getString(3);
                byte age = resultSet.getByte(4);
                users.add(new User(name, lastName, age));
                System.out.println(id + " " + name + " " + lastName + " " + age);
            }
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }
        return users;
    }

    public void cleanUsersTable() {
        String sql = "TRUNCATE table USERS ";
        try {
            Statement statement = conn.createStatement();
            statement.executeUpdate(sql);
            System.out.println("Все записи из таблица Users успешно удалено...");
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }
    }
}