package com.example.myblogtask.services.serviceImpl;

import com.example.myblogtask.models.*;
import com.example.myblogtask.repositories.*;
import com.example.myblogtask.services.LikeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class LikeServiceImpl implements LikeService {

    private final  PostRepository postRepository;
    private final CommentRepository commentRepository;
    private final UserRepository userRepository;
    private final PostLikesRepository postLikesRepo;
    private final CommentLikesRepositories commentLikesRepo;

    @Autowired
    public LikeServiceImpl(PostRepository postRepository, CommentRepository commentRepository,
           UserRepository userRepository, PostLikesRepository postLikesRepo,
                           CommentLikesRepositories commentLikesRepo) {
        this.postRepository = postRepository;
        this.commentRepository = commentRepository;
        this.userRepository = userRepository;
        this.postLikesRepo = postLikesRepo;
        this.commentLikesRepo = commentLikesRepo;
    }

    @Override
    public boolean likePost(Long userId, Long postId, String action) {
        Post post = postRepository.findById(postId).get();
        System.out.println("likes count for post"+post.getPostId()+" initially"+post.getLikesCount());
        UserDetails user1 = userRepository.findById(userId).get();
        PostLikes newLike = new PostLikes();
        newLike.setPost(post);
        newLike.setUser(user1);
        System.out.println(newLike);
        List<PostLikes> postLikes = postLikesRepo.findAllByUser_IdAndPost_PostId(userId, postId);
        if(action.equals("1") && postLikes.size() ==0) {
            postLikesRepo.save(newLike);
            postLikes.add(newLike);
            List<PostLikes> likes = postLikesRepo.findAllByPost_PostId(postId);
           post.setLikesCount(likes.size());
            System.out.println("likes count for post"+post.getPostId()+" after"+post.getLikesCount());
            postRepository.save(post);
            System.out.println("User liked post");
            return true;
        }
        else{
            postLikesRepo.deletePostLikesByPostAndUser(post,user1);
            List<PostLikes> likes = postLikesRepo.findAllByPost_PostId(postId);
            post.setLikesCount(likes.size());
            postRepository.save(post);

            System.out.println("User unliked post");
                return false;
        }

    }

    @Override
    public boolean likeComment(Long userId, Long commentId, String action) {

        Comment comment = commentRepository.findById(commentId).get();
        UserDetails user1 = userRepository.findById(userId).get();
        CommentLikes commentLike = new CommentLikes();
        commentLike.setComment(comment);
        commentLike.setUser(user1);
        List<CommentLikes> commentLikes = commentLikesRepo.findAllByUser_IdAndComment_CommentId(userId,commentId);
        if(action.equals("1") && commentLikes.size() == 0){
            commentLikesRepo.save(commentLike);
            commentLikes.add(commentLike);
            List<CommentLikes> likes = commentLikesRepo.findAllByComment_CommentId(commentId);
            comment.setLikesCount(likes.size());
            commentRepository.save(comment);
            System.out.println("User liked comment");
            return true;
        }
        else {
            commentLikesRepo.deleteCommentLikesByCommentAndUser(comment,user1);
            List<CommentLikes> likes = commentLikesRepo.findAllByComment_CommentId(commentId);
            comment.setLikesCount(likes.size());
            commentRepository.save(comment);
            System.out.println("User unliked comment");
            return false;

        }



    }


}
