package com.example.apartment.controller;

import com.example.apartment.dto.ApartmentRequest;
import com.example.apartment.service.ApartmentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController

@CrossOrigin(origins = "*") // 모든 도메인에서의 요청을 허용

public class ApartmentApiController {

    @Autowired
    private ApartmentService apartmentService;

@PostMapping("/saveLocation")
public ResponseEntity<String> saveApartment(@RequestBody ApartmentRequest request) {
    // ApartmentService를 이용해 아파트 정보를 저장
    boolean isSaved = apartmentService.saveApartment(request);

    if (isSaved) {
        return ResponseEntity.ok("아파트가 성공적으로 저장되었습니다.");
    } else {
        return ResponseEntity.status(500).body("아파트 저장에 실패했습니다.");
    }
}

    @GetMapping("/saveLocation")
    public ResponseEntity<String> handleGetRequest() {
        return ResponseEntity.status(405).body("GET method not allowed for this endpoint.");

}
}