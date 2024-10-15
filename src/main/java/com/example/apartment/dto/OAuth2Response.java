package com.example.apartment.dto;

import java.util.Map;

public interface OAuth2Response {

    // 제공자 (Ex. naver, google, kakao)
    String getProvider();
    // 제공자에서 발급해주는 아이디(번호)
    String getProviderId();
    // 이메일
    String getEmail();
    // 사용자 실명 (설정한 이름)
    String getName();
    // 제공자에서 받은 원시 속성 맵
    Map<String, Object> getAttributes();
}