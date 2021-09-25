package peaksoft;

import peaksoft.service.UserServiceImpl;

import java.sql.SQLException;

public class Main {
    public static void main(String[] args) throws SQLException {
        // реализуйте алгоритм здесь
        UserServiceImpl userService = new UserServiceImpl();
        userService.createUsersTable();
        userService.saveUser("Nurlan", "Abdyldaev", (byte) 20);
        userService.saveUser("Almaz", "Sartov", (byte) 30);
        userService.saveUser("Taalay", "Japarov", (byte) 25);
        userService.saveUser("Ali", "Samatov", (byte) 40);
        userService.getAllUsers();
        userService.cleanUsersTable();
        userService.dropUsersTable();
    }
}
