package pl.hubswi90.spring.OnlineShop.repository;


import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;
import pl.hubswi90.spring.OnlineShop.domain.Category;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;
import java.time.LocalDate;
import java.util.List;

@Repository
public class CategoryRepository {

    @PersistenceContext
    EntityManager em;


    @Transactional
    public void saveCategory(Category category) {

        category.setDate(LocalDate.now());
        em.persist(category);
    }

    public Category getCategorybyId(long id) {

        return em.find(Category.class, id);
    }

    public Category getCategoryByName(String categoryName) {
        Query query = em.createNamedQuery("getCategoryByName");
        query.setParameter("name", categoryName);

        List<?> result = query.getResultList();

        return (Category) result.get(0);
    }

    @Transactional
    public void updateCategory(Category category) {

        em.merge(category);
    }

    @Transactional
    public void removeCategoryById(long id) {

        Category category = em.find(Category.class, id);
        em.remove(category);
    }

    @Transactional
    public void removeCategoryByName(String name) {

        Category category = getCategoryByName(name);
        em.remove(category);
    }

    public List<Category> getAllCategory() {

        return em.createNamedQuery("getAllCategory", Category.class).getResultList();
    }
}
