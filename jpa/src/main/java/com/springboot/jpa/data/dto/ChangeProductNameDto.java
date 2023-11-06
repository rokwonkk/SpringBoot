package com.springboot.jpa.data.dto;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@Schema(title = "수정될 물품 정보")
public class ChangeProductNameDto {

    @Schema(description = "물품번호", example = "1")
    private Long number;
    @Schema(description = "물품이름", example = "연필")
    private String name;

    public ChangeProductNameDto(Long number, String name) {
        this.number = number;
        this.name = name;
    }
    public ChangeProductNameDto() {
    }

}
