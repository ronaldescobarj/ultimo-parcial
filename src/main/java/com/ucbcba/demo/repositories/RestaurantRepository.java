package com.ucbcba.demo.repositories;

import com.ucbcba.demo.entities.Restaurant;
import org.springframework.data.repository.CrudRepository;
import javax.transaction.Transactional;

@Transactional
public interface RestaurantRepository extends CrudRepository<Restaurant, Integer> {

}
