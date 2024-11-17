package com.example.apartment.model;


import com.example.apartment.type.LoginType;
import com.example.apartment.type.UserRole;
import com.example.apartment.type.UserStatus;
import java.time.LocalDateTime;
import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.OneToOne;
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
@Table(name = "users")

public class User {

  @Id //Primary key
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  @Column(name = "user_id")
  private Long id; //auto_increment

  @Column(nullable = false, length = 100, unique = true)  // 100자까지
  private String username; // 로그인용 사용자 ID

  @Column(length = 100)
  private String password; // 로그인용 비밀번호

  @Column(nullable = false, length = 50, unique = true)
  private String email;  //로그인용 이메일


  @Enumerated(EnumType.STRING)
  private UserRole role; // Enum

  @Enumerated(EnumType.STRING)
  private LoginType loginType;


  @CreationTimestamp
  private LocalDateTime created_date;


  @OneToOne(cascade = CascadeType.ALL)
  @JoinColumn(name = "apartment_id")  // 외래 키로 사용할 칼럼 이름
  private Apartment apartment;  // User는 하나의 Apartment를 가짐

  @OneToOne(mappedBy = "user") //
  private Employee employee;

  @Builder.Default
  @Enumerated(EnumType.STRING) // 상태를 관리하는 Enum 필드
  @Column(nullable = false, name = "users_status")
  private UserStatus status = UserStatus.ACTIVE; // 기본 상태는 ACTIVE

  public boolean isAdmin() {
    return this.role == UserRole.ADMIN;
  }
}
