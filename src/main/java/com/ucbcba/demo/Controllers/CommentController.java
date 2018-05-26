package com.ucbcba.demo.Controllers;

import com.ucbcba.demo.entities.Comment;
import com.ucbcba.demo.services.CommentService;
import com.ucbcba.demo.services.CommentServiceImpl;
import com.ucbcba.demo.entities.Restaurant;
import com.ucbcba.demo.services.RestaurantService;
import com.ucbcba.demo.services.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.User;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import javax.validation.Valid;

@Controller
public class CommentController {
    private CommentService commentService;
    private UserService userService;

    @Autowired
    public void setCommentService(CommentService commentService) {
        this.commentService = commentService;
    }

    @Autowired
    public void setUserService(UserService userService) {
        this.userService= userService;
    }

    @RequestMapping(value = "/comment", method = RequestMethod.POST)
    String save(Comment comment, BindingResult bindingResult) {
        Authentication auth = SecurityContextHolder.getContext().getAuthentication();
        User u = (org.springframework.security.core.userdetails.User)auth.getPrincipal();
        com.ucbcba.demo.entities.User user = userService.findByUsername(u.getUsername());
        comment.setUser(user);
        commentService.saveComment(comment);
        return "redirect:/restaurant/" + comment.getRestaurant().getId();
    }
}
