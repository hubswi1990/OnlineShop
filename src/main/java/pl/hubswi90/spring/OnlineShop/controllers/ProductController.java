package pl.hubswi90.spring.OnlineShop.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.ModelAndView;
import pl.hubswi90.spring.OnlineShop.domain.Category;
import pl.hubswi90.spring.OnlineShop.domain.Product;
import pl.hubswi90.spring.OnlineShop.service.CategoryService;
import pl.hubswi90.spring.OnlineShop.service.ProductService;
import sun.reflect.generics.reflectiveObjects.NotImplementedException;

import javax.validation.Valid;
import java.io.IOException;

@Controller
public class ProductController {

    @Autowired
    ProductService service;

    @Autowired
    CategoryService categoryService;

    @RequestMapping(value = "/admin/product", method = RequestMethod.GET)
    public ModelAndView showProducts() {
        ModelAndView view = new ModelAndView("admin/product/product");
        view.addObject("productList", service.getAllProduct());
        return view;
    }

    @RequestMapping(value = "/admin/product/add", method = RequestMethod.GET)
    public ModelAndView addProductForm() {
        ModelAndView view =  new ModelAndView("admin/product/addProduct");
        Product product = new Product();
        view.addObject("product", product);
        view.addObject("categoryList", categoryService.getAllCategoryFromDatabase());
        return view;
    }

    @RequestMapping(value = "/admin/product/add", method = RequestMethod.POST)
    public ModelAndView saveProduct(@Valid Product product, BindingResult bindingResult, @RequestParam("myFile")MultipartFile file, @RequestParam("myCategory")Long id) throws IOException {

        if(bindingResult.hasErrors()) {

            ModelAndView view =  new ModelAndView("admin/product/addProduct");
            view.addObject("categoryList", categoryService.getAllCategoryFromDatabase());
            return view;
        } else {
            ModelAndView view = new ModelAndView("admin/product/addProduct");

            product.setPhoto(file.getBytes());

            Category category = categoryService.getCategorybyIdFromDatabase(id);
            product.setProductCategory(category);
            service.saveProduct(product);
            view.addObject("successMessage", "Product named " +product.getProductName() +" has been added");
            view.addObject("product", new Product());
            return view;
        }
    }

    @RequestMapping(value = "/admin/product/delete/{id}", method = RequestMethod.GET)
    public String removeProduct(@PathVariable("id") long id) {
        service.deleteProduct(id);
        return "redirect:/admin/product";
    }

    @RequestMapping(value = "/admin/product/changeStatus/{id}", method = RequestMethod.GET)
    public String changeStatusProduct(@PathVariable long id) {
        Product p = service.getProduct(id);
        p.setProductStatus(!p.isProductStatus());
        service.updateProduct(p);
        return "redirect:/admin/product";
    }

    @RequestMapping(value = "/admin/product/edit/{id}", method = RequestMethod.GET)
    public ModelAndView showEditProductForm(@PathVariable long id) {
        throw new NotImplementedException();
    }

    @RequestMapping(value = "/admin/product/edit", method = RequestMethod.POST)
    public ModelAndView editProductInformationInDatabase() {
        throw new NotImplementedException();
    }
}
