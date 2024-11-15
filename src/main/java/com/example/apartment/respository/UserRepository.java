package com.example.apartment.respository;

import com.example.apartment.model.User;
import java.util.Optional;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

//DAO
//자동으로 Bean으로 등록


@Repository
public interface UserRepository extends JpaRepository<User, Long> {

    Optional<User> findByUsername(String username);
    Optional<User> findByEmail(String email);
    // 이메일이 존재하는지 확인하는 메소드 추가
    boolean existsByEmail(String email);
    // 아이디체크
  boolean existsByUsername(String username);


}
