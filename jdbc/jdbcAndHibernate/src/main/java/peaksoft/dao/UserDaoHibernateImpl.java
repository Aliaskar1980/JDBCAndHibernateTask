package peaksoft.dao;

import org.hibernate.HibernateException;
import org.hibernate.Session;
import org.hibernate.Transaction;
import peaksoft.model.User;
import peaksoft.util.Util;

import java.util.List;

public class UserDaoHibernateImpl implements UserDao {
    private Transaction transaction = null;

    public UserDaoHibernateImpl() {

    }

    @Override
    public void createUsersTable() {
        try {
            Session session = Util.getSessionFactory().openSession();
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

        try {
            Session session = Util.getSessionFactory().openSession();
            transaction = session.beginTransaction();
            session.createSQLQuery("DROP TABLE users").executeUpdate();
            session.getTransaction().commit();
            session.close();
            System.out.println("Successfully deleted ");
        } catch (
                Exception e) {
            if (transaction != null) {
                transaction.rollback();


            }
        }
    }

    @Override
    public void saveUser(String name, String lastName, byte age) {
        Long userId;

        try {
            Session session = Util.getSessionFactory().openSession();
            transaction = session.beginTransaction();
            User user = new User(name, lastName, age);
            userId = (Long) session.save(user);
            session.getTransaction().commit();
            session.close();
            System.out.println(userId + "Успешно добавлено.");
        } catch (
                Exception e) {
            if (transaction != null) {
                transaction.rollback();

            }
        }
    }

    @Override
    public void removeUserById(long id) {
        try {
            Session session = Util.getSessionFactory().openSession();
            transaction = session.beginTransaction();
            User e = session.get(User.class, id);
            session.delete(e);
            session.getTransaction().commit();
            session.close();
            System.out.println("Successfully deleted" + e);
        } catch (
                Exception e) {
            if (transaction != null) {
                transaction.rollback();
            }
        }
    }

    @Override
    public List<User> getAllUsers() {
        List<User> userList = null;
        try {
            Session session = Util.getSessionFactory().openSession();
            session.beginTransaction();
            userList = session.createQuery("FROM User ").getResultList();
            session.getTransaction().commit();
            session.close();
            System.out.println("Found " + userList.size() + " users");

        } catch (
                Exception e) {
            if (transaction != null) {
                transaction.rollback();
            }
        }
        return userList;
    }

    @Override
    public void cleanUsersTable() {
        try {
            Session session = Util.getSessionFactory().openSession();
            transaction = session.beginTransaction();
            session.createQuery("DELETE FROM User").executeUpdate();
            session.getTransaction().commit();
            session.close();
            System.out.println("Successfully deleted all data in User");

        } catch (
                Exception e) {
            if (transaction != null) {
                transaction.rollback();
            }
        }
    }
}
