package jm.task.core.jdbc.dao;

import jm.task.core.jdbc.model.User;
import jm.task.core.jdbc.util.Util;
import org.hibernate.Session;
import org.hibernate.Transaction;

import java.util.ArrayList;
import java.util.List;

public class UserDaoHibernateImpl implements UserDao {
    public UserDaoHibernateImpl() {

    }

    @Override
    public void createUsersTable() {
        try(Session session = Util.getSessionFactory().openSession()) {
            Transaction trans = session.beginTransaction();
            session.createSQLQuery("CREATE TABLE IF NOT EXISTS Users" +
                    "(Id SERIAL PRIMARY KEY," +
                    "Name VARCHAR(50) NOT NULL," +
                    "LastName VARCHAR(50)," +
                    "Age SMALLINT NOT NULL)").executeUpdate();
            trans.commit();
        }
        catch (Exception e) {
            e.printStackTrace();
        }
    }

    @Override
    public void dropUsersTable() {
        try(Session session = Util.getSessionFactory().openSession()) {
            Transaction trans = session.beginTransaction();
            session.createSQLQuery("DROP TABLE IF EXISTS Users").executeUpdate();
            trans.commit();
        }
        catch (Exception e) {
            e.printStackTrace();
        }
    }

    @Override
    public void saveUser(String name, String lastName, byte age) {
        try(Session session = Util.getSessionFactory().openSession()) {
            Transaction trans = session.beginTransaction();
            session.save(new User(name, lastName, age));
            trans.commit();
        }
        catch (Exception e) {
            e.printStackTrace();
        }
    }

    @Override
    public void removeUserById(long id) {
        try(Session session = Util.getSessionFactory().openSession()) {
            Transaction trans = session.beginTransaction();
            session.delete(session.get(User.class, id));
            trans.commit();
        }
        catch (Exception e) {
            e.printStackTrace();
        }
    }

    @Override
    public List<User> getAllUsers() {
        List<User> users = new ArrayList<>();
        try(Session session = Util.getSessionFactory().openSession()) {
            Transaction trans = session.beginTransaction();
            users = (List<User>) session.createQuery("FROM User").list();
            trans.commit();
        }
        catch (Exception e) {
            e.printStackTrace();
        }
        return users;
    }

    @Override
    public void cleanUsersTable() {
        try(Session session = Util.getSessionFactory().openSession()) {
            Transaction trans = session.beginTransaction();
            session.createSQLQuery("TRUNCATE TABLE Users").executeUpdate();
            trans.commit();
        }
        catch (Exception e) {
            e.printStackTrace();
        }
    }
}
