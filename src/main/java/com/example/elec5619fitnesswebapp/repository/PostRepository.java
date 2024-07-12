package com.example.elec5619fitnesswebapp.repository;

import com.example.elec5619fitnesswebapp.model.Post;
import org.apache.ibatis.annotations.Param;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;

import java.util.*;

public interface PostRepository extends CrudRepository<Post, Integer> {

    @Query(nativeQuery = true,
            value = "SELECT * FROM post ")
    List<Post> getAllPosts();

    @Query(nativeQuery = true, value = "SELECT * FROM post WHERE 1=1 " +
            "AND (:searchTerm IS NULL OR title LIKE %:searchTerm%) " +
            "AND (:category IS NULL OR category = :category OR :category = 'all') " +
            "ORDER BY " +
            "CASE " +
            "WHEN :sorting = 'recentlyCreated' THEN sent_at END ASC, " + // Descending order for recentlyCreated
            "CASE " +
            "WHEN :sorting = 'mostLiked' THEN likes END DESC") // Ascending order for mostLiked
    List<Post> getFilteredPosts(
            @Param("searchTerm") String searchTerm,
            @Param("category") String category,
            @Param("sorting") String sorting);

    Post save(Post post);
}
