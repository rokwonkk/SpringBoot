package com.springboot.security.controller;

import com.springboot.security.data.dto.SignInResultDto;
import com.springboot.security.data.dto.SignUpResultDto;
import com.springboot.security.service.SignService;
import io.swagger.v3.oas.annotations.media.Schema;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.Map;

@Slf4j
@RestController
@RequestMapping("/sign-api")
public class SignController {

    private final SignService service;

    @Autowired
    public SignController(SignService service){
        this.service = service;
    }

    @PostMapping(value = "/sign-in")
    public SignInResultDto signIn(
            @Schema(description = "id", example = "아이디") @RequestParam String id,
            @Schema(description = "password", example = "비번") @RequestParam String password
    ) throws RuntimeException {
        log.info("[signIn] 로그인을 시도하고 있습니다. id : {}, pw : ****", id);
        SignInResultDto dto = service.signIn(id, password);

        if(dto.getCode() == 0){
            log.info("[signIn] 정상적으로 로그인 되었습니다. id : {}, token : {}", id,
                    dto.getToken());
        }
        return dto;
    }

    @PostMapping(value = "/sign-up")
    public SignUpResultDto signUp(
        @Schema(description = "id", example = "id") @RequestParam String id,
        @Schema(description = "password", example = "password") @RequestParam String password,
        @Schema(description = "name", example = "name") @RequestParam String name,
        @Schema(description = "role", example = "role") @RequestParam String role){
        log.info("[signUp] 회원가입을 수행합니다. id : {}, password : ****, name : {}, role : {}", id,
        name, role);
        SignUpResultDto dto = service.signUp(id, password, name, role);

        log.info("[signUp] 회원가입을 완료 했습니다. id : {}", id);
        return dto;
    }

    @GetMapping(value = "/exception")
    public void exceptionTest() throws RuntimeException{
        throw new RuntimeException("접근이 금지되었습니다.");
    }

    @ExceptionHandler(value = RuntimeException.class)
    public ResponseEntity<Map<String, String>> ExceptionHandler(RuntimeException e){

        HttpHeaders responseHeaders = new HttpHeaders();
        //responseHeader.add(HttpHeaders.CONTENT_TYPE, "application/json");
        HttpStatus httpStatus = HttpStatus.BAD_REQUEST;

        log.error("ExceptionHandler 호출, {}, {}", e.getCause(), e.getMessage());

        Map<String, String> map = new HashMap<>();

        map.put("error type", httpStatus.getReasonPhrase());
        map.put("code", "400");
        map.put("message", "에러 발생");

        return new ResponseEntity<>(map, responseHeaders, httpStatus);
    }
}
