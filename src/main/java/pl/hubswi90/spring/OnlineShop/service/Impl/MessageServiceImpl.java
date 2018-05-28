package pl.hubswi90.spring.OnlineShop.service.Impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import pl.hubswi90.spring.OnlineShop.domain.Message;
import pl.hubswi90.spring.OnlineShop.repository.MessageRepository;
import pl.hubswi90.spring.OnlineShop.service.MessageService;

import java.time.LocalDate;

@Service
public class MessageServiceImpl implements MessageService {

    @Autowired
    MessageRepository repository;

    @Override
    public void saveMessage(Message message) {
        message.setDate(LocalDate.now());
        repository.save(message);
    }
}
