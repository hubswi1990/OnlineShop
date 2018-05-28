package pl.hubswi90.spring.OnlineShop.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;
import pl.hubswi90.spring.OnlineShop.domain.User;
import pl.hubswi90.spring.OnlineShop.service.UserService;

import javax.servlet.http.HttpServletRequest;
import javax.validation.Valid;

@Controller
@RequestMapping("/user")
public class UserAccountController {

    @Autowired
    UserService service;

    @RequestMapping(value = "/personalInfo", method = RequestMethod.GET)
    public ModelAndView showPersonalInfoForm(HttpServletRequest request) {
        Long userId = Long.parseLong(request.getSession().getAttribute("userId").toString());
        System.out.println(userId);
        ModelAndView view = new ModelAndView("user/information");
        view.addObject("user", service.findUserById(userId));
        return view;
    }

    @RequestMapping(value = "/personalInfo", method = RequestMethod.POST)
    public ModelAndView updatePersonalInfo(@Valid User user, BindingResult result,
                                     @RequestParam("passwd")String passwd,
                                     @RequestParam("newpasswd") String newpasswd) {

        ModelAndView view = new ModelAndView("user/information");
        boolean changed = service.updateUser(user);

        if(changed) {
            view.addObject("successMessage", "Information successfully updated.");
            return view;
        } else {
            view.addObject("errorMessage", "Personal Info no updated.");
            return view;
        }

    }
}
