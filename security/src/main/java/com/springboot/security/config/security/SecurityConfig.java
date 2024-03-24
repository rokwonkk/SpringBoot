package com.springboot.security.config.security;

import com.springboot.security.config.JwtTokenProvider;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpMethod;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityCustomizer;
import org.springframework.security.config.annotation.web.configurers.AbstractHttpConfigurer;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;

@Configuration
@EnableWebSecurity
public class SecurityConfig {

    private final JwtTokenProvider jwtTokenProvider;

    @Autowired
    public SecurityConfig(JwtTokenProvider jwtTokenProvider) {
        this.jwtTokenProvider = jwtTokenProvider;
    }

    @Bean
    public SecurityFilterChain filterChain(HttpSecurity http) throws Exception {
        http
                .csrf(AbstractHttpConfigurer::disable)
                .authorizeHttpRequests((requests) -> requests
                        .requestMatchers(
                                "/sign-api/",
                                "/sign-api/exception",
                                "/sign-api/sign-in",
                                "/sign-api/sign-up").permitAll()
                        .requestMatchers(HttpMethod.GET, "/product/**").permitAll()
                        .requestMatchers("**exception**").permitAll()
                        .anyRequest().hasRole("ADMIN")
                )
                .sessionManagement((management) -> management
                        .sessionCreationPolicy(SessionCreationPolicy.STATELESS))
                .exceptionHandling((handle) -> handle
                        .accessDeniedHandler(new CustomAccessDeniedHandler())
                        .authenticationEntryPoint(new CustomAuthenticationEntryPoint()))
                .addFilterBefore(new JwtAuthenticationFilter(jwtTokenProvider),
                        UsernamePasswordAuthenticationFilter.class);
        return http.build();
    }

    @Bean
    public WebSecurityCustomizer webSecurityCustomizer() {
        return web -> web
                .ignoring().requestMatchers(
                        "/v3/api-docs", "/swagger-resource/**",
                        "/swagger-ui.html", "/webjars/**", "/swagger-ui/**",
                        "/swagger/**", "/sign-api/exception");
    }
}

//    @Override
//    public void configure(HttpSecurity httpSecurity) throws Exception {
//
//        httpSecurity
//                //UI를 사용하는 것을 기본값으로 가진 시큐리티 설정을 비활성화
//                .httpBasic().disable()
//
//                //REST API에서는 CSRF 보안이 필요 없기 때문에 비활성화 함.
//                .csrf().disable()
//
//                //REST API 기반 애플리케이션의 동작 방식을 설정.
//                //JWT 토큰으로 인증처리 하며, 세션은 사용하지 않기 때문에 STATELESS로 설정
//                .sessionManagement()
//                .sessionCreationPolicy(
//                        SessionCreationPolicy.STATELESS)
//
//                .and()
//
//                //어플리케이션에 들어오는 요청에 대한 사용 권한 체크
//                .authorizeRequests()
//                // 이 경로에 대해서는 모두 허용
//                .requestMatchers("/sign-api/sign-in", "/sign-api/sign-up", "/sign-api/exception").permitAll()
//                // 이 경로로 시작하는 GET 요청은 모두 허용
//                .requestMatchers(HttpMethod.GET, "/product/**").permitAll()
//                // 이 단어가 들어간 경로는 모두 허용
//                .requestMatchers("**exception**").permitAll()
//                //기타 요청은 인증된 권한을 가진 사용자에게 허용
//                .anyRequest().hasRole("ADMIN")
//
//                .and()
//                // 권한을 확인하는 과정에서 통과하지 못하는 예외가 발생할 경우 예외 전달
//                .exceptionHandling().accessDeniedHandler(new CustomAccessDeniedHandler())
//                .and()
//                // 인증 과정에서 예외가 발생할 경우 예외 전달.
//                .exceptionHandling().authenticationEntryPoint(new CustomAuthenticationEntryPoint())
//
//                .and()
//                // 어느 필터 앞에서 설정할 것인지에 따른 설정
//                // UsernamePasswordAuthenticationFilter 앞쪽에 앞에서 생성한 JwtAuthenticationFilter를 추가하겠다는 의미.
//                // 추가된 필터에 인증이 정상 처리 되면 UsernamePasswordAuthenticationFilter는 자동으로 통과되기 때문에 이런 구성을 함.
//                .addFilterBefore(new JwtAuthenticationFilter(jwtTokenProvider),
//                        UsernamePasswordAuthenticationFilter.class);
//    }
//
//    @Override
//    public void configure(WebSecurity webSecurity) throws Exception {
//        //인증과 인가가 모두 적용 되기 전에 적용, 시큐리티 영향권 밖에 있음.
//        //swagger에 적용되는 인증과 인가를 피하기 위해 ignoring()메서드를 사용해 예외처리. 말이 예외 처리지만 그냥 인증, 인가를 무시하는 경로설정임.
//        webSecurity.ignoring().requestMatchers("/v2/api-docs", "/swagger-resource/**",
//                "/swagger-ui.html", "/webjars/**", "/swagger/**", "/sign-api/exception");
//    }
//}