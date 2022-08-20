package com.example.myblogtask.controllers;

import com.example.myblogtask.models.Post;
import com.example.myblogtask.models.UserDetails;
import com.example.myblogtask.services.PostService;
import com.example.myblogtask.services.UserService;
import com.example.myblogtask.services.serviceImpl.PostServiceImpl;
import com.example.myblogtask.services.serviceImpl.UserServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/blogpost")
public class PostController {

    private PostServiceImpl postService;
    private UserServiceImpl userService;

    @Autowired
    public PostController(PostServiceImpl postService, UserServiceImpl userService1) {
        this.postService = postService;
        this.userService = userService1;
    }

    @PostMapping("/createPost/{userId}")
    public String createPost(@RequestBody Post post, @PathVariable Long userId){
        UserDetails user = userService.getUserById(userId);
        if(user != null) {
            postService.createPost(userId, post);
            return "Post " + post + "created";
        }
        return null;
    }

    @PutMapping("editPost/{postId}/user/{userId}")
    public String editPost(@RequestBody Post post,@PathVariable("userId")Long userId,
                           @PathVariable("postId")Long postId){
        UserDetails user = userService.getUserById(userId);
      if(postService.editPost(user,postId,post.getTitle(),post.getBody())){
          return "post edited";
      }
      return "post not edited";
    }

    @GetMapping(path = "getPost/{postId}")
    public Optional<Post> getPostById(@PathVariable("postId")Long postId){
        return postService.getPostById(postId);
    }


    @GetMapping("/getPost")
    public List<Post> getAllPosts(){
        return postService.getAllPosts();
    }

    @DeleteMapping(path = "/delete/{postId}")
        public String deletePostById(@PathVariable("postId") Long id){
        postService.deletePostById(id);
        return "Post with id "+id+ " has been deleted";
        }

}
