package com.ucbcba.demo.services;

import com.ucbcba.demo.entities.Comment;
import com.ucbcba.demo.repository.CommentRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;

@Service
public class CommentServiceImpl implements CommentService {

    private CommentRepository commentRepository;

    @Autowired
    @Qualifier(value = "commentRepository")
    public void setCommentRepository(CommentRepository commentRepository) {
        this.commentRepository = commentRepository;
    }

    @Override
    public Iterable<Comment> listAllComments() {
        return commentRepository.findAll();
    }

    @Override
    public void saveComment(Comment comment) {
        commentRepository.save(comment);
    }

    @Override
    public Comment getComment(Integer id) {
        return commentRepository.findById(id).get();
    }

    @Override
    public void deleteComment(Integer id) {
        commentRepository.deleteById(id);
    }

}
