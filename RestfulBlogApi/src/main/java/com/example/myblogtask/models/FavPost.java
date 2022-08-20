package com.example.myblogtask.models;

import lombok.Data;

import javax.persistence.*;

@Data
@Entity
public class FavPost {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne
    @JoinColumn(name = "postId",referencedColumnName = "postId")
    private Post post;

    @ManyToOne
    @JoinColumn(name = "userid",referencedColumnName = "id")
    private UserDetails user;
}
