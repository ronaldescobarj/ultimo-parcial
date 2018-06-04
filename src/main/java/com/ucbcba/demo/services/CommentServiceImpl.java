package com.ucbcba.demo.services;

import com.ucbcba.demo.entities.Comment;
import com.ucbcba.demo.repositories.CommentRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

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
        return commentRepository.findOne(id);
    }

    @Override
    public void deleteComment(Integer id) {
        commentRepository.delete(id);
    }

    @Override
    public Iterable<Comment> listAllCommentsByUser(Integer userId) {
        List<Comment> allComments = (List<Comment>) commentRepository.findAll();
        List<Comment> res = new ArrayList<>();
        for(int i=0;i<allComments.size();i++)
        {
            if(allComments.get(i).getUser().getId() == userId)
                res.add(allComments.get(i));
        }
        return (Iterable<Comment>)res;
    }

}
