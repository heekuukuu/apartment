package com.example.apartment.config;

import com.example.apartment.config.auth.PrincipalDetailService;
import com.example.apartment.service.CustomOAuth2UserService;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.authentication.AuthenticationFailureHandler;

@Configuration  // Bean 등록
@EnableWebSecurity  // Security filter 추가
public class SecurityConfig {

    private final AuthenticationFailureHandler customFailureHandler;
    private final PrincipalDetailService principalDetailService;
    private final CustomOAuth2UserService customOAuth2UserService;

    public SecurityConfig(AuthenticationFailureHandler customFailureHandler, PrincipalDetailService principalDetailService, CustomOAuth2UserService customOAuth2UserService) {
        this.customFailureHandler = customFailureHandler;
        this.principalDetailService = principalDetailService;
        this.customOAuth2UserService = customOAuth2UserService;
    }

    @Bean
    public BCryptPasswordEncoder bCryptPasswordEncoder() {
        return new BCryptPasswordEncoder();
    }

    @Bean
    public SecurityFilterChain filterChain(HttpSecurity http) throws Exception {
        http.csrf().disable()
                .formLogin(form -> form
                        .loginPage("/auth/loginForm")
                        .loginProcessingUrl("/auth/loginProc")
                        .defaultSuccessUrl("/")
                        .failureHandler(customFailureHandler)
                        .permitAll()
                )
                .oauth2Login(oauth2 -> oauth2
                        .loginPage("/auth/socialLogin")
                        .defaultSuccessUrl("/")
                        .failureHandler(customFailureHandler)
                        .userInfoEndpoint(userInfo -> userInfo
                                .userService(customOAuth2UserService)
                        )
                )
                .authorizeRequests(auth -> auth
                        .antMatchers("/", "/auth/**", "/js/**", "/css/**", "/image/**", "/dummy/**", "/h2-console/**").permitAll()
                        .anyRequest().authenticated()
                );

        return http.build();
    }
}