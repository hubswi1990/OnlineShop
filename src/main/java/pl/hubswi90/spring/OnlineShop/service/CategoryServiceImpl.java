package pl.hubswi90.spring.OnlineShop.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import pl.hubswi90.spring.OnlineShop.domain.Category;
import pl.hubswi90.spring.OnlineShop.repository.CategoryRepository;

import java.time.LocalDate;
import java.util.List;

@Service
public class CategoryServiceImpl implements CategoryService {

    @Autowired
    CategoryRepository categoryRepository;


    @Transactional
    @Override
    public void saveCategoruInDatabase(Category category) {

        categoryRepository.saveCategory(category);
    }

    @Override
    public Category getCategorybyIdFromDatabase(long id) {

        return categoryRepository.getCategorybyId(id);
    }

    @Override
    public Category getCategoryByNameFromDatabase(String categoryName) {

        return categoryRepository.getCategoryByName(categoryName);
    }

    @Transactional
    @Override
    public void updateCategoryInDatabase(Category category) {

        categoryRepository.updateCategory(category);
    }

    @Transactional
    @Override
    public void removeCategoryByIdInDatabse(long id) {

        categoryRepository.removeCategoryById(id);
    }

    @Transactional
    @Override
    public void removeCategoryByNameInDatabase(String name) {

        categoryRepository.removeCategoryByName(name);
    }

    @Override
    public List<Category> getAllCategoryFromDatabase() {

        return categoryRepository.getAllCategory();
    }

    @Transactional
    @Override
    public void duplicateCategory(long id) {
        Category category = categoryRepository.getCategorybyId(id);
        Category newCategory = new Category(category.getCategoryName(), category.getDescription());
        newCategory.setDate(LocalDate.now());
        categoryRepository.saveCategory(newCategory);
    }
}
