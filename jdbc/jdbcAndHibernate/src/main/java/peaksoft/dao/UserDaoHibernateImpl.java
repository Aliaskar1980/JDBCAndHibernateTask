package peaksoft.dao;

import org.hibernate.HibernateException;
import org.hibernate.Session;
import peaksoft.model.User;
import peaksoft.util.HibernateUtil;

import java.sql.SQLException;
import java.util.List;

public class UserDaoHibernateImpl implements UserDao {

    public UserDaoHibernateImpl() throws SQLException {

    }

    @Override
    public void createUsersTable() throws SQLException {
       try{
           Session session = HibernateUtil.getSessionFactory().openSession();
           session.beginTransaction();
           session.createSQLQuery("CREATE TABLE IF NOT EXISTS users " +
                           "(id SERIAL PRIMARY KEY ," +
                           "name VARCHAR(50)," +
                           "lastName VARCHAR(50)," +
                           "age INTEGER)").executeUpdate();

           session.getTransaction().commit();
           session.close();
           System.out.println("Created successfully ");
       } catch (HibernateException e) {
           e.printStackTrace();
       }
    }

    @Override
    public void dropUsersTable() {

        Session session = HibernateUtil.getSessionFactory().openSession();
        session.beginTransaction();
        session.createSQLQuery("DROP TABLE users").executeUpdate();
        session.getTransaction().commit();
        session.close();
        System.out.println("Successfully deleted ");

    }

    @Override
    public void saveUser(String name, String lastName, byte age) {
        Long userId = null;
        Session session = HibernateUtil.getSessionFactory().openSession();
        session.beginTransaction();
        User user = new User(name,lastName,age);
        userId = (Long) session.save(user);
        session.getTransaction().commit();
        session.close();
        System.out.println(userId + "Успешно добавлено.");

    }

    @Override
    public void removeUserById(long id) {
        Session session = HibernateUtil.getSessionFactory().openSession();
        session.beginTransaction();
        User e = session.get(User.class, id);
        session.delete(e);
        session.getTransaction().commit();
        session.close();
        System.out.println("Successfully deleted" + e);

    }

    @Override
    public List<User> getAllUsers() {
    Session session = HibernateUtil.getSessionFactory().openSession();
    session.beginTransaction();
    List<User> userList =session.createQuery("FROM User ").getResultList();
    session.getTransaction().commit();
    session.close();
    System.out.println("Found " + userList.size() + " users");
        return userList;
    }

    @Override
    public void cleanUsersTable() {
        Session session = HibernateUtil.getSessionFactory().openSession();
        session.beginTransaction();
        session.createQuery("DELETE FROM User").executeUpdate();
        session.getTransaction().commit();
        session.close();
        System.out.println("Successfully deleted all data in User");

    }
}
