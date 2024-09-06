package com.example.apartment.model;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.CreationTimestamp;

import javax.persistence.*;
import java.time.LocalDateTime;

@Entity
@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder


@Table(name = "comments")
public class Comment {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;

     @Column(nullable = false,length = 200)
     private String content;

    @ManyToOne(fetch = FetchType.LAZY)// Many = board . one = user
    @JoinColumn(name = "boardId")
    private Board board;


    @ManyToOne(fetch = FetchType.LAZY)// Many = board . one = user
    @JoinColumn(name = "userId")
    private User user;


    @CreationTimestamp
    private LocalDateTime createdAt;


}