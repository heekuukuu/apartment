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
    private String email;  //로그인용 이메일


    @Enumerated(EnumType.STRING)
    private UserRole role; // Enum

    @Enumerated(EnumType.STRING)
    private LoginType loginType;


    @CreationTimestamp
    private LocalDateTime created_date;


//    @ManyToOne(fetch = FetchType.LAZY)
//    @JoinColumn(name = "apartment_id")
//    private Apartment apartment; // 소속 아파트 정보

}