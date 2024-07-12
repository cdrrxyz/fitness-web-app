package com.example.elec5619fitnesswebapp.controller;

import com.example.elec5619fitnesswebapp.service.ModelAndViewService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;

@Controller
public class VideoPlayerController {

    @Autowired
    private ModelAndViewService modelAndViewService;
    @RequestMapping(value = {"/videoPlayer"}, method = RequestMethod.GET)
    public ModelAndView videoPlayer() {
        return modelAndViewService.getModelAndView("videoPlayer");
    }
}
