package com.example.myblogtask.controllers;

import com.example.myblogtask.dto.LikesDto;
import com.example.myblogtask.models.Comment;
import com.example.myblogtask.models.Post;
import com.example.myblogtask.repositories.CommentLikesRepositories;
import com.example.myblogtask.repositories.PostLikesRepository;
import com.example.myblogtask.services.serviceImpl.CommentServiceImpl;
import com.example.myblogtask.services.serviceImpl.LikeServiceImpl;
import com.example.myblogtask.services.serviceImpl.PostServiceImpl;
import com.example.myblogtask.services.serviceImpl.UserServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/like")
public class LikesController {
    private final LikeServiceImpl likeService;
    private final UserServiceImpl userService;
    private final PostServiceImpl postService;
    private final CommentServiceImpl commentService;
    private final PostLikesRepository postRepo;
   private final CommentLikesRepositories commentRepo;

    @Autowired
    public LikesController(UserServiceImpl userService, LikeServiceImpl likeService, PostServiceImpl postService,
         CommentServiceImpl commentService, PostLikesRepository postRepo, CommentLikesRepositories commentRepo) {
        this.userService = userService;
        this.likeService = likeService;
        this.postService = postService;
        this.commentService = commentService;
        this.postRepo = postRepo;
        this.commentRepo = commentRepo;

    }


        @PostMapping (path = "/post/{postId}/{userId}")
        public Post likeAPost(@RequestBody LikesDto postDto, @PathVariable Long postId,
                              @PathVariable Long userId){
            if(likeService.likePost(userId, postId,postDto.getAction()) ){
                return postService.getPostById(postId).get();
            }
            return postService.getPostById(postId).get();


        }

    @PostMapping (path = "/comment/{commentId}/{userId}")
    public Comment likeAComment(@RequestBody LikesDto postDto, @PathVariable Long commentId,
                                @PathVariable Long userId){
        if(likeService.likeComment(userId, commentId,postDto.getAction()) ){
            return commentService.getCommentById(commentId).get();
//                    "Comment with id: "+commentId+ " liked!, total likes for comment: "
//                    +commentRepo.findAllByComment_CommentId(commentId).size();
//                    ", total likes for comment"+postRepo.count();
        }
        return commentService.getCommentById(commentId).get();
//                "Comment with id: "+commentId+" unliked!, total likes for comment: "
//                +commentRepo.findAllByComment_CommentId(commentId).size();

    }

}
