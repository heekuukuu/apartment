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
@Table(name = "boards")
public class Board {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;

    @Column(nullable = false, length = 100)
    private String title;

    @Lob
    private String content;


    @Column(nullable = false, length = 20)
    private  String category;

    @ManyToOne(fetch = FetchType.LAZY)// Many = board . one = user
    @JoinColumn(name = "userId")
    private User user;

    @CreationTimestamp
    private LocalDateTime created_date;
}