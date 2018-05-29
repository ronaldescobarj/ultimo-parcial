package com.ucbcba.demo.Controllers;

import com.ucbcba.demo.entities.Comment;
import com.ucbcba.demo.entities.User;
import com.ucbcba.demo.services.CommentService;
import com.ucbcba.demo.services.CommentServiceImpl;
import com.ucbcba.demo.entities.Restaurant;
import com.ucbcba.demo.services.RestaurantService;
import com.ucbcba.demo.services.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import javax.validation.Valid;
import java.util.List;

@Controller
public class CommentController {
    private CommentService commentService;
    private UserService userService;
    private RestaurantService restaurantService;

    @Autowired
    public void setCommentService(CommentService commentService) {
        this.commentService = commentService;
    }

    @Autowired
    public void setRestaurantService(RestaurantService restaurantService) {
        this.restaurantService= restaurantService;
    }
    @RequestMapping(value = "/comment", method = RequestMethod.POST)
    String save(Model model, @Valid @ModelAttribute Comment comment, BindingResult bindingResult) {
        model.addAttribute("comment",comment);
        User userExists = null;
        User user = comment.getUser();
        List<Comment> comments=restaurantService.listAllComments(comment.getRestaurant().getId());
        for(int i=0;i<comments.size();i++){
            if(user.getUsername() == comments.get(i).getUser().getUsername()){
                System.out.println(comments.get(i).getUser().getUsername());
                userExists=user;
            }
        }
        if(userExists!= null) {
            return "redirect:/restaurant/" + comment.getRestaurant().getId();
        }
        commentService.saveComment(comment);
        return "redirect:/restaurant/" + comment.getRestaurant().getId();
    }
}
