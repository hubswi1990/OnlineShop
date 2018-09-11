package pl.hubswi90.spring.OnlineShop.service.Impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import pl.hubswi90.spring.OnlineShop.domain.Subscriber;
import pl.hubswi90.spring.OnlineShop.repository.SubscriberRepository;
import pl.hubswi90.spring.OnlineShop.service.SubscriberService;

@Service
public class SubscriberServiceImpl  implements SubscriberService {

    @Autowired
    SubscriberRepository repository;

    @Override
    public void saveSubscriber(Subscriber subscriber) {

        repository.save(subscriber);
    }
}
