package pl.hubswi90.spring.OnlineShop.service;

import pl.hubswi90.spring.OnlineShop.domain.User;

/**
public interface UserService {

    public void saveUserInDatabase(User user);
    public User getUserById(long id);
    public void UpdateUserDataInDatabase(User user);
    public void deleteUserFromBatabase(User user);
} **/

public interface UserService {
    public User findUserByEmail(String email);
    public void saveUser(User user);
}
