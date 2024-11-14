package com.example.apartment.model;

import java.time.LocalDateTime;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.CreationTimestamp;

@Entity
@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder


@Table(name = "comments")
public class Comment {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "comment_id")
    private long id;

     @Column(nullable = false,length = 200)
     private String content;

    @ManyToOne(fetch = FetchType.LAZY)// Many = board . one = user
    @JoinColumn(name = "board_id")
    private Board board;


    @ManyToOne(fetch = FetchType.LAZY)// Many = board . one = user
    @JoinColumn(name = "user_id")
    private User user;


    @CreationTimestamp
    private LocalDateTime createdAt;



}