package pl.hubswi90.spring.OnlineShop.service;

import pl.hubswi90.spring.OnlineShop.domain.Category;

import java.util.List;

public interface CategoryService {

    public void saveCategoruInDatabase(Category category);
    public Category getCategorybyIdFromDatabase(long id);
    public Category getCategoryByNameFromDatabase(String categoryName);
    public void updateCategoryInDatabase(Category category);
    public void removeCategoryByIdInDatabse(long id);
    public void removeCategoryByNameInDatabase(String name);
    public List<Category> getAllCategoryFromDatabase();

    void duplicateCategory(long id);
}
