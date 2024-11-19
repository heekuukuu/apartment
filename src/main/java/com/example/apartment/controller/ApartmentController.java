package com.example.apartment.controller;


import com.example.apartment.respository.ApartmentRepository;
import com.example.apartment.service.ApartmentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;

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





    // 아파트 삭제
    @DeleteMapping("/{id}")
    public void deleteApartment(@PathVariable Long id) {
        apartmentService.deleteApartment(id);
    }
}