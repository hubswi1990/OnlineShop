package pl.hubswi90.spring.OnlineShop.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;
import pl.hubswi90.spring.OnlineShop.domain.User;
import pl.hubswi90.spring.OnlineShop.domain.Role;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import java.util.HashSet;
import java.util.Set;

/**@Repository
public class UserRepository {

    @PersistenceContext
    EntityManager em;

    @Transactional
    public void saveUser(User user) {
        BCryptPasswordEncoder bCryptPasswordEncoder = new BCryptPasswordEncoder();
        String passwordToHash = bCryptPasswordEncoder.encode(user.getPassword());
        user.setPassword(passwordToHash);
        Role role = em.find(Role.class, new Long(1));
        Set<Role> roles = new HashSet<Role>();
        roles.add(role);

        user.setRoles(roles);
        em.persist(user);
    }

    public User getUserById(long id) {

        return em.find(User.class, new Long(id));
    }

    @Transactional
    public void updateUser(User user) {

        em.merge(user);
    }

    public void removeUser(User user) {

        em.remove(user);
    }
} **/

@Repository("userRepository")
public interface UserRepository extends JpaRepository<User, Long> {
    User findByEmail(String email);
}
