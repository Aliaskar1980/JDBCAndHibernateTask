package peaksoft;

import org.junit.Assert;
import org.junit.Test;
import peaksoft.model.User;
import peaksoft.service.UserServiceHiberImpl;
import java.util.List;

public class UserServiceHiberTest {

    private final UserServiceHiberImpl userServiceHiber = new UserServiceHiberImpl();

    private final String testName = "Kanat";
    private final String testLastName = "Subanov";
    private final byte testAge = 23;

    @Test
    public void dropUsersTable() {
        try {
            userServiceHiber.dropUsersTable();
        } catch (Exception e) {
            Assert.fail("При тестировании удаления таблицы произошло исключение\n" + e);
        }
    }

    @Test
    public void createUsersTable() {
        try {
            userServiceHiber.createUsersTable();

        } catch (Exception e) {
            Assert.fail("При тестировании создания таблицы пользователей произошло исключение\n" + e.getMessage());
        }
    }

    @Test
    public void saveUser() {
        try {
            userServiceHiber.dropUsersTable();
            userServiceHiber.createUsersTable();
            userServiceHiber.saveUser(testName, testLastName, testAge);

            User user = userServiceHiber.getAllUsers().get(0);

            if (!testName.equals(user.getName())
                    || !testLastName.equals(user.getLastName())
                    || testAge != user.getAge()
            ) {
                Assert.fail("User был некорректно добавлен в базу данных");
            }

        } catch (Exception e) {
            Assert.fail("Во время тестирования сохранения пользователя произошло исключение\n" + e);
        }
    }

    @Test
    public void removeUserById() {
        try {
            userServiceHiber.dropUsersTable();
            userServiceHiber.createUsersTable();
            userServiceHiber.saveUser(testName, testLastName, testAge);
            userServiceHiber.removeUserById(1L);
        } catch (Exception e) {
            Assert.fail("При тестировании удаления пользователя по id произошло исключение\n" + e);
        }
    }

    @Test
    public void getAllUsers() {
        try {
            userServiceHiber.dropUsersTable();
            userServiceHiber.createUsersTable();
            userServiceHiber.saveUser(testName, testLastName, testAge);
            List<User> userList = userServiceHiber.getAllUsers();

            if (userList.size() != 1) {
                Assert.fail("Проверьте корректность работы метода сохранения пользователя/удаления или создания таблицы");
            }
        } catch (Exception e) {
            Assert.fail("При попытке достать всех пользователей из базы данных произошло исключение\n" + e);
        }
    }

    @Test
    public void cleanUsersTable() {
        try {
            userServiceHiber.dropUsersTable();
            userServiceHiber.createUsersTable();
            userServiceHiber.saveUser(testName, testLastName, testAge);
            userServiceHiber.cleanUsersTable();

            if (userServiceHiber.getAllUsers().size() != 0) {
                Assert.fail("Метод очищения таблицы пользователей реализован не корректно");
            }
        } catch (Exception e) {
            Assert.fail("При тестировании очистки таблицы пользователей произошло исключение\n" + e);
        }
    }
}
