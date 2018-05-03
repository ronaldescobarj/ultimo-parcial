package com.ucbcba.demo.services;

import com.ucbcba.demo.entities.Restaurant;
import com.ucbcba.demo.repository.RestaurantRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;

@Service
public class RestaurantServiceImpl implements RestaurantService {
    
    private RestaurantRepository restaurantRepository;

    @Autowired
    @Qualifier(value = "restaurantRepository")
    public void setRestaurantRepository(RestaurantRepository restaurantRepository) {
        this.restaurantRepository = restaurantRepository;
    }

    @Override
    public Iterable<Restaurant> listAllRestaurants() {
        return restaurantRepository.findAll();
    }

    @Override
    public void saveRestaurant(Restaurant restaurant) {
        restaurantRepository.save(restaurant);
    }

    @Override
    public Restaurant getRestaurant(Integer id) {
        return restaurantRepository.findById(id).get();
    }

    @Override
    public void deleteRestaurant(Integer id) {
        restaurantRepository.deleteById(id);
    }
    
}
