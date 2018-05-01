package com.ucbcba.demo.repository;

import com.ucbcba.demo.entities.User;
import org.springframework.data.repository.CrudRepository;

import javax.transaction.Transactional;

@Transactional
public interface UserRepository extends CrudRepository<User, Integer> {
}
