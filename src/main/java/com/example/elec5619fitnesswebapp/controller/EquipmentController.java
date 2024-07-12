package com.example.elec5619fitnesswebapp.controller;


import com.example.elec5619fitnesswebapp.model.Equipment;
import com.example.elec5619fitnesswebapp.service.EquipmentService;
import com.example.elec5619fitnesswebapp.service.ModelAndViewService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;

import java.util.List;

@Controller
public class EquipmentController {

    @Autowired
    private EquipmentService equipmentService;

    @Autowired
    private ModelAndViewService modelAndViewService;
    @RequestMapping(value = {"/equipment"}, method = RequestMethod.GET)
    public ModelAndView equipment(Model model) {
        List<Equipment> equipments = equipmentService.getAllEquipments();

        ModelAndView modelAndView = modelAndViewService.getModelAndView("equipment");
        modelAndView.addObject("equipments", equipments);
        return modelAndView;
    }
}
