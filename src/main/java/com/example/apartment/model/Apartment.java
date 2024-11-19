//package com.example.apartment.model;
//
//import lombok.AllArgsConstructor;
//import lombok.Builder;
//import lombok.Data;
//import lombok.NoArgsConstructor;
//import org.hibernate.annotations.CreationTimestamp;
//import org.hibernate.annotations.UpdateTimestamp;
//
//import javax.persistence.*;
//import java.time.LocalDateTime;
//
//@Entity
//@Data
//@NoArgsConstructor
//@AllArgsConstructor
//@Builder
//@Table(name = "apartment")
//public class Apartment {
//
//    @Id
//    @GeneratedValue(strategy = GenerationType.IDENTITY)
//    private Long id_apartment;  // 아파트 고유 ID
//
//    @Column(nullable = false, length = 100)
//    private String name;  // 아파트 이름
//
//    @Column(nullable = false, length = 255)
//    private String address;  // 아파트 주소
//
//    @CreationTimestamp
//    private LocalDateTime created_date;  // 아파트 등록일
//
//    @UpdateTimestamp
//    private LocalDateTime modified_date;  // 아파트 수정일
//
//    private LocalDateTime removed_date;  // 아파트 삭제일 (soft delete)
//}