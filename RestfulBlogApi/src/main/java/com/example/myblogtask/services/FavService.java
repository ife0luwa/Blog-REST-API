package com.example.myblogtask.services;

import com.example.myblogtask.models.FavPost;

public interface FavService {

    FavPost saveFavourite(Long userId, Long postId);
}
