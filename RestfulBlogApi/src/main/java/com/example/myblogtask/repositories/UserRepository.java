package com.example.myblogtask.repositories;

import com.example.myblogtask.models.Post;
import com.example.myblogtask.models.UserDetails;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface UserRepository extends JpaRepository<UserDetails, Long> {
    UserDetails findUserDetailsByEmail(String email);
    UserDetails findByEmail(String email);
    UserDetails findByEmailAndPassword(String email, String password);
    void deleteById(Long id);
    Optional<UserDetails> findById(Long postId);



}
