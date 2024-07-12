package com.example.elec5619fitnesswebapp.controller;

import com.example.elec5619fitnesswebapp.service.CommentService;
import com.example.elec5619fitnesswebapp.service.CurrentUserService;
import com.example.elec5619fitnesswebapp.service.ModelAndViewService;
import com.example.elec5619fitnesswebapp.service.PostService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;

import java.util.List;
import java.util.Map;

@Controller
public class PostController {

    @Autowired
    CurrentUserService currentUserService;

    @Autowired
    private PostService postService;

    @Autowired
    private CommentService commentService;

    @Autowired
    private ModelAndViewService modelAndViewService;

    @RequestMapping(value = "/discussion", method = RequestMethod.GET)
    public ModelAndView getFilteredPosts(
            @RequestParam(name = "search", required = false) String searchTerm,
            @RequestParam(name = "category", required = false) String category,
            @RequestParam(name = "sorting", required = false) String sorting) {

        boolean hasSearchTerm = searchTerm != null && !searchTerm.isEmpty();
        boolean hasCategory = category != null && !category.isEmpty();
        boolean hasSorting = sorting != null && !sorting.isEmpty();

        List<Map> posts;

        if (hasSearchTerm || hasCategory || hasSorting) {
            posts = postService.getFilteredPosts(searchTerm, category, sorting);
        } else {
            posts = postService.getAllPosts();
        }
        ModelAndView modelAndView = modelAndViewService.getModelAndView("discussion");
        modelAndView.addObject("posts", posts);
        return modelAndView;
    }

    @RequestMapping(value = "/addComment", method = RequestMethod.POST)
    public ModelAndView addComment(@RequestParam("post_id") Integer postId,
                             @RequestParam("comment") String commentContent) {

        if (currentUserService.getLoggedIn()) {
            commentService.addComment(currentUserService.getCurrentUid(), postId, commentContent);
            return modelAndViewService.getModelAndView("redirect:/discussion");
        }
        return modelAndViewService.getModelAndView("login");
    }


}

