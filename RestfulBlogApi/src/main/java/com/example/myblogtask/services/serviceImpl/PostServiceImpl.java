package com.example.myblogtask.services.serviceImpl;

import com.example.myblogtask.dto.PostDTO;
import com.example.myblogtask.models.Post;
import com.example.myblogtask.models.UserDetails;
import com.example.myblogtask.repositories.PostRepository;
import com.example.myblogtask.repositories.UserRepository;
import com.example.myblogtask.services.PostService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.Instant;
import java.util.Date;
import java.util.List;
import java.util.Optional;

@Service
public class PostServiceImpl implements PostService {

    private UserRepository userRepository;
    private PostRepository postRepository;

    @Autowired
    public PostServiceImpl(UserRepository userRepository, PostRepository postRepository) {
        this.userRepository = userRepository;
        this.postRepository = postRepository;
    }

    @Override
    public boolean createPost(Long userId, Post post) {
        UserDetails userData = userRepository.findById(userId).get();
        Post post1 = new Post();
        post1.setTitle(post.getTitle());
        post1.setBody(post.getBody());
        if(userData != null){
            post1.setUser(userData);
            post.setUser(userData);
            postRepository.save(post1);
            return true;
        }
        return false;
    }

    @Override
    public boolean editPost(UserDetails user, Long postId, String title, String body) {
        Post post = postRepository.getById(postId);
        if(post != null){
        post.setTitle(title);
        post.setBody(body);
        postRepository.save(post);
        return true;
        }
        return false;
    }

    @Override
    public Optional <Post> getPostById(Long postId) {
        System.out.println("From service"+postRepository.findById(postId));
        return postRepository.findById(postId);
    }

    @Override
    public List<Post> getAllPosts() {
       return postRepository.findAll();
    }

    @Override
    public void deletePostById(Long id) {
        postRepository.deleteById(id);
    }
}
