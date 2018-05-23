package pl.hubswi90.spring.OnlineShop.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;
import pl.hubswi90.spring.OnlineShop.service.ShoppingCartService;

import javax.servlet.http.HttpSession;

@Controller
@RequestMapping(value = "/shoppingcart")
public class ShoppingCartController {

    @Autowired
    ShoppingCartService cartService;

    @GetMapping()
    public ModelAndView showShoppingCart(HttpSession session) {
        ModelAndView view = new ModelAndView("/ShoppingCart");
        view.addObject("productsMap", cartService.getProductsInCart());
        view.addObject("totalPrice", cartService.getTotalPrice());
        session.setAttribute("sessionTotalPrice", cartService.getTotalPrice());
        return view;
    }

    @GetMapping(value = "/add/{id}")
    public String addProductToCart(@PathVariable Long id) {
        cartService.addProduct(id);
        return "redirect:/shoppingcart";
    }

    @GetMapping(value = "/delete/{id}")
    public String removeProductToCart(@PathVariable Long id) {
        cartService.removeProduct(id);
        return "redirect:/shoppingcart";
    }


}
