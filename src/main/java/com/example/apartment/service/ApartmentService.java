package com.example.apartment.service;

import com.example.apartment.config.auth.PrincipalDetail;
import com.example.apartment.dto.ApartmentRequest;
import com.example.apartment.model.Apartment;
import com.example.apartment.model.User;
import com.example.apartment.respository.ApartmentRepository;
import com.example.apartment.respository.UserRepository;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.Optional;
@Service
@Slf4j
public class ApartmentService {

    @Autowired
    private ApartmentRepository apartmentRepository;

    @Autowired
    private UserRepository userRepository;



    // 아파트 수정
    public Apartment updateApartment(Long id, Apartment apartmentDetails) {
        Apartment apartment = apartmentRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Apartment not found"));
        apartment.setName(apartmentDetails.getName());
        apartment.setAddress(apartmentDetails.getAddress());
        return apartmentRepository.save(apartment);
    }

    // 아파트 삭제
    public void deleteApartment(Long id) {
        apartmentRepository.deleteById(id);
    }
    // 로그인된 사용자의 이메일 가져오기
    public String getCurrentUserEmail() {
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        if (authentication != null) {
            Object principal = authentication.getPrincipal();
            if (principal instanceof PrincipalDetail) {
                PrincipalDetail principalDetail = (PrincipalDetail) principal;
                return principalDetail.getEmail(); // 일반 로그인 및 OAuth2 로그인 모두에서 이메일 반환
            } else if (principal instanceof UserDetails) {
                UserDetails userDetails = (UserDetails) principal;
                return userDetails.getUsername(); // 이메일을 사용하는 경우
            } else {
                return authentication.getName(); // OAuth 로그인 시 사용자 이름 반환
            }
        }
        return null; // 로그인되지 않은 경우
    }


    @Transactional
// 아파트 저장 (ApartmentRequest 사용)
    public boolean saveApartment(ApartmentRequest request) {
        try {
            // 로그인된 사용자의 이메일 가져오기
            String email = getCurrentUserEmail();
            if (email == null) {
                throw new RuntimeException("로그인된 사용자 정보를 찾을 수 없습니다.");
            }

            // 중복된 이메일과 주소를 확인
            Optional<Apartment> existingApartment = apartmentRepository.findByAddress(request.getAddress());
            if (existingApartment.isPresent()) {
                // 기존 아파트 수정
                Apartment apartmentToUpdate = existingApartment.get();
                apartmentToUpdate.setName(request.getApartmentName()); // 이름 업데이트
                apartmentToUpdate.setEmail(email); // 이메일 업데이트
                // 다른 필요한 필드도 업데이트...
                apartmentRepository.save(apartmentToUpdate);
            } else {
                // 새로운 아파트 생성 및 저장
                Apartment newApartment = new Apartment();
                newApartment.setName(request.getApartmentName());
                newApartment.setAddress(request.getAddress());
                newApartment.setEmail(email);  // 로그인된 사용자의 이메일 저장

                apartmentRepository.save(newApartment);
            }
            return true;
        } catch (Exception e) {
            e.printStackTrace();
            return false;
        }
    }

//    @Override
//    public Apartment getApartmentById(Long id) {
//        // ID로 아파트 정보 조회
//        return apartmentRepository.findById(id)
//                .orElseThrow(() -> new IllegalArgumentException("아파트를 찾을 수 없습니다. ID: " + id));
//    }

    // 현재 로그인된 사용자의 아파트 정보를 가져오는 메서드
    public Apartment getMyApartment() {
        String email = getCurrentUserEmail();
        if (email == null) {
            log.error("로그인된 사용자의 이메일을 가져올 수 없습니다.");
            throw new IllegalArgumentException("로그인된 사용자의 이메일을 가져올 수 없습니다.");
        }

        log.info("Fetching apartment for user with email: {}", email);
        User user = userRepository.findByEmail(email)
                .orElseThrow(() -> new IllegalArgumentException("사용자를 찾을 수 없습니다: " + email));

        Apartment apartment = user.getApartment();
        if (apartment == null) {
            log.warn("No apartment assigned to user: {}", email);
            throw new IllegalArgumentException("해당 사용자는 아파트에 소속되지 않았습니다.");
        }

        log.info("Found apartment for user {}: {}", email, apartment.getName());
        return apartment;
    }
}
