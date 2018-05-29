package com.ucbcba.demo.services;

import com.ucbcba.demo.entities.UserLike;
import com.ucbcba.demo.repositories.UserLikesRepository;
import ognl.IteratorElementsAccessor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;
import java.util.List;

@Service
public class UserLikesServiceImpl implements UserLikesService {

    UserLikesRepository userLikesRepository;

    @Autowired
    @Qualifier(value = "userLikesRepository")
    public void setUserLikesRepository(UserLikesRepository userLikesRepository)
    {
        this.userLikesRepository = userLikesRepository;
    }

    @Override
    public Iterable<UserLike> listAllUserLikes() {
        return userLikesRepository.findAll();
    }

    @Override
    public void saveUserLike(UserLike userLike) {
        userLikesRepository.save(userLike);
    }

    @Override
    public UserLike getUserLike(Integer id) {
        return userLikesRepository.findOne(id);
    }

    @Override
    public Boolean isLiked(Integer userId, Integer restaurantId) {
        List<UserLike> usersLike;
        Boolean res = false;
        usersLike = (List<UserLike>)userLikesRepository.findAll();
        for(int i=0;i<usersLike.size();i++)
        {
            if (usersLike.get(i).getUser().getId() == userId && usersLike.get(i).getRestaurant().getId() == restaurantId)
                res=true;
        }
        return res;
    }

    @Override
    public void deleteUserLike(Integer userId, Integer restaurantId) {
        List<UserLike> usersLike;
        usersLike = (List<UserLike>)userLikesRepository.findAll();
        for(int i=0;i<usersLike.size();i++)
        {
            if (usersLike.get(i).getUser().getId() == userId && usersLike.get(i).getRestaurant().getId() == restaurantId) {
                userLikesRepository.delete(usersLike.get(i).getId());
            }
        }
    }

    @Override
    public Integer getLikes(Integer restaurantId) {
        List<UserLike> usersLike;
        usersLike = (List<UserLike>)userLikesRepository.findAll();
        Integer count = 0;
        for(int i = 0; i < usersLike.size(); i++)
        {
            if (usersLike.get(i).getRestaurant().getId() == restaurantId) {
                count++;
            }
        }
        return count;
    }
}
