package com.example.apartment.model;


import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.security.Timestamp;

@Entity
@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
@Table(name = "users")

public class User {
    @Id //Primary key
    @GeneratedValue(strategy = GenerationType.IDENTITY)

    private long id; //auto_increment

    @Column(nullable = false, length = 100, unique = true)  // 100자까지,중복불가
    private String username; // 로그인용 사용자 ID

    @Column(length = 100)
    private String password; // 로그인용 비밀번호

    @Column(nullable = false, length = 50)
    private String email;


    @Enumerated(EnumType.STRING)
    private UserRole role; // Enum

    @Enumerated(EnumType.STRING)
    private LoginType loginType;


    private Timestamp created_date;

}