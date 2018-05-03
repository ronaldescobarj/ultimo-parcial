package com.ucbcba.demo.repository;

import com.ucbcba.demo.entities.Photo;
import org.springframework.data.repository.CrudRepository;
import javax.transaction.Transactional;

@Transactional
public interface PhotoRepository extends CrudRepository<Photo, Integer> {
}
