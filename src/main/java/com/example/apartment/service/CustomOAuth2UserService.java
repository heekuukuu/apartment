package com.example.apartment.service;

import com.example.apartment.config.auth.PrincipalDetail;
import com.example.apartment.dto.GoogleResponse;
import com.example.apartment.dto.KakaoResponse;
import com.example.apartment.dto.NaverResponse;
import com.example.apartment.dto.OAuth2Response;
import com.example.apartment.model.LoginType;
import com.example.apartment.model.User;
import com.example.apartment.model.UserRole;
import com.example.apartment.respository.UserRepository;
import org.springframework.security.oauth2.client.userinfo.DefaultOAuth2UserService;
import org.springframework.security.oauth2.client.userinfo.OAuth2UserRequest;
import org.springframework.security.oauth2.core.OAuth2AuthenticationException;
import org.springframework.security.oauth2.core.user.OAuth2User;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class CustomOAuth2UserService extends DefaultOAuth2UserService {

    private final UserRepository userRepository;

    public CustomOAuth2UserService(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    @Override
    public OAuth2User loadUser(OAuth2UserRequest userRequest) throws OAuth2AuthenticationException {
        OAuth2User oAuth2User = super.loadUser(userRequest);

        String registrationId = userRequest.getClientRegistration().getRegistrationId();
        OAuth2Response oAuth2Response;

        // OAuth2 로그인 제공자에 따른 응답 매핑
        switch (registrationId) {
            case "naver":
                oAuth2Response = new NaverResponse(oAuth2User.getAttributes());
                break;
            case "google":
                oAuth2Response = new GoogleResponse(oAuth2User.getAttributes());
                break;
            case "kakao":
                oAuth2Response = new KakaoResponse(oAuth2User.getAttributes());
                break;
            default:
                throw new OAuth2AuthenticationException("지원되지 않는 제공자: " + registrationId);
        }

        // 사용자 이메일을 이용하여 기존 사용자 조회
        Optional<User> userOptional = userRepository.findByEmail(oAuth2Response.getEmail());
        User user;

        if (userOptional.isPresent()) {
            user = userOptional.get();

            // 이메일이 존재하지만 다른 로그인 유형으로 가입된 경우 예외를 던짐
            if (!user.getLoginType().equals(LoginType.valueOf(oAuth2Response.getProvider().toUpperCase()))) {
                throw new OAuth2AuthenticationException("이미 가입된 이메일입니다. 다른 로그인 방식을 사용하세요.");
            }

            // 동일한 로그인 유형으로 가입된 경우 사용자 정보를 업데이트
            user.setUsername(oAuth2Response.getName());
            userRepository.save(user); // 사용자 정보 업데이트 저장
        } else {
            // 신규 사용자 저장
            user = User.builder()
                    .username(oAuth2Response.getName())
                    .email(oAuth2Response.getEmail())
                    .role(UserRole.USER)
                    .loginType(LoginType.valueOf(oAuth2Response.getProvider().toUpperCase()))
                    .build();
            userRepository.save(user);
        }

        // PrincipalDetail을 반환하여 인증 정보로 사용
        return new PrincipalDetail(user);
    }
}