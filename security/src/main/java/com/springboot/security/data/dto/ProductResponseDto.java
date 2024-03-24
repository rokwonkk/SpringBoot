package com.springboot.security.data.dto;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@Schema(title = "Response 물품 정보")
public class ProductResponseDto {

    @Schema(description = "물품번호", example = "1")
    private Long number;
    @Schema(description = "물품이름", example = "연필")
    private String name;
    @Schema(description = "물품가격", example = "500000")
    private int price;
    @Schema(description = "물품갯수", example = "100")
    private int stock;

    public ProductResponseDto() {

    }
    public ProductResponseDto(Long number, String name, int price, int stock) {
        this.number = number;
        this.name = name;
        this.price = price;
        this.stock = stock;
    }
}
