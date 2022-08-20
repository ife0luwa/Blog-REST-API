package com.example.myblogtask.services;

import com.example.myblogtask.models.Post;
import com.example.myblogtask.models.UserDetails;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public interface UserService {
    boolean registerUser(UserDetails user);
    UserDetails loginUser(String email, String password);
    void deactivateUser(Long id);
    UserDetails getUserById(Long id);
    List<UserDetails> getAllUsers();
//    Post addFavouritePost(Long userId,Long postId);


}
