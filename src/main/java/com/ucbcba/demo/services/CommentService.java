package com.ucbcba.demo.services;

import com.ucbcba.demo.entities.Comment;

public interface CommentService {

    Iterable<Comment> listAllComments();

    void saveComment(Comment comment);

    Comment getComment(Integer id);

    void deleteComment(Integer id);

    Iterable<Comment> listAllCommentsByUser(Integer userId);

    Integer numberOfCommentsByUser(Integer userId);

    Integer numberOfCommentsByRestaurant(Integer restaurantId);

    Float averageScoreByUser(Integer userId);

}
