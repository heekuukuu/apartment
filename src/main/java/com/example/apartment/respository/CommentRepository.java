package com.example.apartment.respository;


import com.example.apartment.model.Comment;
import com.example.apartment.model.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface CommentRepository extends JpaRepository<Comment, Long> {
    void deleteAllByUser(User user);
}
