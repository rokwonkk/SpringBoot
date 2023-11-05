package com.springboot.api.controller;

import com.springboot.api.dto.MemberDto;
import io.swagger.v3.oas.annotations.Operation;
import org.springframework.web.bind.annotation.*;

import java.util.Map;

@RestController
@RequestMapping("/api/v1/post-api")
public class PostController {

    @RequestMapping(value = "/domain", method = RequestMethod.POST)
    @Operation(summary = "RequestMapping(PostMapping)",description = "requestmapping에 method=RequstMethod.POST", tags = {"02.POST", })
    private String postExample(){
        return "Hello Post API";
    }

    //http://localhost:8080/api/v1/post-api/member
    @PostMapping(value = "/member")
    @Operation(summary = "String을 이용한 PostMapping",description = "String으로 받아 toString형식으로 리턴", tags = {"02.POST", })
    public String postMember(@RequestBody Map<String, Object> postData){

        StringBuilder sb = new StringBuilder();

        postData.forEach((key, value) -> sb.append(key)
                .append(" : ")
                .append(value)
                .append("\n"));

        return sb.toString();
    }

    /**
     * DTO 객체를 활용한 POST API 구현
     * @param memberDto
     * @return
     */
    //http://localhost:8080/api/v1/post-api/member2
    @PostMapping(value = "/member2")
    @Operation(summary = "DTO 객체 이용한 PostMapping",description = "DTO 객체를 toString형식으로 리턴 ", tags = {"02.POST", })
    public String postMemberDto(@RequestBody MemberDto memberDto){
        return memberDto.toString();
    }

    /**
     * DTO 객체를 활용한 POST API 구현
     * @param memberDto
     * @return
     */
    //http://localhost:8080/api/v1/post-api/member3
    @PostMapping(value = "/member3")
    @Operation(summary = "DTO 객체 이용한 PostMapping",description = "DTO 객체를 바로 리턴 ", tags = {"02.POST", })
    public MemberDto postMemberDto1(@RequestBody MemberDto memberDto){
        return memberDto;
    }
}
