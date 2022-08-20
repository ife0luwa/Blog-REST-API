package com.example.myblogtask.models;


import com.fasterxml.jackson.annotation.JsonBackReference;
import lombok.*;

import javax.persistence.*;


@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Entity
public class Comment extends TimeStamps {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long commentId;

    @Column(nullable = false, columnDefinition = "TEXT")
    private String comment;

    @Transient
    private int likesCount; //Holds the number of counts for a comment

    @ManyToOne(fetch = FetchType.EAGER)
    @JsonBackReference
    @JoinColumn(name = "postId", referencedColumnName = "postId")
    private Post post;

    @OneToOne
    @JoinColumn(name = "userId", referencedColumnName = "id")
    private UserDetails users;
}
