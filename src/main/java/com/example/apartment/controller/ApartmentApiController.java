package com.example.apartment.controller;

import com.example.apartment.dto.ApartmentRequest;
import com.example.apartment.model.Apartment;
import com.example.apartment.service.ApartmentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;

@Controller // JSP 뷰를 반환할 때 사용
@CrossOrigin(origins = "*") // 모든 도메인에서의 요청을 허용
public class ApartmentApiController {

  @Autowired
  private ApartmentService apartmentService;

  // 아파트 저장 (POST)
  @PostMapping("/saveLocation")
  public ResponseEntity<String> saveApartment(@RequestBody ApartmentRequest request) {
    // 아파트 정보 유효성 검사
    if (request.getApartmentName() == null || request.getApartmentName().isEmpty()
        || request.getAddress() == null || request.getAddress().isEmpty()) {
      return ResponseEntity.status(400).body("아파트 이름과 주소는 필수 항목입니다.");
    }

    // ApartmentService를 이용해 아파트 정보를 저장
    boolean isSaved = apartmentService.saveApartment(request);

    if (isSaved) {
      return ResponseEntity.ok("아파트가 성공적으로 저장되었습니다.");
    } else {
      return ResponseEntity.status(500).body("아파트 저장에 실패했습니다.");
    }
  }

  // 내 아파트 정보 조회 (JSP 페이지로 반환)
  @GetMapping("/myApartment")
  public String getMyApartment(Model model) {
    try {
      Apartment apartment = apartmentService.getMyApartment();
      model.addAttribute("apartment", apartment); // JSP에 아파트 정보 전달
      return "apartment/myApartment"; // 아파트 정보 JSP 페이지 반환
    } catch (Exception e) {
      model.addAttribute("error", e.getMessage());
      return "error"; // error.jsp 페이지로 오류 전달
    }
  }

  // 아파트 수정 페이지 (JSP 페이지로 반환)
//  @GetMapping("/editApartment")
//  public String editApartment(Model model) {
//    try {
//      Apartment apartment = apartmentService.getMyApartment();
//      model.addAttribute("apartment", apartment); // 수정할 아파트 정보 전달
//      return "apartment/editApartment"; // 아파트 수정 JSP 페이지 반환
//    } catch (Exception e) {
//      model.addAttribute("error", e.getMessage());
//      return "error"; // error.jsp 페이지로 오류 전달
//    }
  }
