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
import java.util.*;

@Service
public class PostService {

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private CommentRepository commentRepository;

    @Autowired
    private PostRepository postRepository;

    public List<Map> getPostModels (List<Post> posts) {
        List postList = new ArrayList();
        for (Post p: posts) {
            Map<String, Object> postModel = new HashMap<>();
            postModel.put("id", p.getId());
            postModel.put("title", p.getTitle());

            User user = p.getUser();
            postModel.put("name", user.getFirstName() + " " + user.getLastName());
            postModel.put("content", p.getContent());
            postModel.put("likes", p.getLikes());
            List comments = new ArrayList();

            for (Comment c: commentRepository.getCommentByPost(p.getId())) {
                Map<String, Object> commentModel = new HashMap<>();
                User author = c.getUser();
                commentModel.put("name", author.getFirstName() + " " + author.getLastName());
                commentModel.put("comment", c.getContent());
                comments.add(commentModel);
            }

            postModel.put("comments", comments);
            postList.add(postModel);
        }
        return postList;
    }

    public List<Map> getAllPosts() {

        List<Post> posts = postRepository.getAllPosts();
        return getPostModels(posts);
    }

    public List<Map> getFilteredPosts(String searchTerm, String category, String sorting) {

        List<Post> posts = postRepository.getFilteredPosts(searchTerm, category, sorting);
        return getPostModels(posts);
    }

    public Post addPost(Integer uid, String title, String content, String category) {
        Post post = new Post();
        Optional<User> user = userRepository.findById(uid);
        if (user.isPresent()) {
            post.setUser(user.get());
            post.setTitle(title);
            post.setContent(content);
            post.setCategory(category);
            post.setSentTime(LocalDate.now());
            post.setLikes(0);
        }

        return postRepository.save(post);
    }

}
