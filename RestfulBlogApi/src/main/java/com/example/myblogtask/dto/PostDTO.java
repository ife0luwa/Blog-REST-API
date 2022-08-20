package com.example.myblogtask.dto;

import com.example.myblogtask.models.UserDetails;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class PostDTO {
    private String postId;
    private String title;
    private String body;
    private String name;
    private String email;
    private int likesCount;
    private int commentsCount;
    private boolean likedPost;
    private Long userId;


}
