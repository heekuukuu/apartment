package com.example.apartment.respository;


import com.example.apartment.model.Comment;
import com.example.apartment.model.User;
import com.example.apartment.type.CommentStatus;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

@Repository
public interface CommentRepository extends JpaRepository<Comment, Long> {

  void deleteAllByUser(User user);

  @Modifying
  @Query("UPDATE Comment c SET c.status = :status WHERE c.user.id = :userId")
  void updateCommentStatusByUserId(@Param("userId") Long userId,
      @Param("status") CommentStatus status);


}
