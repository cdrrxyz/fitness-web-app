package com.example.elec5619fitnesswebapp.repository;

import com.example.elec5619fitnesswebapp.model.Equipment;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface EquipmentRepository extends CrudRepository<Equipment, Integer> {

    @Query(nativeQuery = true,
            value = "SELECT * FROM equipment")
    List<Equipment> getAllEquipments();

    @Query(nativeQuery = true,
            value = "SELECT * FROM equipment " +
                    "WHERE name = :name")
    Equipment getEquipmentByName(@Param("name")String name);

}
