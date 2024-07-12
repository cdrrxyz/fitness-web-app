package com.example.elec5619fitnesswebapp.repository;

import com.example.elec5619fitnesswebapp.model.Training;
import org.apache.ibatis.annotations.Param;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;

import java.util.List;

public interface TrainingRepository extends CrudRepository<Training, Integer> {
    @Query(nativeQuery = true,
            value = "SELECT * FROM training")
    List<Training> getAllTrainingGuide();


    @Query(nativeQuery = true,
            value = "SELECT * FROM training WHERE 1=1 " +
            "AND (:searchTerm IS NULL OR title LIKE %:searchTerm%) " +
            "AND (:type IS NULL OR type = :type OR :type = 'all') ")
    List<Training> getFilteredTrainingGuide(
            @Param("searchTerm") String searchTerm,
            @Param("type") String type);

}
