package com.example.apartment.respository;

import com.example.apartment.model.Apartment;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface ApartmentRepository extends JpaRepository<Apartment, Long> {


   boolean existsByAddress(String address);
    // 주소로 아파트를 찾는 메서드 정의

    Optional<Apartment> findByEmail(String email);

}
