package com.example.myblogtask.services;

import com.example.myblogtask.models.PostLikes;
import com.example.myblogtask.models.UserDetails;

public interface LikeService {

//    boolean likePost(UserDetails user, Long postId, String action);
    boolean likePost(Long userId, Long postId, String action);
    boolean likeComment(Long userId, Long commentId, String action);
}
