package com.example.apartment.model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.OneToOne;
import javax.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;


@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Table(name = "apartments")
public class Apartment {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "apartment_id")
    private Long id;

    @Column(name = "name", nullable = false)
    private String name; // 아파트 이름

    @Column(nullable = false)
    private String address;

    @Column(nullable = false, unique = true)
    private String email;

    @JoinColumn(name = "user_id")
    @OneToOne(mappedBy = "apartment")  // 'apartment' 필드를 관리하는 주 테이블은 'User'
    private User user;  // 하나의 Apartment는 하나의 User에 속함


}





