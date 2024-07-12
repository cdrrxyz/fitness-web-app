package com.example.elec5619fitnesswebapp.controller;

import com.example.elec5619fitnesswebapp.service.EquipmentService;
import com.example.elec5619fitnesswebapp.service.ModelAndViewService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

@Controller
public class EquipmentDetailsController {

    @Autowired
    private EquipmentService equipmentService;

    @Autowired
    private ModelAndViewService modelAndViewService;

    @RequestMapping(value = {"/equipmentDetails"}, method = RequestMethod.GET)
    public ModelAndView equipmentDetails(
            @RequestParam(name = "name", required = false) String name,
            Model model
    ) {
        ModelAndView modelAndView = modelAndViewService.getModelAndView("equipmentDetails");
        modelAndView.addObject("equipment", equipmentService.getEquipmentByName(name));
        return modelAndView;
    }
}
