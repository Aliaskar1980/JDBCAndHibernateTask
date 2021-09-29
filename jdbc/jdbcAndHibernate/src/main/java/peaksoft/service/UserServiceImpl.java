package peaksoft.service;

import peaksoft.dao.UserDao;
import peaksoft.dao.UserDaoHibernateImpl;
import peaksoft.dao.UserDaoJdbcImpl;
import peaksoft.model.User;

import java.sql.SQLException;
import java.util.List;

public class UserServiceImpl implements UserService {

    public void createUsersTable() throws SQLException {
        UserDaoJdbcImpl userDaoJdbc = new UserDaoJdbcImpl();
        userDaoJdbc.createUsersTable();
    }

    public void dropUsersTable() throws SQLException {
        UserDaoJdbcImpl userDaoJdbc = new UserDaoJdbcImpl();
        userDaoJdbc.dropUsersTable();
    }

    public void saveUser(String name, String lastName, byte age) throws SQLException {
        UserDaoJdbcImpl userDaoJdbc = new UserDaoJdbcImpl();
        userDaoJdbc.saveUser(name,lastName,age);

    }

    public void removeUserById(long id) throws SQLException {
        UserDaoJdbcImpl userDaoJdbc = new UserDaoJdbcImpl();
        userDaoJdbc.removeUserById(id);

    }

    public List<User> getAllUsers() throws SQLException {
        UserDaoJdbcImpl userDaoJdbc = new UserDaoJdbcImpl();
        return   userDaoJdbc.getAllUsers();

    }

    public void cleanUsersTable() throws SQLException {
        UserDaoJdbcImpl userDaoJdbc = new UserDaoJdbcImpl();
        userDaoJdbc.cleanUsersTable();
    }
}
