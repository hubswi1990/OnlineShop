package pl.hubswi90.spring.OnlineShop.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.View;
import pl.hubswi90.spring.OnlineShop.domain.Address;
import pl.hubswi90.spring.OnlineShop.domain.User;
import pl.hubswi90.spring.OnlineShop.service.AddressService;
import pl.hubswi90.spring.OnlineShop.service.UserService;

import javax.servlet.http.HttpServletRequest;
import javax.validation.Valid;

@Controller
@RequestMapping("/user")
public class UserAccountController {

    @Autowired
    UserService service;

    @Autowired
    AddressService addressService;

    @RequestMapping(value = "/personalInfo", method = RequestMethod.GET)
    public ModelAndView showPersonalInfoForm(HttpServletRequest request) {
        Long userId = Long.parseLong(request.getSession().getAttribute("userId").toString());
        System.out.println(userId);
        ModelAndView view = new ModelAndView("user/information");
        view.addObject("user", service.findUserByQuery(userId));
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

    @RequestMapping(value = "/address", method = RequestMethod.GET)
    public ModelAndView shoowUserAddress(HttpServletRequest request) {

        Long userId = Long.parseLong(request.getSession().getAttribute("userId").toString());
        User user = service.findUserByQuery(userId);
        ModelAndView view = new ModelAndView("user/address");
        view.addObject("addressList", user.getAddressSet());
        return view;
    }

    @RequestMapping(value = "/address/add", method = RequestMethod.GET)
    public ModelAndView shoowAddAddressForm() {

        ModelAndView view = new ModelAndView("user/addAddress");
        view.addObject("address", new Address());
        return view;
    }

    @RequestMapping(value = "/address/add", method = RequestMethod.POST)
    public ModelAndView saveNewAddress(@Valid Address address, BindingResult result, HttpServletRequest request) {

        Long userId = Long.parseLong(request.getSession().getAttribute("userId").toString());
        addressService.saveAddressAndAddToUser(address, userId);

        User user = service.findUserByQuery(userId);

        ModelAndView view = new ModelAndView("user/address");
        view.addObject("successMessage", "Address successfully added!");
        view.addObject("addressList", user.getAddressSet());
        return view;
    }

    @RequestMapping(value = "address/delete/{id}", method = RequestMethod.GET)
    public ModelAndView deleteAddress(@PathVariable("id") long addressId, HttpServletRequest request) {

        Long userId = Long.parseLong(request.getSession().getAttribute("userId").toString());
        addressService.deleteAddress(userId, addressId);

        User user = service.findUserByQuery(userId);

        ModelAndView view = new ModelAndView("user/address");
        view.addObject("successMessage", "Address successfully removed!");
        view.addObject("addressList", user.getAddressSet());
        return view;
    }

    @RequestMapping(value = "address/edit/{id}", method = RequestMethod.GET)
    public ModelAndView showEditAdressForm(@PathVariable("id") long addressId) {

        ModelAndView view = new ModelAndView("user/editAddress");
        view.addObject("address", addressService.getAddressById(addressId));
        return view;
    }

    @RequestMapping(value = "address/edit", method = RequestMethod.POST)
    public ModelAndView editAdressInformation(@Valid Address address, HttpServletRequest request) {

        Long userId = Long.parseLong(request.getSession().getAttribute("userId").toString());
        addressService.editUserAddress(userId, address);
        User user = service.findUserByQuery(userId);

        ModelAndView view = new ModelAndView("user/address");
        view.addObject("successMessage", "Address successfully updated!");
        view.addObject("addressList", user.getAddressSet());
        return view;
    }
}
