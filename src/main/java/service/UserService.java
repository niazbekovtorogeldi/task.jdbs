package service;

import model.User;

import java.util.List;

public interface UserService {
    void createTable();
    void saveUser(User user);
    User getUserById(Long id);
    List<User> getAllUsers();
    void updateUserInfo(Long id, User user);
    void cleanUserTable();
    void  dropUserTable();
    void deleteById(Long id );
    List<User>getAllSortedUsers(String ascOrDesc);
}
