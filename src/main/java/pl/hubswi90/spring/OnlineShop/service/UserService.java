package pl.hubswi90.spring.OnlineShop.service;

import pl.hubswi90.spring.OnlineShop.domain.User;

public interface UserService {
    public User findUserByEmail(String email);
    public void saveUser(User user);
}
