package com.example.elec5619fitnesswebapp.repository;

import com.example.elec5619fitnesswebapp.model.Calendar;
import org.apache.ibatis.annotations.Param;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;

import java.util.List;

public interface CalendarRepository extends CrudRepository<Calendar, Integer> {

    @Query(nativeQuery = true,
            value = "select c.duration, c.workout_type from user u join calendar c on u.id = c.user_id\n" +
                    "where u.id=:user_id and c.entry_time like :date% and c.workout_type IS NOT NULL")
    List<Object[]> getWorkoutPlans(@Param("user_id") Integer user_id,
                                           @Param("date") String date);
}
