package com.example.elec5619fitnesswebapp.service;

import com.example.elec5619fitnesswebapp.model.Equipment;
import com.example.elec5619fitnesswebapp.repository.EquipmentRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.*;

@Service
public class EquipmentService {

    @Autowired
    private EquipmentRepository equipmentRepository;

    public List<Equipment> getAllEquipments() {
        return equipmentRepository.getAllEquipments();
    }

    public Equipment getEquipmentByName(String name) {
        return equipmentRepository.getEquipmentByName(name);
    }

}
