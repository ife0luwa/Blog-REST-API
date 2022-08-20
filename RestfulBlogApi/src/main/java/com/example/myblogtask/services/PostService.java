package com.example.myblogtask.services;

import com.example.myblogtask.dto.PostDTO;
import com.example.myblogtask.models.Post;
import com.example.myblogtask.models.UserDetails;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public interface PostService {

    boolean createPost(Long userId, Post post);
    boolean editPost(UserDetails user, Long postId,String title,String body);
    Optional <Post> getPostById(Long postId);
    List<Post> getAllPosts();
    void deletePostById(Long id);
}
