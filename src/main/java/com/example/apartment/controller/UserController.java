package com.example.apartment.controller;


import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;

@Controller
public class UserController {

    @GetMapping("/auth/joinForm")
    public String joinForm() {
        return "user/joinForm";
    }

    @GetMapping("/auth/loginForm")
    public String loginForm() {
        return "user/loginForm";
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
}
