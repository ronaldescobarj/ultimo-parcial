package com.ucbcba.demo.repositories;

import com.ucbcba.demo.entities.Photo;
import org.springframework.data.repository.CrudRepository;
import javax.transaction.Transactional;

@Transactional
public interface PhotoRepository extends CrudRepository<Photo, Integer> {
}
