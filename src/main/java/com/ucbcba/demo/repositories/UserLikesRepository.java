package com.ucbcba.demo.repositories;

import com.ucbcba.demo.entities.UserLike;
import org.springframework.data.repository.CrudRepository;
import javax.transaction.Transactional;

@Transactional
public interface UserLikesRepository extends CrudRepository<UserLike,Integer> {
}
