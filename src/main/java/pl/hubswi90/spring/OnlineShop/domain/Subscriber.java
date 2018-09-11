package pl.hubswi90.spring.OnlineShop.domain;

import javax.persistence.*;
import javax.validation.constraints.Email;
import javax.validation.constraints.NotNull;
import java.time.LocalDate;

@Entity
@Table(name = "Subscribers")
public class Subscriber {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long subscriberId;

    @Email
    @NotNull
    @Column(name = "email", nullable = false)
    private String subscriberEmail;

    @Column(name = "date")
    private LocalDate dateAddedToDatabase;

    public Subscriber() {}

    public Subscriber(@Email @NotNull String subscriberEmail) {
        this.subscriberEmail = subscriberEmail;
    }

    public long getSubscriberId() {
        return subscriberId;
    }

    public String getSubscriberEmail() {
        return subscriberEmail;
    }

    public void setSubscriberEmail(String subscriberEmail) {
        this.subscriberEmail = subscriberEmail;
    }

    public LocalDate getDateAddedToDatabase() {
        return dateAddedToDatabase;
    }

    public void setDateAddedToDatabase(LocalDate dateAddedToDatabase) {
        this.dateAddedToDatabase = dateAddedToDatabase;
    }
}
