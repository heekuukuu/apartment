package com.example.apartment.respository;

import com.example.apartment.model.Board;
import com.example.apartment.model.User;
import com.example.apartment.type.BoardStatus;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

@Repository
public interface BoardRepository extends JpaRepository<Board, Long> {

  Page<Board> findByTitleContainingOrContentContaining(String title, String content,
      Pageable pageable);

  // 사용자Id로  게시물상태변경
  @Modifying
  @Query("UPDATE Board b SET b.status = :status WHERE b.user.id = :userId")
  void updateBoardStatusByUserId(@Param("userId") Long userId, @Param("status") BoardStatus status);


 @Transactional
 void deleteAllById(Long id);
  @Transactional
  void deleteAllByUser(User user);
}
