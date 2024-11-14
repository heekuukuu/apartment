//package com.example.apartment.handler;
//
//
//
//import org.springframework.security.authentication.BadCredentialsException;
//import org.springframework.security.core.AuthenticationException;
//import org.springframework.security.web.authentication.SimpleUrlAuthenticationFailureHandler;
//import org.springframework.stereotype.Component;
//
//import javax.servlet.ServletException;
//import javax.servlet.http.HttpServletRequest;
//import javax.servlet.http.HttpServletResponse;
//import java.io.IOException;
//import java.net.URLEncoder;
//
////로그인 실패시 출력
//@Component
//public class CustomAuthFailureHandler extends SimpleUrlAuthenticationFailureHandler {
//
// @Override
//    public void onAuthenticationFailure(HttpServletRequest request, HttpServletResponse response, AuthenticationException exception) throws IOException, ServletException {
//
//
//
//
//
//     String errorMessage = "";
//
//     if (exception instanceof BadCredentialsException) {
//         errorMessage = "아이디 또는 비밀번호가 맞지 않습니다. 다시 확인해 주세요.";
//     }
//     errorMessage = URLEncoder.encode(errorMessage, "UTF-8");
//
//     setDefaultFailureUrl("/auth/loginFailure?error=true&exception=" + errorMessage);
//     super.onAuthenticationFailure(request, response, exception);
//
// }}

package com.example.apartment.handler;

import java.io.IOException;
import java.net.URLEncoder;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.oauth2.core.OAuth2AuthenticationException;
import org.springframework.security.web.authentication.SimpleUrlAuthenticationFailureHandler;
import org.springframework.stereotype.Component;
import org.springframework.web.bind.annotation.ControllerAdvice;

@ControllerAdvice
@Component
public class CustomAuthFailureHandler extends SimpleUrlAuthenticationFailureHandler {

  @Override
  public void onAuthenticationFailure(HttpServletRequest request, HttpServletResponse response,
      AuthenticationException exception) throws IOException, ServletException {

    String errorMessage = "알 수 없는 이유로 로그인에 실패했습니다. 관리자에게 문의하세요."; // 기본 메시지 설정

    if (exception instanceof BadCredentialsException) {
      errorMessage = "아이디 또는 비밀번호가 맞지 않습니다. 다시 확인해 주세요.";
    } else if (exception.getMessage().contains("이미 가입된 이메일")) {
      errorMessage = "이미 가입된 이메일입니다. 다른 로그인 방식을 사용하세요.";
    } else if (exception instanceof OAuth2AuthenticationException) {
      errorMessage = "소셜 로그인에 실패했습니다. 다시 시도해 주세요.";
    } else {
      // 다른 예외 유형에 대한 메시지를 추가할 수 있습니다.
      errorMessage = exception.getMessage();
    }

    errorMessage = URLEncoder.encode(errorMessage, "UTF-8");

    setDefaultFailureUrl("/auth/loginFailure?error=true&exception=" + errorMessage);
    super.onAuthenticationFailure(request, response, exception);
  }
}