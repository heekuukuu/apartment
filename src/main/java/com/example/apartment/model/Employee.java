package com.example.apartment.model;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;

@Entity
@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
@Table(name = "employees")
public class Employee {

    @Id // Primary key
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id; // 사원 고유 ID

    @Column(nullable = false, length = 50, unique = true)
    private String email; // 사원 이메일

    @Column(nullable = false, length = 6)
    private String employeeNumber; // 사원 번호 (6자리)

    @Enumerated(EnumType.STRING) // Enum으로 역할 저장
    @Column(nullable = false)
    private UserRole role; // 직원 역할
}