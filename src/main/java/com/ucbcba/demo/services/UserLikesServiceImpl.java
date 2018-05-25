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
    public Boolean thisUserDidLike(Long id) {
        List<UserLike> users;
        Boolean res = false;
        users = (List<UserLike>)userLikesRepository.findAll();
        for(int i=0;i<users.size();i++)
        {
            if(users.get(i).getUser().getId() == id)
                res=true;
        }
        return res;
    }

    @Override
    public void deleteUserLike(Integer id) {
        userLikesRepository.delete(id);
    }
}
