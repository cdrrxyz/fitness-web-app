package com.example.elec5619fitnesswebapp.service;

import com.example.elec5619fitnesswebapp.model.Comment;
import com.example.elec5619fitnesswebapp.model.Post;
import com.example.elec5619fitnesswebapp.model.User;
import com.example.elec5619fitnesswebapp.repository.CommentRepository;
import com.example.elec5619fitnesswebapp.repository.PostRepository;
import com.example.elec5619fitnesswebapp.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.List;
import java.util.Optional;

@Service
public class CommentService {

    @Autowired
    private CommentRepository commentRepository;

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private PostRepository postRepository;

    public List<Comment> getCommentsByPost(Integer post_id) {
        return commentRepository.getCommentByPost(post_id);
    }

    public Comment addComment(Integer uid, Integer postId, String content) {
        Comment comment = new Comment();

        Optional<User> user = userRepository.findById(uid);
        Optional<Post> post = postRepository.findById(postId);

        if (user.isPresent() && post.isPresent()) {
            comment.setUser(user.get());
            comment.setPost(post.get());
            comment.setContent(content);
            comment.setSentTime(LocalDate.now());
        }
        return commentRepository.save(comment);
    }
}
