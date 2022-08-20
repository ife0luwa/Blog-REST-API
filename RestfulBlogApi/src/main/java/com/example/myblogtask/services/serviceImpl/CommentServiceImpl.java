package com.example.myblogtask.services.serviceImpl;

import com.example.myblogtask.models.Comment;
import com.example.myblogtask.models.Post;
import com.example.myblogtask.models.UserDetails;
import com.example.myblogtask.repositories.CommentRepository;
import com.example.myblogtask.repositories.PostRepository;
import com.example.myblogtask.repositories.UserRepository;
import com.example.myblogtask.services.CommentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class CommentServiceImpl implements CommentService {

    private PostRepository postRepository;
    private CommentRepository commentRepository;
    private UserRepository userRepository;

    @Autowired
    public CommentServiceImpl(PostRepository postRepository, CommentRepository commentRepository,
                              UserRepository userRepository) {
        this.postRepository = postRepository;
        this.commentRepository = commentRepository;
        this.userRepository = userRepository;
    }



    @Override
    public boolean createComment(Long userId, Long postId, Comment comment) {

        var post = postRepository.findById(postId);//.orElseThrow(NullPointerException::new);
        var user = userRepository.findById(userId);//.orElseThrow(NullPointerException::new);
        Comment comment1 = new Comment();
        comment1.setComment(comment.getComment());
        if(post.isPresent() && user.isPresent()){
            comment.setPost(post.get());
            comment.setUsers(user.get());
            post.get().getComments().add(comment);
//            postRepository.save(post.get());
            commentRepository.save(comment);
            return true;
        }
        return false;
    }

    @Override
    public boolean editComment(UserDetails user, Long postId,Long commentId, String comment) {
        Comment comment1 = commentRepository.getById(commentId);
        Post post = postRepository.getById(postId);
        if(comment1.getPost() == post) {
            comment1.setComment(comment);
            commentRepository.save(comment1);
            return true;
        }
        return false;
    }

    @Override
    public Optional<Comment> getCommentById(Long commentId) {
        return commentRepository.findById(commentId);
    }

    @Override
    public List<Comment> getAllComments() {
        return commentRepository.findAll();
    }

    @Override
    public void deleteCommentById(Long commentId) {
        commentRepository.deleteById(commentId);
    }
}
