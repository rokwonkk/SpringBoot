package com.springboot.api.controller;

import io.swagger.v3.oas.annotations.Operation;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/v1/delete-api")
public class DeleteController {

    //http://localhost:8080/api/v1/delete-api/{String 값}
    @DeleteMapping(value = "/{variable}")
    @Operation(summary = "@PathVariable를 이용한 DELETE 메서드",description = "DeleteMapping에서 정의한 value의 이름과 메서드의 매개변수 이름이 동일해야 삭제 가능", tags = {"04.DELETE", })
    public String DeleteVariable(@PathVariable String variable){
        return variable;
    }

    //http://localhost:8080/api/v1/delete-api/request1?email=value
    @DeleteMapping(value = "/request1")
    @Operation(summary = "@RequestParam을 이용한 DELETE 메서드",description = "파라미터를 받아서 삭제 가능", tags = {"04.DELETE", })
    public String getRequestParam1(@RequestParam String email){
        return "e-mail : " + email;
    }
}
