package com.example.apartment.service;

import com.example.apartment.config.auth.PrincipalDetail;
import com.example.apartment.dto.GoogleResponse;
import com.example.apartment.dto.KakaoResponse;
import com.example.apartment.dto.NaverResponse;
import com.example.apartment.dto.OAuth2Response;
import com.example.apartment.model.User;
import com.example.apartment.respository.UserRepository;
import com.example.apartment.type.LoginType;
import com.example.apartment.type.UserRole;
import java.util.Optional;
import org.springframework.security.oauth2.client.userinfo.DefaultOAuth2UserService;
import org.springframework.security.oauth2.client.userinfo.OAuth2UserRequest;
import org.springframework.security.oauth2.core.OAuth2AuthenticationException;
import org.springframework.security.oauth2.core.user.OAuth2User;
import org.springframework.stereotype.Service;

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

        String email = oAuth2Response.getEmail();

        // 이메일 중복 체크
        Optional<User> userOptional = userRepository.findByEmail(email);
        if (userOptional.isPresent()) {
            User existingUser = userOptional.get();

            // 이메일이 중복되면 로그인 타입을 비교하여 예외 처리
            if (!existingUser.getLoginType().equals(LoginType.valueOf(oAuth2Response.getProvider().toUpperCase()))) {
                // 다른 로그인 방식으로 가입된 경우 예외 처리
                throw new OAuth2AuthenticationException("이미 가입된 이메일입니다. 해당 이메일은 "
                    + existingUser.getLoginType() + " 방식으로 가입된 회원입니다. 다른 로그인 방식을 사용하세요.");
            }

            // 동일한 로그인 유형으로 가입된 경우 사용자 정보 업데이트
            existingUser.setUsername(oAuth2Response.getName()); // 이름 업데이트
            userRepository.save(existingUser); // 사용자 정보 업데이트 저장
            // PrincipalDetail을 반환하여 인증 정보로 사용
            return new PrincipalDetail(existingUser, oAuth2Response.getAttributes());
        } else {
            // 신규 사용자 저장
            String username = email.split("@")[0];  // 이메일 앞부분을 사용자 아이디로 사용

            // 이메일 앞부분이 같은 경우라도 전체 이메일이 다르면 다른 사용자로 취급
            if (userRepository.existsByUsername(username)) {
                throw new OAuth2AuthenticationException("이미 사용된 사용자 이름입니다. 다른 이름을 선택하세요.");
            }

            User user = User.builder()
                .username(username)  // 이메일 앞부분을 사용자 아이디로 사용
                .email(email)
                .role(UserRole.USER)
                .loginType(LoginType.valueOf(oAuth2Response.getProvider().toUpperCase()))
                .build();

            userRepository.save(user);
            // PrincipalDetail을 반환하여 인증 정보로 사용
            return new PrincipalDetail(user, oAuth2Response.getAttributes());
        }
    }
}