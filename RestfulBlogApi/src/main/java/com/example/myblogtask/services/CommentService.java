package com.example.myblogtask.services;

import com.example.myblogtask.models.Comment;
import com.example.myblogtask.models.Post;
import com.example.myblogtask.models.UserDetails;
import com.example.myblogtask.repositories.PostRepository;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public interface CommentService {


    boolean createComment(Long userId, Long postId, Comment comment);
    boolean editComment(UserDetails user, Long postId,Long commentId, String comment);
    Optional<Comment> getCommentById(Long id);
    List<Comment> getAllComments();
    void deleteCommentById(Long commentId);
}
