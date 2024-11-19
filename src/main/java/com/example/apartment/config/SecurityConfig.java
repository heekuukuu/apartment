package com.example.apartment.config;


import com.example.apartment.config.auth.PrincipalDetailService;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.web.authentication.AuthenticationFailureHandler;

@Configuration  // Bean등록
@EnableWebSecurity  // Security filter 추가
public class SecurityConfig extends WebSecurityConfigurerAdapter {

    private final AuthenticationFailureHandler customFailureHandler;
    private final PrincipalDetailService principalDetailService;

    public SecurityConfig(AuthenticationFailureHandler customFailureHandler, PrincipalDetailService principalDetailService) {
        this.customFailureHandler = customFailureHandler;
        this.principalDetailService = principalDetailService;
    }

    private static final String[] PERMIT_URL_ARRAY = {
            "/", "/auth/**", "/js/**", "/css/**", "/image/**", "/dummy/**",
            /* h2 */
            "/h2-console/**"
    };

    @Bean
    @Override
    public AuthenticationManager authenticationManagerBean() throws Exception {
        return super.authenticationManagerBean();

    }

    @Bean
    public BCryptPasswordEncoder bCryptPasswordEncoder() {
        return new BCryptPasswordEncoder();
    }

        // 시큐리티가 대신 로그인 해주는데 비밀번호 해쉬 비교를 위한 로직

        @Override
        protected void configure(AuthenticationManagerBuilder auth) throws Exception {
            auth.userDetailsService(principalDetailService).passwordEncoder(bCryptPasswordEncoder());
        }

        @Override
        protected void configure (HttpSecurity http) throws Exception {
            http.headers().frameOptions().sameOrigin();
            http.csrf().disable() // csrf token disable -> from tag를 이용해서 요청이 아닌 ajax call 에 토큰이 없어서 block 당함
                    .authorizeRequests() // 특정 경로에 권한을 가진 사용자만 접근가능
                    .antMatchers(PERMIT_URL_ARRAY) // 패턴
                    .permitAll() //허용
                    .anyRequest() // 야외 모든 요청
                    .authenticated()//인증필요
                    .and()
                    .formLogin()
                    .loginPage("/auth/loginForm")
                    .loginProcessingUrl("/auth/loginProc")
                    .defaultSuccessUrl("/")
                    .failureHandler(customFailureHandler) // 로그인 실패 핸들러
                    .and()
                    .logout()
                    .and()
                    .userDetailsService(principalDetailService);


        }


}