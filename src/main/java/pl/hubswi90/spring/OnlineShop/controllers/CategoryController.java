package pl.hubswi90.spring.OnlineShop.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.PathVariable;
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
        ModelAndView view =  new ModelAndView("admin/category/addCategory");
        Category category = new Category();
        view.addObject("category", category);
        return view;
    }

    @RequestMapping(value = "/admin/category/add", method = RequestMethod.POST)
    public ModelAndView saveCategory(@Valid Category category, BindingResult bindingResult) {


        if (bindingResult.hasErrors()) {
            return new ModelAndView("admin/category/addCategory");
        } else {

            service.saveCategoruInDatabase(category);
            ModelAndView view = new ModelAndView("admin/category/addCategory");
            view.addObject("successMessage", "Category named " + category.getCategoryName() + " has been added");
            view.addObject("category", new Category());
            return view;
        }
    }

    @RequestMapping(value = "/admin/category", method = RequestMethod.GET)
    public ModelAndView category() {
        ModelAndView view = new ModelAndView("admin/category/category");
        view.addObject("categoryList", service.getAllCategoryFromDatabase());
        return view;
    }

    @RequestMapping(value = "/admin/category/delete/{id}", method = RequestMethod.GET)
    public String removeCategory(@PathVariable ("id") long id) {
        service.removeCategoryByIdInDatabse(id);
        return "redirect:/admin/category";
    }

    @RequestMapping(value = "/admin/category/edit/{id}", method = RequestMethod.GET)
    public ModelAndView showEditCategoryForm(@PathVariable long id) {
        ModelAndView view =  new ModelAndView("admin/category/editCategory");
        Category category = service.getCategorybyIdFromDatabase(id);
        view.addObject("category", category);
        return view;
    }

    @RequestMapping(value = "/admin/category/edit", method = RequestMethod.POST)
    public ModelAndView editCategoryInformationInDatabase(@Valid Category category, BindingResult bindingResult) {
        ModelAndView view = new ModelAndView("admin/category/editCategory");
        service.updateCategoryInDatabase(category);
        view.addObject("successMessage", "Category named " +category.getCategoryName() +" has been updated");
        return view;
    }
}
