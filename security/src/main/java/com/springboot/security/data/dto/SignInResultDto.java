package com.springboot.security.data.dto;

import lombok.*;

@EqualsAndHashCode(callSuper = true)
@Data
@NoArgsConstructor
@AllArgsConstructor
@ToString
public class SignInResultDto extends SignUpResultDto{

    private String token;

    @Builder
    public SignInResultDto(boolean success, int code, String msg, String token) {
        super(success, code, msg);
        this.token = token;
    }
}
