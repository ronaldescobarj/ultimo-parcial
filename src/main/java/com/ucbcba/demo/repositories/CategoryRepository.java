package com.ucbcba.demo.repositories;

import com.ucbcba.demo.entities.Category;
import com.ucbcba.demo.entities.City;
import org.springframework.data.repository.CrudRepository;
import javax.transaction.Transactional;

@Transactional
public interface CategoryRepository extends CrudRepository<Category, Integer> {
}
