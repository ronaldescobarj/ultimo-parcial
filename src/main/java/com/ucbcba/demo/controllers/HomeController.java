package com.ucbcba.demo.controllers;

import com.ucbcba.demo.entities.Category;
import com.ucbcba.demo.entities.Restaurant;
import com.ucbcba.demo.services.CityService;
import com.ucbcba.demo.services.RestaurantService;
import com.ucbcba.demo.services.UserService;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.User;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import java.util.ArrayList;
import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

@Controller
public class HomeController {

    private final UserService userService;
    private final RestaurantService restaurantService;
    private final CityService cityService;

    public HomeController(UserService userService, RestaurantService restaurantService, CityService cityService) {
        this.userService = userService;
        this.restaurantService = restaurantService;
        this.cityService = cityService;
    }

    @RequestMapping(value = {"/", "/home"}, method = RequestMethod.GET)
    public String welcome(Model model) {
        Authentication auth = SecurityContextHolder.getContext().getAuthentication();
        Boolean logged = (!getUserRole(auth).equals("notLogged"));


        model.addAttribute("role", getUserRole(auth));
        model.addAttribute("logged", logged);
        model.addAttribute("cities", cityService.listAllCities());
        model.addAttribute("restaurants", restaurantService.listAllRestaurants());
        model.addAttribute("searchFilter", "");
        return "home";
    }

    @RequestMapping(value = {"/search"}, method = RequestMethod.POST)
    public String search(Model model, String searchFilter) {

        List<Restaurant> restaurantList = new ArrayList<>();
        for (Restaurant restaurant : restaurantService.listAllRestaurants()) {
            restaurantList.add(restaurant);
        }
        List<Restaurant> restaurants = restaurantList.stream().filter(
                p -> (p.getName().toLowerCase().contains(searchFilter.toLowerCase())
                        || searchCategories(p.getCategories(), searchFilter.toLowerCase()))
        ).collect(Collectors.toList());

        Authentication auth = SecurityContextHolder.getContext().getAuthentication();
        Boolean logged = (!getUserRole(auth).equals("notLogged"));
        model.addAttribute("role", getUserRole(auth));
        model.addAttribute("logged", logged);
        model.addAttribute("restaurants", restaurants);
        model.addAttribute("searchFilter", searchFilter);
        model.addAttribute("cities", cityService.listAllCities());
        return "home";
    }

    private Boolean searchCategories(Set<Category> categories, String param) {
        for (Category category : categories) {
            if (category.getName().toLowerCase().contains(param))
                return true;
        }
        return false;
    }

    private String getUserRole(Authentication auth) {
        if (!auth.getPrincipal().equals("anonymousUser")) {
            User u = (org.springframework.security.core.userdetails.User) auth.getPrincipal();
            com.ucbcba.demo.entities.User user = userService.findByUsername(u.getUsername());
            return user.getRole().toLowerCase();
        }
        return "notLogged";
    }
}
