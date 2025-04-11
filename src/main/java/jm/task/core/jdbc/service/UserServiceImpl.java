package jm.task.core.jdbc.service;

import jm.task.core.jdbc.dao.UserDaoHibernateImpl;
import jm.task.core.jdbc.model.User;

import java.util.List;

import jm.task.core.jdbc.dao.UserDao;

public class UserServiceImpl implements UserService {
    private final UserDao userDao = new UserDaoHibernateImpl();

    public void createUsersTable() {
        userDao.createUsersTable();
        System.out.println("Таблица Users создана.");
    }

    public void dropUsersTable() {
        userDao.dropUsersTable();
        System.out.println("Таблица Users удалена.");
    }

    public void saveUser(String name, String lastName, byte age) {
        userDao.saveUser(name, lastName, age);
        System.out.println("Пользователь " + name + " " + lastName + " " + age + " был добавлен.");
    }

    public void removeUserById(long id) {
        userDao.removeUserById(id);
        System.out.println("Пользователь id = " + id + " успешно удален.");
    }

    public List<User> getAllUsers() {
        return userDao.getAllUsers();
    }

    public void cleanUsersTable() {
        userDao.cleanUsersTable();
        System.out.println("Таблица Users очищена.");
    }
}
