package com.example.apartment.model;

import com.example.apartment.type.BoardStatus;
import java.time.LocalDateTime;
import java.util.List;
import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.Lob;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.OrderBy;
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
@Table(name = "boards")
public class Board {

  @Id
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  @Column(name = "board_id")
  private long id;

  @Column(nullable = false, length = 100)
  private String title;

  @Lob
  private String content;


  @Column(nullable = false, length = 20)
  private String category;

  @ManyToOne(fetch = FetchType.LAZY)// Many = board . one = user
  @JoinColumn(name = "user_id")
  private User user;

  @OneToMany(mappedBy = "board", fetch = FetchType.EAGER, cascade = CascadeType.REMOVE)
  @OrderBy("id desc")
  private List<Comment> comments;

  @CreationTimestamp
  private LocalDateTime created_date;


  // 게시글의 상태는 BoardStatus enum으로 관리
  @Enumerated(EnumType.STRING)
  @Column(nullable = false,name ="board_status")
  private BoardStatus status;

}