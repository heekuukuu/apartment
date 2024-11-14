package com.example.apartment.controller;


import com.example.apartment.model.Apartment;
import com.example.apartment.respository.ApartmentRepository;
import com.example.apartment.service.ApartmentService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Slf4j
@Controller
@RequestMapping("/apartments")
public class ApartmentController {

  @Autowired
  private ApartmentService apartmentService;
  @Autowired
  private ApartmentRepository apartmentRepository;

  @GetMapping("/apartmentSearch")
  public String searchApartment(Model model) {
    // 검색 로직 추가
    return "apartment/apartmentSearch"; // 검색 페이지 반환
  }

  @GetMapping("/myApartment")
  public String getMyApartment(Model model) {
    try {
      Apartment apartment = apartmentService.getMyApartment();
      model.addAttribute("apartment", apartment);
      return "apartment/myApartment"; // 아파트 정보 JSP 페이지 반환
    } catch (Exception e) {
      model.addAttribute("error", e.getMessage());
      return "error"; // error.jsp 페이지로 오류 전달
    }
  }
}







