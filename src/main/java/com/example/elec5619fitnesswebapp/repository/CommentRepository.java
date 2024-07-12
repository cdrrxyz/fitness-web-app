package com.example.elec5619fitnesswebapp.repository;

import com.example.elec5619fitnesswebapp.model.Comment;
import com.example.elec5619fitnesswebapp.model.Post;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;
import java.util.List;

public interface CommentRepository extends CrudRepository<Comment, Integer> {
    Post getPostById(Integer id);

    @Query(nativeQuery = true,
            value = "SELECT * FROM comment " +
                    "WHERE post_id = :post_id")
    List<Comment> getCommentByPost(@Param("post_id")Integer post_id);

    Comment save(Comment comment);


}
