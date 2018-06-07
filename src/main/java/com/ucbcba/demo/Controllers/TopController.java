package com.ucbcba.demo.Controllers;

import com.ucbcba.demo.entities.Category;
import com.ucbcba.demo.entities.City;
import com.ucbcba.demo.entities.Comment;
import com.ucbcba.demo.entities.Restaurant;
import com.ucbcba.demo.services.CityService;
import com.ucbcba.demo.services.CommentService;
import com.ucbcba.demo.services.RestaurantService;
import com.ucbcba.demo.services.UserService;
import org.springframework.beans.factory.annotation.Autowired;
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
public class TopController {

    private UserService userService;
    private RestaurantService restaurantService;
    private CommentService commentService;

    @Autowired
    public void setRestaurantService(RestaurantService restaurantService) {
        this.restaurantService = restaurantService;
    }

    @Autowired
    public void setUserService(UserService userService) {
        this.userService = userService;
    }

    @Autowired
    public void setCommentService(CommentService commentService) {
        this.commentService= commentService;
    }

    @RequestMapping(value = "topCommentingUsers", method = RequestMethod.GET)
    public String listTopCommentingUsers(Model model) {
        List<com.ucbcba.demo.entities.User> allUsers = new ArrayList<>();
        for (com.ucbcba.demo.entities.User user: userService.listAllUsers()) {
            allUsers.add(user);
        }
        for (int i = 0; i < allUsers.size(); i++) {
            allUsers.get(i).setNumberOfComments(commentService.numberOfCommentsByUser(allUsers.get(i).getId()));
        }
        allUsers.sort((u1, u2) -> {
            Integer c1, c2;
            c1 = u1.getNumberOfComments();
            c2 = u2.getNumberOfComments();
            return c2.compareTo(c1);
        });
        List<com.ucbcba.demo.entities.User> topThree = new ArrayList<>();
        for (int i = 0; i < 3; i++) {
            topThree.add(allUsers.get(i));
        }
        model.addAttribute("top", topThree);
        return "topCommentingUsersView";
    }

    @RequestMapping(value = "topCommentedRestaurants", method = RequestMethod.GET)
    public String listTopCommentedRestaurants(Model model) {
        List<Restaurant> allRestaurants = new ArrayList<>();
        for (Restaurant restaurant: restaurantService.listAllRestaurants()) {
            allRestaurants.add(restaurant);
        }
        for (int i = 0; i < allRestaurants.size(); i++) {
            allRestaurants.get(i).setNumberOfComments(commentService.numberOfCommentsByRestaurant(allRestaurants.get(i).getId()));
        }
        allRestaurants.sort((r1, r2) -> {
            Integer c1, c2;
            c1 = r1.getNumberOfComments();
            c2 = r2.getNumberOfComments();
            return c2.compareTo(c1);
        });
        List<Restaurant> topThree = new ArrayList<>();
        for (int i = 0; i < 3; i++) {
            topThree.add(allRestaurants.get(i));
        }
        model.addAttribute("top", topThree);
        return "topCommentedRestaurantsView";
    }

    @RequestMapping(value = "topQualifyingUsers", method = RequestMethod.GET)
    public String listTopQualifyingUsers(Model model) {
        List<com.ucbcba.demo.entities.User> allUsers = new ArrayList<>();
        for (com.ucbcba.demo.entities.User user: userService.listAllUsers()) {
            allUsers.add(user);
        }
        for (int i = 0; i < allUsers.size(); i++) {
            allUsers.get(i).setAverageScore(commentService.averageScoreByUser(allUsers.get(i).getId()));
        }
        allUsers.sort((u1, u2) -> {
            Float c1, c2;
            c1 = u1.getAverageScore();
            c2 = u2.getAverageScore();
            return c2.compareTo(c1);
        });
        List<com.ucbcba.demo.entities.User> topThree = new ArrayList<>();
        for (int i = 0; i < 3; i++) {
            topThree.add(allUsers.get(i));
        }
        model.addAttribute("top", topThree);
        return "topQualifyingUsersView";
    }

    @RequestMapping(value = "topWorstQualifyingUsers", method = RequestMethod.GET)
    public String listTopWorstQualifyingUsers(Model model) {
        List<com.ucbcba.demo.entities.User> allUsers = new ArrayList<>();
        for (com.ucbcba.demo.entities.User user: userService.listAllUsers()) {
            allUsers.add(user);
        }
        for (int i = 0; i < allUsers.size(); i++) {
            allUsers.get(i).setAverageScore(commentService.averageScoreByUser(allUsers.get(i).getId()));
        }
        allUsers.sort((u1, u2) -> {
            Float c1, c2;
            c1 = u1.getAverageScore();
            c2 = u2.getAverageScore();
            return c1.compareTo(c2);
        });
        List<com.ucbcba.demo.entities.User> topThree = new ArrayList<>();
        for (int i = 0; i < 3; i++) {
            topThree.add(allUsers.get(i));
        }
        model.addAttribute("top", topThree);
        return "topWorstQualifyingUsersView";
    }
}
