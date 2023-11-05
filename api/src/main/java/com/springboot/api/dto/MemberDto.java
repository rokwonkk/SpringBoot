package com.springboot.api.dto;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@Getter
@Setter
@ToString
@Schema(title = "맴버 정보")
public class MemberDto {

    @Schema(title = "맴버 이름", example = "이순신")
    private String name;
    @Schema(title = "맴버 이메일", example = "soonsinlee@gmail.com")
    private String email;
    @Schema(title = "맴버 부서", example = "정헌대부")
    private String organization;

}
