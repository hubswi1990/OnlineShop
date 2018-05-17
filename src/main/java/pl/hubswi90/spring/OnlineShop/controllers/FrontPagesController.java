package pl.hubswi90.spring.OnlineShop.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;
import pl.hubswi90.spring.OnlineShop.domain.Category;
import pl.hubswi90.spring.OnlineShop.service.CategoryService;
import pl.hubswi90.spring.OnlineShop.service.ProductService;

@Controller
public class FrontPagesController {

    @Autowired
    CategoryService categoryService;

    @Autowired
    ProductService productService;

    @RequestMapping(value = "/", method = RequestMethod.GET)
    public ModelAndView index() {
        ModelAndView view = new ModelAndView("showProducts");
        view.addObject("categoryList", categoryService.getAllCategoryFromDatabase());
        view.addObject("productList", productService.getAllProduct());
        return view;
    }

    @RequestMapping(value = "/productInfo/{id}", method = RequestMethod.GET)
    public ModelAndView showEditCategoryForm(@PathVariable long id) {
        ModelAndView view =  new ModelAndView("showProductInfo");
        view.addObject("categoryList", categoryService.getAllCategoryFromDatabase());
        view.addObject("product", productService.getProduct(id));
        return view;
    }
}
