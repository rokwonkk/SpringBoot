package com.springboot.api.controller;

import com.springboot.api.dto.MemberDto;
import io.swagger.v3.oas.annotations.Operation;
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
    @Operation(summary = "@RequestBody를 이용한 PUT메서드",description = "Map을 이용함 ", tags = {"03.PUT", })
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
    @Operation(summary = "@RequestBody를 이용한 PUT메서드",description = "DTO를 이용해 String으로 반환 ", tags = {"03.PUT", })
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
    @Operation(summary = "@RequestBody를 이용한 PUT메서드",description = "DTO를 이용해 DTO를 반환 ", tags = {"03.PUT", })
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
    @Operation(summary = "ResponseEntity 이용한 PUT메서드",description = "header와 Body를 쉽게 구성", tags = {"03.PUT", })
    public ResponseEntity<MemberDto> postMemberDto3(@RequestBody MemberDto memberDto){
        return ResponseEntity
                .status(HttpStatus.ACCEPTED)
                .body(memberDto);
    }
}
