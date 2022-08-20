package com.example.myblogtask.dto;

import com.example.myblogtask.models.Post;
import com.example.myblogtask.models.UserDetails;
import lombok.Data;

@Data
public class LikesDto {
//    private Long userId;
    private UserDetails user;
    private String action;
}
