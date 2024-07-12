package com.example.elec5619fitnesswebapp.controller;

import com.example.elec5619fitnesswebapp.service.ModelAndViewService;
import com.example.elec5619fitnesswebapp.service.TrainingService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

import java.util.List;
import java.util.Map;

@Controller
public class TrainingController {
    @Autowired
    private TrainingService trainingService;

    @Autowired
    private ModelAndViewService modelAndViewService;

    @RequestMapping(value = {"/training"}, method = RequestMethod.GET)
    public ModelAndView training(
            @RequestParam(name = "search", required = false) String searchTerm,
            @RequestParam(name = "type", required = false) String type,
            Model model) {
        boolean hasSearchTerm = searchTerm != null && !searchTerm.isEmpty();
        boolean hasType = type != null && !type.isEmpty();

        List<Map> guides;

        if (hasSearchTerm || hasType) {
            guides = trainingService.getFilteredTrainingGuide(searchTerm, type);
        } else {
            guides = trainingService.getAllTrainingGuide();
        }
        ModelAndView modelAndView = modelAndViewService.getModelAndView("training");
        modelAndView.addObject("guides", guides);
        return modelAndView;
    }
}
