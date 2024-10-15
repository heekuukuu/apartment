//package com.example.apartment.config.auth;
//
//
//// 스프링 시큐리티가 로그인 요청을 가로채서 완료시 userDetails 타입의
//// Object를 고유한 세션 저장소에저장
//
//import com.example.apartment.model.User;
//import lombok.Getter;
//import org.springframework.security.core.GrantedAuthority;
//import org.springframework.security.core.userdetails.UserDetails;
//
//import java.util.ArrayList;
//import java.util.Collection;
//
//@Getter
//public class PrincipalDetail22 implements UserDetails {
//
//    private User user;
//
//    public PrincipalDetail22(User user) {
//        this.user = user;
//    }
//    @Override
//    public String getPassword() {
//        return user.getPassword();
//    }
//
//    @Override
//    public String getUsername() {
//        return user.getUsername();
//    }
//
//    @Override
//    public boolean isAccountNonExpired() {
//        return true;
//    }
//
//    @Override
//    public boolean isAccountNonLocked() {
//        return true;
//    }
//
//    @Override
//    public boolean isCredentialsNonExpired() {
//        return true;
//    }
//
//    @Override
//    public boolean isEnabled(){
//        return true;
//
//    }
// @Override
//    public Collection<? extends GrantedAuthority> getAuthorities() {
//     Collection<GrantedAuthority> collectors = new ArrayList<>();
//     collectors.add(() -> {
//         return "ROLE_" + user.getRole();
//     });
//     return collectors;
//    }
//
//}