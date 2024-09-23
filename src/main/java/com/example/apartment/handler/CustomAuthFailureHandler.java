package com.example.apartment.handler;



import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.web.authentication.SimpleUrlAuthenticationFailureHandler;
import org.springframework.stereotype.Component;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.net.URLEncoder;

//로그인 실패시 출력
@Component
public class CustomAuthFailureHandler extends SimpleUrlAuthenticationFailureHandler {

 @Override
    public void onAuthenticationFailure(HttpServletRequest request, HttpServletResponse response, AuthenticationException exception) throws IOException, ServletException {





     String errorMessage = "";

     if (exception instanceof BadCredentialsException) {
         errorMessage = "아이디 또는 비밀번호가 맞지 않습니다. 다시 확인해 주세요.";
     }
     errorMessage = URLEncoder.encode(errorMessage, "UTF-8");

     setDefaultFailureUrl("/auth/loginFailure?error=true&exception=" + errorMessage);
     super.onAuthenticationFailure(request, response, exception);

 }}