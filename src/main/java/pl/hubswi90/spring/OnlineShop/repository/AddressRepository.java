package pl.hubswi90.spring.OnlineShop.repository;

import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;
import pl.hubswi90.spring.OnlineShop.domain.Address;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

@Repository
@Transactional
public class AddressRepository {

    @PersistenceContext
    EntityManager em;

    public void saveAddress(Address address) {

        em.persist(address);
    }

    public Address getAddressById(long id) {

        return (Address) em.createNamedQuery("getAddressById").setParameter("id", id).getResultList().get(0);
    }

    public void updateAddress(Address address) {
        em.merge(address);
    }

    public void deleteAddressInDatabase(long addressId) {

        em.remove(getAddressById(addressId));
    }
}
