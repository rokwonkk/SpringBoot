package com.springboot.security.config;

import com.springboot.security.service.UserDetailsService;
import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jws;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import jakarta.annotation.PostConstruct;
import jakarta.servlet.http.HttpServletRequest;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Component;

import java.nio.charset.StandardCharsets;
import java.util.Base64;
import java.util.Date;
import java.util.List;

@Component
@RequiredArgsConstructor
@Slf4j
public class JwtTokenProvider {

    private final UserDetailsService userDetailsService;

    @Value("${springboot.jwt.secret}")
    private String secretKey = "secretKey";
    private final long tokenValidMillisecond = 1000L * 60 * 60;

    /**
     * @PostConstruct 빈 객체 주입 이후 수행되는 메서드
     * @Component 있으므로 scan에 대상이 됨으로 자동으로 빈으로 주입됨.
     * 그때 init메서드가 자동 실행됨. secretKey를 base64형식으로 인코딩 함.
     */
    @PostConstruct
    protected void init() {
        log.info("[init] JwtTokenProvider 내 secretKey 초기화 시작 {}", secretKey);
        secretKey = Base64.getEncoder().encodeToString(secretKey.getBytes(StandardCharsets.UTF_8));
        log.info("[init] JwtTokenProvider 내 secretKey 초기화 완료 {}", secretKey);
    }

    public String createToken(String userUid, List<String> roles) {
        log.info("[createToken] 토큰 생성 시작");
        // JWT 토큰에 값을 넣기위해 Claims 객체 생성. setSubject 메서드를 통해 sub 속성에 값을 추가하려면 User의 uid값을 사용함.
        Claims claims = Jwts.claims().setSubject(userUid);
        // 사용자의 권한을 확인하기 위해 role 값 별개로 추가.
        claims.put("roles", roles);
        Date now = new Date();

        //Builder를 사용해 토큰을 생성함.
        String token = Jwts.builder()
                .setClaims(claims)
                .setIssuedAt(now)
                .setExpiration(new Date(now.getTime() + tokenValidMillisecond))
                .signWith(SignatureAlgorithm.HS256, secretKey)
                .compact();

        log.info("[createToken] 토큰 생성 완료");
        return token;
    }

    /**
     * 필터에서 인증 성공했을 때 SecurityContextHolder에 저장할 Authentication을 생성하는 역할을 함.
     * Authentication을 구현하는 편한 방법 -> UsernamePasswordAuthenticationToken을 사용하면됨.
     * 사용하기 위해서는 UserDetails가 필요함. 이 객체는 UserDetailsService로 가져오면됨.
     * 이때 사용되는 Username값은 아래의 getUsername 메서드로 만들어줌.
     * @param token
     * @return
     */
    public Authentication getAuthentication(String token) {
        log.info("[getAuthentication] 토큰 인증 정보 조회 시작");
        UserDetails userDetails = userDetailsService.loadUserByUsername(this.getUsername(token));
        log.info("[getAuthentication] 토큰 인증 정보 조회 완료, UserDetails UserName : {}", userDetails.getAuthorities());
        return new UsernamePasswordAuthenticationToken(userDetails, "", userDetails.getAuthorities());
    }

    public String getUsername(String token) {
        log.info("[getUsername] 토큰 기반 회원 구별 정보 추출");
        // Jwts.parser()를 통해서 secretKey를 설정하고 클레임을 추출해 토큰할 때 넣었던 sub값을 추출한다.
        String info = Jwts.parser().setSigningKey(secretKey).parseClaimsJws(token).getBody().getSubject();
        log.info("[getUsername] 토큰 기반 회원 구별 정보 추출 완료, info : {}", info);
        return info;
    }

    /**
     * 헤더 값으로 전달된 X-AUTH-TOKEN 값을 가져와 리턴함.
     * 클라이언트가 헤더를 통해 애플리케이션 서버로 JWT 토큰 값을 전달 해야 정상적인 추출이 가능.
     * 헤더의 이름은 임의로 해도 됨.
     * @param request
     * @return
     */
    public String resolveToken(HttpServletRequest request) {
        log.info("[resolveToken] HTTP 헤더에서 Token 값 추출");
        return request.getHeader("X-AUTH-TOKEN");
    }

    /**
     * 토큰을 전달받아 클레임의 유효기간을 체크하고 boolean타입으로 값을 리턴하는 역할을 함.
     * @param token
     * @return
     */
    public boolean validateToken(String token) {
        log.info("[validateToken] 토큰 유효 체크 시작");
        try {
            Jws<Claims> claims = Jwts.parser().setSigningKey(secretKey).parseClaimsJws(token);
            return !claims.getBody().getExpiration().before(new Date());
        } catch (Exception e) {
            log.info("[validateToken] 토큰 유효 체크 예외 발생");
            return false;
        }
    }
}