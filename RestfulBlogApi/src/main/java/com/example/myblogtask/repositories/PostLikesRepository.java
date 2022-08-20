package com.example.myblogtask.repositories;

import com.example.myblogtask.models.Post;
import com.example.myblogtask.models.PostLikes;
import com.example.myblogtask.models.UserDetails;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

public interface PostLikesRepository extends JpaRepository<PostLikes, Long> {
    @Transactional
    void deletePostLikesByPostAndUser(Post post, UserDetails user);
    List<PostLikes> findAllByUser_IdAndPost_PostId(Long userId,Long postId);
    List<PostLikes> findAllByPost_PostId(Long postId);

}
