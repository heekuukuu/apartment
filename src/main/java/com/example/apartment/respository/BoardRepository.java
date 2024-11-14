package com.example.apartment.respository;

import com.example.apartment.model.Board;
import com.example.apartment.model.User;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface BoardRepository extends JpaRepository<Board, Long> {
    void deleteAllByUser(User user);
    Page<Board> findByTitleContainingOrContentContaining(String title, String content, Pageable pageable);


}
