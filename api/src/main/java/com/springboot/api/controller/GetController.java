package com.springboot.api.controller;

import com.springboot.api.dto.MemberDto;
import io.swagger.v3.oas.annotations.Hidden;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.bind.annotation.*;

import java.util.Map;

@RestController
@RequestMapping("/api/v1/get-api")
@Tag(name = "01.GET")
public class GetController {

    private final Logger LOGGER = LoggerFactory.getLogger(GetController.class);

    //http://localhost:8080/api/v1/get-api
    //@Hidden -> 해당 객체를 표시 대상에서 제외함.
    @RequestMapping(value = "/hello", method = RequestMethod.GET)
    @Operation(summary = "@RequestMapping 구현",description = "@RequestMapping String 조회", tags = {"01.GET", })
    public String getHello(){
        LOGGER.info("getHello 메서드가 호출되었습니다.");
        return "Hello World";
    }

    //http://localhost:8080/api/v1/get-api/name
    @RequestMapping(value = "/name")
    @Operation(summary = "매개변수가 없는 GET 메서드",description = "정해진 Flature 응답")
    public String getName(){
        LOGGER.info("getName 메서드가 호출되었습니다.");
        return "Flature";
    }

    //http://localhost:8080/api/v1/get-api/variable1/{String 값}
    @RequestMapping(value = "/variable1/{variable}")
    @Operation(summary = "@PathVariable로 GET 메서드",description = "URL 자체에 값을 담아 요청 처리")
    public String getvaliable1(@PathVariable String variable){
        LOGGER.info("@PathVariable을 통해 들어온 값 : {}", variable);
        return variable;
    }

    //http://localhost:8080/api/v1/get-api/variable2/{String 값}
    @GetMapping(value = "/variable2/{variable}")
    @Operation(summary = "@GetMapping으로 GET 메서드",description = "변수 이름을 정의하면 매개변수와 매핑 가능")
    //public String getvaliable2(@PathVariable(value = "variable") String var){ 풀어서 쓴다면 이것과 같다.
    public String getvaliable2(@PathVariable("variable") String var){
        return var;
    }

    /**
     * 파라미터 형식으로 {키}={값} 형태로 요청 보낼때
     * @param name
     * @param email
     * @param organization
     * @return
     */
    //http://localhost:8080/api/v1/get-api/request1?name=value1@email=value2&organization=value3
    @GetMapping(value = "/request1")
    @Operation(summary = "파라미터 형식 조회",description = "파라미터 형식으로 조회를 해본다.")
    public String getRequestParam1(
            @RequestParam String name,
            @RequestParam String email,
            @RequestParam String organization
    ) {
        return name + " " + email + " " + organization;
    }

    /**
     * 어떤 값이 들어올지 모를때 Map 객체를 활용하면 좋다.
     * @param param
     * @return
     */
    //http://localhost:8080/api/v1/get-api/request2?key1=value1&key2=value2
    @GetMapping(value = "/request2")
    @Operation(summary = "Map 객체 활용 조회",description = "Map 객체 활용 조회를 해본다.")
    public String getRequestParam2(@RequestParam Map<String, String> param) {
        StringBuilder sb = new StringBuilder();
        param.forEach((key, value) -> sb.append(key)
                .append(" : ")
                .append(value)
                .append("\n"));
        return sb.toString();
    }

    //http://localhost:8080/api/v1/get-api/request3?name=value&email=value&organization=value3
    @GetMapping(value = "/request3")
    @Operation(summary = "Dto 조회 toString",description = "Dto를 toString 조회를 해본다.")
    public String getRequestParam3(MemberDto memberDto) {
        return memberDto.toString();
    }

    @GetMapping(value = "/request4")
    @Operation(summary = "MemberDto 조회",description = "Dto를 조회를 해본다.")
    public MemberDto getRequestParam4(MemberDto memberDto) {
        return memberDto;
    }
}