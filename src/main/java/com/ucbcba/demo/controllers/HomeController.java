package com.ucbcba.demo.Controllers;

import com.ucbcba.demo.entities.Category;
import com.ucbcba.demo.entities.City;
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
import org.springframework.web.bind.annotation.RequestParam;

import java.util.*;
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
    public String welcome(Model model, @RequestParam(value = "searchFilter", required = false, defaultValue="") String searchFilter, @RequestParam(value = "cityDropdown", required = false, defaultValue="") String cityDropdown, @RequestParam(value = "showContent", required = false, defaultValue="") String showContent) {
        Authentication auth = SecurityContextHolder.getContext().getAuthentication();
        Boolean logged = (!getUserRole(auth).equals("notLogged"));
        com.ucbcba.demo.entities.User user = new com.ucbcba.demo.entities.User();
        User u;
        if(logged == true)
        {
        u = (org.springframework.security.core.userdetails.User)auth.getPrincipal();
        user = userService.findByUsername(u.getUsername());
        }
        model.addAttribute("user",user);
        model.addAttribute("role", getUserRole(auth));
        model.addAttribute("logged", logged);
        model.addAttribute("cities", cityService.listAllCities());
        String search = "";
        if (!searchFilter.equals("")) {
            search = searchFilter;
        }
        model.addAttribute("search", search);

        String citySelected = "";
        if (!cityDropdown.equals("") || !cityDropdown.equals("All cities")) {
            citySelected = cityDropdown;
        }
        model.addAttribute("citySelected", citySelected);

        String showTable = "table";
        if (showContent.equals("map")) {
            showTable = showContent;
        }
        model.addAttribute("showTable", showTable);
        List<Restaurant> allRestaurants = new ArrayList<>();
        for (Restaurant restaurant : restaurantService.listAllRestaurants()) {
            allRestaurants.add(restaurant);
        }
        List<Restaurant> filteredRestaurants = new ArrayList<>();
        if (searchFilter.equals("") && (cityDropdown.equals("") || cityDropdown.equals("All cities"))) {
            filteredRestaurants = allRestaurants;
        } else {
            if (!searchFilter.equals("") && (cityDropdown.equals("") || cityDropdown.equals("All cities"))) {
                for (int i = 0; i < allRestaurants.size(); i++) {
                    if (allRestaurants.get(i).getName().toLowerCase().contains(searchFilter.toLowerCase())
                            || searchCategories(allRestaurants.get(i).getCategories(), searchFilter.toLowerCase())) {
                        filteredRestaurants.add(allRestaurants.get(i));
                    }
                }
            }
            else {
                if (searchFilter.equals("") && !cityDropdown.equals("") && !cityDropdown.equals("All cities")) {
                    System.out.println("entra");
                    for (int i = 0; i < allRestaurants.size(); i++) {
                        if (allRestaurants.get(i).getCity().getName().equals(cityDropdown)) {
                            filteredRestaurants.add(allRestaurants.get(i));
                        }
                    }
                }
                else {
                    for (int i = 0; i < allRestaurants.size(); i++) {
                        if ((allRestaurants.get(i).getName().toLowerCase().contains(searchFilter.toLowerCase())
                                || searchCategories(allRestaurants.get(i).getCategories(), searchFilter.toLowerCase()))
                                && allRestaurants.get(i).getCity().getName().equals(cityDropdown)) {
                            filteredRestaurants.add(allRestaurants.get(i));
                        }
                    }
                }
            }
        }
        model.addAttribute("restaurants", filteredRestaurants);




        List<Restaurant> temp = new ArrayList<>();
        filteredRestaurants.forEach(temp::add);
        List<Restaurant> restaurantsList = new ArrayList<>();
        for (int i = 0; i < temp.size(); i++) {
            Restaurant r = new Restaurant();
            r.setName(temp.get(i).getName());
            r.setLatitude(temp.get(i).getLatitude());
            r.setLongitude(temp.get(i).getLongitude());
            restaurantsList.add(r);
        }
        model.addAttribute("restaurantsList", restaurantsList);
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
