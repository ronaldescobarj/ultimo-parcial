package com.ucbcba.demo.services;


import com.ucbcba.demo.entities.UserLike;

public interface UserLikesService {

    Iterable<UserLike> listAllUserLikes();

    void saveUserLike(UserLike restaurant);

    UserLike getUserLike(Integer id);

    Boolean thisUserDidLike(Long id);

    void deleteUserLike(Integer id);

}
