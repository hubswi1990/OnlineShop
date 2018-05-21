package pl.hubswi90.spring.OnlineShop.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;
import pl.hubswi90.spring.OnlineShop.domain.User;
import pl.hubswi90.spring.OnlineShop.service.UserService;

import javax.servlet.http.HttpSession;

@Controller
public class UserLoginCotroller {

    @Autowired
    private UserService userService;

    @RequestMapping(value="/user/home", method = RequestMethod.GET)
    public ModelAndView Userhome(HttpSession session){
        ModelAndView modelAndView = new ModelAndView("user/home");
        Authentication auth = SecurityContextHolder.getContext().getAuthentication();
        User user = userService.findUserByEmail(auth.getName());

        session.setAttribute("userSessionAtribute", "Welcome " + user.getName() + " " + user.getLastName() + " (" + user.getEmail() + ")");
        session.setAttribute("adminMessage","Content Available Only for Users with User Role");

        return modelAndView;
    }
}
