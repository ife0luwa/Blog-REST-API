package com.example.myblogtask.repositories;

import com.example.myblogtask.models.FavPost;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface FavPostRepository extends JpaRepository<FavPost, Long> {

    List<FavPost> findAllByUser_Id(Long userId);

    void  deleteFavPostByUser_Id(Long userId);
}
