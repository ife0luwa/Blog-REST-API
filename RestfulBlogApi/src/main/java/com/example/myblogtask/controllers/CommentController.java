package com.example.myblogtask.controllers;

import com.example.myblogtask.models.Comment;
import com.example.myblogtask.models.UserDetails;
import com.example.myblogtask.services.serviceImpl.CommentServiceImpl;
import com.example.myblogtask.services.serviceImpl.PostServiceImpl;
import com.example.myblogtask.services.serviceImpl.UserServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/blogComment")
public class CommentController {

    private CommentServiceImpl commentService;
    private UserServiceImpl userService;
    private PostServiceImpl postService;

    @Autowired
    public CommentController(CommentServiceImpl commentService,
              UserServiceImpl userService, PostServiceImpl postService) {
        this.commentService = commentService;
        this.userService = userService;
        this.postService = postService;
    }

    @PostMapping(path = "/createComment/{postId}/{userId}")
    public String createComment(@RequestBody Comment comment, @PathVariable Long postId,
                                @PathVariable Long userId){
        UserDetails user = userService.getUserById(userId);
        if (user != null && commentService.createComment(userId,postId,comment)) {
//            commentService.createComment(userId,postId,comment);
            return "Comment "+ comment.getComment()+" saved!!!";
        }
       return "Comment not saved";
    }

    @PutMapping("editComment/{commentId}/post/{postId}/user/{userId}")
    public String editComment(@RequestBody Comment comment, @PathVariable Long commentId,
                @PathVariable Long postId,@PathVariable Long userId ){
        UserDetails user = userService.getUserById(userId);
        return commentService.editComment(user,postId,commentId,comment.getComment())?
                "Comment with id "+commentId+ " updated":"failed to update comment";
    }

    @GetMapping(path = "/getComment/{commentId}")
    public Optional<Comment> getCommentById(@PathVariable Long commentId){
        return commentService.getCommentById(commentId);
    }

    @GetMapping("/getComment")
    public List<Comment> getComments(){
        return commentService.getAllComments();
    }

    @DeleteMapping("/delete/{commentId}")
    public String deleteComment(@PathVariable Long commentId){
        commentService.deleteCommentById(commentId);
        return "Comment with id "+commentId+ " deleted!!";
    }
}
