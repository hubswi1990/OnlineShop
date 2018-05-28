package pl.hubswi90.spring.OnlineShop.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import pl.hubswi90.spring.OnlineShop.domain.Message;

@Repository
public interface MessageRepository extends JpaRepository<Message, Long> {
}
