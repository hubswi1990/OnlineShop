package pl.hubswi90.spring.OnlineShop.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;
import pl.hubswi90.spring.OnlineShop.domain.Category;
import pl.hubswi90.spring.OnlineShop.service.CategoryService;

import javax.validation.Valid;


@Controller
public class CategoryController {

    @Autowired
    CategoryService service;


    @RequestMapping(value = "/admin/category/add", method = RequestMethod.GET)
    public ModelAndView addCategoryForm() {
        ModelAndView view =  new ModelAndView();
        Category category = new Category();
        view.addObject("category", category);
        view.setViewName("admin/addCategory");
        return view;
    }

    @RequestMapping(value = "/admin/category/add", method = RequestMethod.POST)
    public ModelAndView saveCategory(@Valid Category category, BindingResult bindingResult) {
        ModelAndView view = new ModelAndView();

        service.saveCategoruInDatabase(category);
        view.addObject("successMessage", "Category named " +category.getCategoryName() +" has been added");
        view.addObject("category", new Category());
        view.setViewName("admin/addCategory");
        return view;
    }

    @RequestMapping(value = "/admin/category", method = RequestMethod.GET)
    public ModelAndView category() {
        ModelAndView view = new ModelAndView();
        view.addObject("categoryList", service.getAllCategoryFromDatabase());
        view.setViewName("admin/category");
        return view;
    }
}
