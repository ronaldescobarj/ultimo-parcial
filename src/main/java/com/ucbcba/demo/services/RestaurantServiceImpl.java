package com.ucbcba.demo.services;

import com.ucbcba.demo.entities.Comment;
import com.ucbcba.demo.entities.Restaurant;
import com.ucbcba.demo.repositories.RestaurantRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;

import java.util.List;

//demonios
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
        return restaurantRepository.findOne(id);
    }

    @Override
    public void deleteRestaurant(Integer id) {
        restaurantRepository.delete(id);
    }

    @Override
    public List<Comment> listAllComments(Integer id) {
        return restaurantRepository.findOne(id).getComments();
    }

    @Override
    public boolean alreadyCommented(Integer userId, Integer restId) {
        List<Comment> comments = listAllComments(restId);
        for (Comment comment : comments) {
            if (comment.getUser().getId().equals(userId) && comment.getRestaurant().getId().equals(restId))
                return true;
        }
        return false;
    }

    @Override
    public Integer getScore(Integer id) {
        Integer average = 0;
        List<Comment> comments=listAllComments(id);
        if(!comments.isEmpty()){for(int i=0;i<comments.size();i++) {
            average=average+comments.get(i).getScore();
        }
         average=average/comments.size();}
        return average;
    }

}
