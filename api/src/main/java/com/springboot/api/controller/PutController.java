package com.springboot.api.controller;

import com.springboot.api.dto.MemberDto;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.Map;

@RestController
@RequestMapping("/api/v1/put-api")
public class PutController {

    //http://localhost:8080/api/v1/put-api/member
    @PutMapping(value = "/member")
    public String postMember(@RequestBody Map<String, Object> putData){
        StringBuilder sb = new StringBuilder();

        putData.forEach((key, value) ->
                sb.append(key)
                .append(" : ")
                .append(value)
                .append("\n"));

        return sb.toString();
    }

    /**
     * 스트링 타입으로 반환을 하게되면 텍스트로 전달을 받게 된다.
     * @param memberDto
     * @return
     */
    //http://localhost:8080/api/v1/put-api/member1
    @PutMapping(value = "/member1")
    public String postMemberDto(@RequestBody MemberDto memberDto){
        return memberDto.toString();
    }

    /**
     * 스트링 타입이 아닌 객체를 바로 반환하면 {키}={값} 형태로 전달을 받게 된다.
     * @param memberDto
     * @return
     */
    //http://localhost:8080/api/v1/put-api/member2
    @PutMapping(value = "/member2")
    public MemberDto postMemberDto2(@RequestBody MemberDto memberDto){
        return memberDto;
    }

    /**
     * ResponseEntity를 반환 타입으로 사용
     * 장점 : Header와 Body를 쉽게 구성 가능
     * @param memberDto
     * @return
     */
    //http://localhost:8080/api/v1/put-api/member3
    @PutMapping(value = "/member3")
    public ResponseEntity<MemberDto> postMemberDto3(@RequestBody MemberDto memberDto){
        return ResponseEntity
                .status(HttpStatus.ACCEPTED)
                .body(memberDto);
    }
}
