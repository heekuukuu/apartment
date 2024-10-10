package com.example.apartment.controller;


import com.example.apartment.model.Apartment;
import com.example.apartment.model.User;
import com.example.apartment.model.UserRole;
import com.example.apartment.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

@Controller
public class UserController {

    @Autowired
    private UserService userService;


    @GetMapping("/auth/joinForm")
    public String joinForm() {
        return "user/joinForm";
    }

    @GetMapping("/auth/loginForm")
    public String loginForm() {
        return "user/loginForm";
    }
    @GetMapping("/auth/socialLogin")
    public String socialLoginForm() {
        return "user/socialLogin"; // 소셜 로그인 페이지로 이동
    }
    @GetMapping("/auth/updateForm")
    public String updateForm() {
        return "user/updateForm";
    }



    @GetMapping("/auth/loginFailure")
    public String login(@RequestParam(value = "error", required = false)
                        String error, @RequestParam(value = "exception", required = false)
                        String exception, Model model) {
        model.addAttribute("error", error);
        model.addAttribute("exception", exception);
        return "/user/loginForm";
    }
    @Value("${kakao.api.key}")
    private String kakaoApiKey;

    @GetMapping("/apartment/apartmentSearch")
    public String showApartmentSearchPage(Model model) {
        model.addAttribute("apiKey", kakaoApiKey);
        return "/apartment/apartmentSearch"; // templates/apartmentSearch.html 파일을 반환
    }

    @PostMapping("/user/changeRole")
    public ModelAndView changeUserRole(@RequestParam String email,
                                       @RequestParam String employeeNumber,
                                       @RequestParam String newRole) {
        UserRole role;
        try {

            role = UserRole.valueOf(newRole.toString().toUpperCase());
        } catch (IllegalArgumentException e) {
            // 유효하지 않은 역할이 입력된 경우 예외 처리
            ModelAndView modelAndView = new ModelAndView("user/result");
            modelAndView.addObject("message", "유효하지 않은 권한 값입니다: " + newRole);
            return modelAndView;
        }

        // 권한 변경 로직 호출
        String resultMessage = userService.changeUserRole(email, employeeNumber, role);
        ModelAndView modelAndView = new ModelAndView("user/result");
        modelAndView.addObject("message", resultMessage);
        return modelAndView;
    }
    @GetMapping("/user/employeeNumberCheck")
    public String showEmployeeNumberCheckPage() {
        return "/user/employeeNumberCheck"; // JSP 페이지 이름 반환
    }


    @GetMapping("/user/profile")
    public String getUserProfile(Model model) {
        // UserService에서 로그인된 사용자의 이메일 가져오기
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        String email = authentication.getName(); // 이메일 가져오기

        // 이메일 로그 출력
        System.out.println("Current user email: " + email);

        try {
            // 이메일로 사용자 찾기
            User user = userService.findUserByEmail(email);

            if (user == null) {
                throw new IllegalArgumentException("해당 이메일로 사용자를 찾을 수 없습니다.");
            }

            // 아파트 정보 가져오기
            Apartment apartment = user.getApartment();

            // 모델에 사용자 정보 추가
            model.addAttribute("email", user.getEmail());
            model.addAttribute("apartmentName", apartment != null ? apartment.getName() : "아파트 정보 없음");
            model.addAttribute("apartmentAddress", apartment != null ? apartment.getAddress() : "주소 정보 없음");

            return "user/profile";

        } catch (IllegalArgumentException e) {
            System.err.println(e.getMessage());
            model.addAttribute("error", e.getMessage());
            return "error";  // 오류 페이지로 이동
        }
    }

    @PostMapping("/deleteUser")
    public String deleteUser(@RequestParam String email) {
        userService.deleteUser(email); // 이메일로 사용자 삭제 처리
        return "redirect:/profile"; // 삭제 후 리다이렉트 경로 설정
    }
    public String getCurrentUserEmail() {
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        if (authentication == null || !authentication.isAuthenticated()) {
            throw new IllegalStateException("로그인된 사용자가 없습니다.");
        }
        return authentication.getName(); // 이메일이나 사용자명을 반환
    }
}