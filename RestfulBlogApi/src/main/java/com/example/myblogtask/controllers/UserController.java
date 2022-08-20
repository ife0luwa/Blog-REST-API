package com.example.myblogtask.controllers;

import com.example.myblogtask.models.FavPost;
import com.example.myblogtask.models.Post;
import com.example.myblogtask.models.UserDetails;
import com.example.myblogtask.services.serviceImpl.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpSession;
import java.util.List;

@RestController
@RequestMapping("/blog")
public class UserController {


    private final UserServiceImpl userService;
    private final PostServiceImpl postService;
    private final FavPostImpl favPost;

    @Autowired
    public UserController(UserServiceImpl userService, PostServiceImpl postService, FavPostImpl favPost) {
        this.userService = userService;
        this.postService = postService;
        this.favPost = favPost;
    }

    @PostMapping("/register")
    public String registerUser(@RequestBody  UserDetails user){
        boolean registerUser = userService.registerUser(user);
        if(registerUser) {
            return "User: " + user.getName() + " registered successfully";
        }
        return "User: "+user+" not saved!!!";
    }

    @GetMapping("/login")
    public String loginUser(@RequestBody UserDetails user, HttpSession session){
        UserDetails userDb = userService.loginUser(user.getEmail(),user.getPassword());
        if (userDb != null) {
            session.setAttribute("user",userDb);
            return  userDb.getName() + " logged in!!!";
        }
        return "User not in the Database!!!";
    }

    @GetMapping("/getUser")
    public List<UserDetails> getAllUsers(){
        return userService.getAllUsers();
    }

    @GetMapping("/getUser/{userId}")
    public UserDetails getUserById(@PathVariable Long userId){
        return userService.getUserById(userId);
    }


    @PostMapping("/addFavoritePost/{userId}/{postId}")
    public FavPost addFavoritePost(@PathVariable Long postId, @PathVariable Long userId){
        UserDetails user = userService.getUserById(userId);
        Post post =  postService.getPostById(postId).get();

       if(user != null && post != null){
//           userService.addFavouritePost(userId,postId);
           return  favPost.saveFavourite(userId,postId);
       }
        return new FavPost();
    }

    @DeleteMapping("/delete/{userId}")
    public String deleteUserById(@PathVariable Long userId,HttpSession session){
        UserDetails user = (UserDetails) session.getAttribute("user");
        userService.deactivateUser(userId);
        return (userId!=null)?"user with id "+userId+" deleted!":"user doesnt exist";
    }

}
