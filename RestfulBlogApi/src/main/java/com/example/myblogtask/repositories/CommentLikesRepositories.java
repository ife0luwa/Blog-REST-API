package com.example.myblogtask.repositories;

import com.example.myblogtask.models.Comment;
import com.example.myblogtask.models.CommentLikes;
import com.example.myblogtask.models.PostLikes;
import com.example.myblogtask.models.UserDetails;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Repository
public interface CommentLikesRepositories extends JpaRepository<CommentLikes, Long> {

    @Transactional
    void deleteCommentLikesByCommentAndUser(Comment comment, UserDetails user);
    List<CommentLikes> findAllByUser_IdAndComment_CommentId(Long userId, Long commentId);
    List<CommentLikes> findAllByComment_CommentId(Long commentId);


}
