package pl.hubswi90.spring.OnlineShop.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.view.RedirectView;
import pl.hubswi90.spring.OnlineShop.domain.Message;
import pl.hubswi90.spring.OnlineShop.domain.Subscriber;
import pl.hubswi90.spring.OnlineShop.service.CategoryService;
import pl.hubswi90.spring.OnlineShop.service.MessageService;
import pl.hubswi90.spring.OnlineShop.service.ProductService;
import pl.hubswi90.spring.OnlineShop.service.SubscriberService;

import javax.validation.Valid;
import java.time.LocalDate;

@Controller
public class FrontPagesController {

    @Autowired
    CategoryService categoryService;

    @Autowired
    ProductService productService;

    @Autowired
    MessageService messageService;

    @Autowired
    SubscriberService subscriberService;

    @RequestMapping(value = {"/", "/products"}, method = RequestMethod.GET)
    public ModelAndView index() {
        ModelAndView view = new ModelAndView("showProducts");
        view.addObject("categoryList", categoryService.getAllCategoryFromDatabase());
        view.addObject("productList", productService.getAllProduct());
        return view;
    }

    @RequestMapping(value = "/productInfo/{id}", method = RequestMethod.GET)
    public ModelAndView showProductinfoPage(@PathVariable long id) {
        ModelAndView view =  new ModelAndView("showProductInfo");
        view.addObject("categoryList", categoryService.getAllCategoryFromDatabase());
        view.addObject("product", productService.getProduct(id));
        return view;
    }

    @RequestMapping(value = "/products/category/{id}", method = RequestMethod.GET)
    public ModelAndView showProductInCategory(@PathVariable long id) {
        ModelAndView view =  new ModelAndView("showProductsInCategory");
        view.addObject("categoryList", categoryService.getAllCategoryFromDatabase());
        view.addObject("categoryName", categoryService.getCategorybyIdFromDatabase(id).getCategoryName());
        view.addObject("productList", productService.getProductsInCategory(id));
        return view;
    }

    @RequestMapping(value = "/contact", method = RequestMethod.GET)
    public ModelAndView showContactPage() {

        ModelAndView view = new ModelAndView("contact");
        view.addObject("message", new Message());
        return view;
    }

    @RequestMapping(value = "/contact", method = RequestMethod.POST)
    public RedirectView saveMessage(@Valid Message message, BindingResult result) {

        if(!result.hasErrors()) {
            message.setDate(LocalDate.now());
            messageService.saveMessage(message);
        }

        return new RedirectView("/");
    }


    @RequestMapping(value = "/subscribe", method = RequestMethod.GET)
    public ModelAndView showSubscribePage() {

        ModelAndView view = new ModelAndView("subscribe");
        view.addObject("subscriber", new Subscriber());
        return view;
    }

    @RequestMapping(value = "/subscribe", method = RequestMethod.POST)
    public  RedirectView addSubscriberToDatabase(@Valid Subscriber subscriber, BindingResult result) {

        if(!result.hasErrors()){
            subscriber.setDateAddedToDatabase(LocalDate.now());
            subscriberService.saveSubscriber(subscriber);
        }

        return new RedirectView("/");
    }
}
