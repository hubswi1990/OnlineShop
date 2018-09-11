package pl.hubswi90.spring.OnlineShop.controllers.rest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;
import pl.hubswi90.spring.OnlineShop.domain.Category;
import pl.hubswi90.spring.OnlineShop.service.CategoryService;

import java.util.List;

@RestController
@RequestMapping("/api/categories")
public class CategoryRestController {

    @Autowired
    private CategoryService categoryService;

    @GetMapping
    public List getAllCategory() {

        return categoryService.getAllCategoryFromDatabase();
    }

    @GetMapping("/{id}")
    public Category getCategory(@PathVariable long id) {

        return categoryService.getCategorybyIdFromDatabase(id);
    }

    @PostMapping
    @ResponseStatus(value = HttpStatus.OK)
    public void createCategory(@RequestBody Category category){

        categoryService.saveCategoruInDatabase(category);
    }

    @DeleteMapping("/{id}")
    @ResponseStatus(value = HttpStatus.OK)
    public void deleteCategory(@PathVariable long id) {

        categoryService.removeCategoryByIdInDatabse(id);
    }
}
