package com.ucbcba.demo.repository;

import com.ucbcba.demo.entities.Comment;
import org.springframework.data.repository.CrudRepository;
import javax.transaction.Transactional;

@Transactional
public interface CommentRepository extends CrudRepository<Comment, Integer> {
    
}
