package com.example.elec5619fitnesswebapp.repository;

import com.example.elec5619fitnesswebapp.model.UserWorkout;
import org.apache.ibatis.annotations.Param;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;

import java.util.List;

public interface UserWorkoutRepository extends CrudRepository<UserWorkout, Integer> {
    UserWorkout getById(Integer id);

    @Query(nativeQuery = true,
            value = "select weight from user u join user_workout uw on u.id = uw.user_id\n" +
                    "where u.id=:user_id and uw.weight is not null\n" +
                    "order by uw.entry_time desc, uw.id desc limit 1")
    Float getCurrentWeight(@Param("user_id") Integer user_id);

    @Query(nativeQuery = true,
            value = "select SUM(steps) from user u join user_workout uw on u.id = uw.user_id\n" +
                    "where u.id=:user_id and uw.entry_time like :date%")
    Integer getStepsOnDate(@Param("user_id") Integer user_id,
                           @Param("date") String date);

    @Query(nativeQuery = true,
            value = "select SUM(calories_burnt) from user u join user_workout uw on u.id = uw.user_id\n" +
                    "where u.id=:user_id and uw.entry_time like :date%")
    Integer getCaloriesBurntOnDate(@Param("user_id") Integer user_id,
                                   @Param("date") String date);

    //get every workout_type, calories burned pair for a given user on given date
    @Query(nativeQuery = true,
            value = "select uw.calories_burnt, uw.workout_type from user u join user_workout uw on u.id = uw.user_id\n" +
                    "where u.id=:user_id and uw.entry_time like :date% and uw.workout_type IS NOT NULL")
    List<Object[]> getCaloriesAndExercises(@Param("user_id") Integer user_id,
                                           @Param("date") String date);

    //get a lists containing the date and weight
    @Query(nativeQuery = true,
            value = "SELECT uw.entry_time, uw.weight FROM user u JOIN user_workout uw ON u.id = uw.user_id " +
                    "WHERE u.id = :user_id AND uw.weight IS NOT NULL " +
                    "ORDER BY uw.entry_time ASC")
    List<Object[]> getWeightOverTime(@Param("user_id") Integer user_id);

    // find top three
    @Query(nativeQuery = true,
    value = "select user_id, sum(calories_burnt) from user_workout " +
            "group by user_id\n" +
            "order by  sum(calories_burnt) desc limit 3;")
    List<Object[]> getTopThreeCalories();


}
