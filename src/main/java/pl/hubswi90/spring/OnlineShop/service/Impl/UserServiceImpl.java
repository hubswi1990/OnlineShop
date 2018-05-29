package pl.hubswi90.spring.OnlineShop.service.Impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;
import pl.hubswi90.spring.OnlineShop.domain.Role;
import pl.hubswi90.spring.OnlineShop.domain.User;
import pl.hubswi90.spring.OnlineShop.repository.RoleRepository;
import pl.hubswi90.spring.OnlineShop.repository.UserRepository;
import pl.hubswi90.spring.OnlineShop.service.UserService;

import java.util.Arrays;
import java.util.HashSet;

@Service("userService")
public class UserServiceImpl implements UserService {

    @Autowired
    private UserRepository userRepository;
    @Autowired
    private RoleRepository roleRepository;
    @Autowired
    private BCryptPasswordEncoder bCryptPasswordEncoder;

    @Override
    public User findUserByEmail(String email) {
        return userRepository.findByEmail(email);
    }

    @Override
    public void saveUser(User user) {
        user.setPassword(bCryptPasswordEncoder.encode(user.getPassword()));
        user.setActive(1);
        Role userRole = roleRepository.findByRole("ADMIN");
        user.setRoles(new HashSet<Role>(Arrays.asList(userRole)));
        userRepository.save(user);
    }

    @Override
    public User findUserById(long id) {
        return userRepository.getOne(id);
    }

    @Override
    public boolean updateUser(User user) {
        User userInDatabase = findUserByQuery(user.getId());
        boolean changed = false;

        if(!user.getName().equals(userInDatabase.getName()) ||
                !user.getLastName().equals(userInDatabase.getLastName()) ||
                !user.getEmail().equals(userInDatabase.getEmail())) {
            changed = true;
            userInDatabase.setName(user.getName());
            userInDatabase.setLastName(user.getLastName());
            userInDatabase.setEmail(user.getEmail());
            userRepository.save(userInDatabase);
        }
        return changed;
    }

    @Override
    public User findUserByQuery(long id) {
        return userRepository.findBYUserId(id);
    }
}