package peaksoft;

import peaksoft.service.UserServiceHiberImpl;
import peaksoft.util.Util;

import java.sql.SQLException;

public class Main {
    public static void main(String[] args) throws SQLException {
        // реализуйте алгоритм здесь
//        UserServiceImpl userService = new UserServiceImpl();
//        userService.createUsersTable();
//        userService.saveUser("Nurlan", "Abdyldaev", (byte) 20);
//        userService.saveUser("Almaz", "Sartov", (byte) 30);
//        userService.saveUser("Taalay", "Japarov", (byte) 25);
//        userService.saveUser("Ali", "Samatov", (byte) 40);
//        userService.getAllUsers();
//        userService.cleanUsersTable();
//        userService.dropUsersTable();

        UserServiceHiberImpl userServiceHiber = new UserServiceHiberImpl();
//        userServiceHiber.createUsersTable();
//        User user1 = new User("Nurlan", "Abdyldaev", (byte) 20);
//        User user2 = new User("Almaz", "Suranchiev", (byte) 30);
//        User user3 =new User("Taalay", "Japarov", (byte) 40);
//        User[] users = {user1, user2, user3};
//        for (User e:users  ) {
//            userServiceHiber.saveUser(e);
//        }
//        userServiceHiber.dropUsersTable();
//        userServiceHiber.saveUser("Nur","Ad",(byte) 54);
//        userServiceHiber.saveUser("Marat","Ad",(byte) 14);
//        userServiceHiber.removeUserById(1);
//        userServiceHiber.getAllUsers();
//        userServiceHiber.cleanUsersTable();
        userServiceHiber.dropUsersTable();


        Util.shutDown();
    }

}
