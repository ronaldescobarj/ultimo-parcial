package com.ucbcba.demo.services;


import com.ucbcba.demo.entities.UserLike;

public interface UserLikesService {

    Iterable<UserLike> listAllUserLikes();

    void saveUserLike(UserLike restaurant);

    UserLike getUserLike(Integer id);

    Boolean isLiked(Long userId, Integer restaurantId);

    void deleteUserLike(Long userId, Integer restaurantId);

    Integer getLikes(Integer restaurantId);

}
