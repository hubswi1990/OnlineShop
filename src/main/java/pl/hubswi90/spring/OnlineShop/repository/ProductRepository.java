package pl.hubswi90.spring.OnlineShop.repository;

import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;
import pl.hubswi90.spring.OnlineShop.domain.Product;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import java.util.List;

@Repository
public class ProductRepository {

    @PersistenceContext
    EntityManager entityManager;

    @Transactional
    public void saveProductInDatabase(Product product) {
        entityManager.persist(product);
    }

    @Transactional
    public Product getProoductFromDatabse(long id) {
        return entityManager.find(Product.class, id);
    }

    @Transactional
    public List<Product> getAllProductFromDatabse(){
        return entityManager.createNamedQuery("getAllProduct", Product.class).getResultList();
    }

    @Transactional
    public void removeProductInDatabase(long id) {
        entityManager.remove(getProoductFromDatabse(id));
    }

    @Transactional
    public void updateProductInDatabase(Product p) {
        entityManager.merge(p);
    }
}
