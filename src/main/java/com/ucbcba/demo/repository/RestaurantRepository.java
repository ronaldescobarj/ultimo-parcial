package com.ucbcba.demo.repository;

import com.ucbcba.demo.entities.Restaurant;
import org.springframework.data.repository.CrudRepository;
import javax.transaction.Transactional;

@Transactional
public interface RestaurantRepository extends CrudRepository<Restaurant, Integer> {

}
