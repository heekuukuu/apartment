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
    private Long id; //auto_increment

    @Column(nullable = false, length = 100)  // 100자까지
    private String username; // 로그인용 사용자 ID

    @Column(length = 100)
    private String password; // 로그인용 비밀번호

    @Column(nullable = false, length = 50,unique = true)
    private String email;  //로그인용 이메일


    @Enumerated(EnumType.STRING)
    private UserRole role; // Enum

    @Enumerated(EnumType.STRING)
    private LoginType loginType;


    @CreationTimestamp
    private LocalDateTime created_date;


    //@JoinColumn(name = "apartment_id")  // 외래 키로 아파트 ID를 참조
    // 여러 유저가 하나의 아파트에 속할 수 있으므로 ManyToOne 사용
    @ManyToOne(cascade = CascadeType.REMOVE)
    private Apartment apartment;


}
