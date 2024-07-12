package com.example.elec5619fitnesswebapp.controller;

import com.example.elec5619fitnesswebapp.model.Goals;
import com.example.elec5619fitnesswebapp.service.GoalsService;
import com.example.elec5619fitnesswebapp.service.ModelAndViewService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;

import java.util.List;

@RestController
@RequestMapping("/goals")
public class GoalsController {

    @Autowired
    private GoalsService goalsService;

    @Autowired
    private ModelAndViewService modelAndViewService;
    @CrossOrigin
    @GetMapping
    public ModelAndView goals() {
        return modelAndViewService.getModelAndView("goals");
    }
    @CrossOrigin
    @GetMapping("/all")
    public List<Goals> getAllGoals() {
        return goalsService.findall();
    }
    @CrossOrigin
    @GetMapping("/{id}")
    public Goals getGoalsById(@PathVariable Integer id) {
        return goalsService.findById(id);
    }
    @CrossOrigin
    @PostMapping("/add")
    public Goals addGoals(@RequestBody Goals goals) {
        return goalsService.save(goals);
    }
    @CrossOrigin
    @PostMapping("/edit")
    public Goals editGoals(@RequestBody Goals goals) {
        return goalsService.edit(goals);
    }
    @CrossOrigin
    @GetMapping("/byCreator/{createdBy}")
    public List<Goals> getGoalsByCreator(@PathVariable Integer createdBy) {
        return goalsService.findAllByCreatedBy(createdBy);
    }


}
