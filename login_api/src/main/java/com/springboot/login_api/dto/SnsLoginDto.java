package com.springboot.login_api.dto;

import jakarta.validation.constraints.NotEmpty;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@NoArgsConstructor
@AllArgsConstructor
public class SnsLoginDto {

    @NotEmpty
    String id;

    String email;

    String regiType;

}