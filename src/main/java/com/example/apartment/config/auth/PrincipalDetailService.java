package com.example.apartment.config.auth;


import com.example.apartment.model.User;
import com.example.apartment.respository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

@Service
public class PrincipalDetailService implements UserDetailsService {
    //해당 username 이  DB에 있는지 확인
    @Autowired
    private UserRepository userRepository;

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        User principal = userRepository.findByUsername(username).orElseThrow(() -> new UsernameNotFoundException("해당 사용자를 찾을 수 없습니다." + username));
        return new PrincipalDetail(principal); //시큐리티 세션에 저장
    }
    public UserDetails loadUserByEmail(String email) throws UsernameNotFoundException {
        User principal = userRepository.findByEmail(email)
                .orElseThrow(() -> new UsernameNotFoundException("사용자를 찾을 수 없습니다: " + email));

        return new PrincipalDetail(principal); // 시큐리티 세션에 저장
    }

    }

