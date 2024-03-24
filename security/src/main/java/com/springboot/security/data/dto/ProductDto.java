package com.springboot.security.data.dto;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@Schema(title = "물품 정보")
public class ProductDto {

    @Schema(description = "물품이름", example = "연필")
    private String name;
    @Schema(description = "물품가격", example = "500000")
    private int price;
    @Schema(description = "물품개수", example = "100")
    private int stock;

    public ProductDto(String name, int price, int stock) {
        this.name = name;
        this.price = price;
        this.stock = stock;
    }
}
