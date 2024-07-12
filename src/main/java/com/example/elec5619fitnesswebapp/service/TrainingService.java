package com.example.elec5619fitnesswebapp.service;

import com.example.elec5619fitnesswebapp.model.Training;
import com.example.elec5619fitnesswebapp.repository.TrainingRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Service
public class TrainingService {

    @Autowired
    private TrainingRepository trainingRepository;

    public List<Map> getGuideModels (List<Training> guides) {
        List guideList = new ArrayList();

        for (Training g: guides) {
            Map<String, Object> guideModel = new HashMap<>();
            guideModel.put("title", g.getTitle());
            guideModel.put("videoId", g.getVideoId());
            guideModel.put("description", g.getDescription());
            guideList.add(guideModel);
        }

        return guideList;
    }

    public List<Map> getAllTrainingGuide() {
        List<Training> guides = trainingRepository.getAllTrainingGuide();
        return getGuideModels(guides);
    }

    public List<Map> getFilteredTrainingGuide(String searchTerm, String type) {
        List<Training> guides = trainingRepository.getFilteredTrainingGuide(searchTerm, type);
        return getGuideModels(guides);
    }

}
