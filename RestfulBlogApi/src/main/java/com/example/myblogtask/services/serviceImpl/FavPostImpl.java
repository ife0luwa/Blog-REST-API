package com.example.myblogtask.services.serviceImpl;

import com.example.myblogtask.models.FavPost;
import com.example.myblogtask.repositories.FavPostRepository;
import com.example.myblogtask.services.FavService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class FavPostImpl  implements FavService {
    private final PostServiceImpl postService;
    private  final UserServiceImpl userService;
    private final FavPostRepository favPostRepository;

    @Autowired
    public FavPostImpl(PostServiceImpl postService, UserServiceImpl userService, FavPostRepository favPostRepository) {
        this.postService = postService;
        this.userService = userService;
        this.favPostRepository = favPostRepository;
    }

    @Override
    public FavPost saveFavourite(Long userId, Long postId) {
        FavPost favPost = new FavPost();
        var post = postService.getPostById(postId);
        var user= userService.getUserById(userId);
        favPost.setPost(post.get());
        favPost.setUser(user);
        favPostRepository.save(favPost);
        return favPost;
    }
}
