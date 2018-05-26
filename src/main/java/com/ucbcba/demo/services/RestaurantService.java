package com.ucbcba.demo.services;


import com.ucbcba.demo.entities.Comment;
import com.ucbcba.demo.entities.Restaurant;

import java.util.List;


public interface RestaurantService {
    
    Iterable<Restaurant> listAllRestaurants();

    void saveRestaurant(Restaurant restaurant);

    Restaurant getRestaurant(Integer id);

    void deleteRestaurant(Integer id);

    List<Comment> listAllComments(Integer id);

    boolean alreadyCommented(Integer userId, Integer restId);

    Integer getScore(Integer id);

}
