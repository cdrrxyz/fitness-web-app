package com.example.elec5619fitnesswebapp.controller;

import com.example.elec5619fitnesswebapp.service.CurrentUserService;
import com.example.elec5619fitnesswebapp.service.PostService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

@Controller
public class AddPostController {

    @Autowired
    private CurrentUserService currentUserService;

    @Autowired
    private PostService postService;

    @RequestMapping(value = {"/post"}, method = RequestMethod.GET)
    public ModelAndView addPost() {

        ModelAndView modelAndView = new ModelAndView("addPost");
        modelAndView.addObject("loggedIn", String.valueOf(currentUserService.getLoggedIn()));
        return modelAndView;
    }

    @RequestMapping(value = "/addPost", method = RequestMethod.POST)
    public ModelAndView addPost(@RequestParam("title") String title,
                          @RequestParam("content") String content,
                          @RequestParam("category") String category) {
        if (currentUserService.getLoggedIn()) {
            postService.addPost(currentUserService.getCurrentUid(), title, content, category);
            ModelAndView modelAndView = new ModelAndView("redirect:/discussion");
            modelAndView.addObject("loggedIn", String.valueOf(currentUserService.getLoggedIn()));
            return modelAndView;
        }

        ModelAndView modelAndView = new ModelAndView("login");
        modelAndView.addObject("loggedIn", String.valueOf(currentUserService.getLoggedIn()));
        return modelAndView;
    }
}
