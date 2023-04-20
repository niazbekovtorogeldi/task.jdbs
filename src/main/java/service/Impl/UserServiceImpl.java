package service.Impl;

import model.User;
import model.dao.UserDao;
import model.dao.impl.UserDaoImpl;
import service.UserService;

import java.util.List;

public class UserServiceImpl implements UserService {
    UserDao userDao = new UserDaoImpl();
    @Override
    public void createTable() {
        userDao.createTable();
    }

    @Override
    public void saveUser(User user) {
        userDao.saveUser(user);

    }

    @Override
    public User getUserById(Long id) {
        return userDao.getUserById(id);
    }

    @Override
    public List<User> getAllUsers() {
        return userDao.getAllUsers();
    }

    @Override
    public void updateUserInfo(Long id, User user) {
        userDao.updateUserInfo(id,user);
    }

    @Override
    public void cleanUserTable() {
        userDao.cleanUserTable();
    }

    @Override
    public void dropUserTable() {
        userDao.dropUserTable();

    }

    @Override
    public void deleteById(Long id) {
        userDao.deleteById(1L);

    }

    @Override
    public List<User> getAllSortedUsers(String ascOrDesc) {
        return userDao.getAllSortedUsers("Asc");
    }
}
