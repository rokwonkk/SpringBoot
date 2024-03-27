package com.manning.sbip.ch02.model;

import javax.validation.constraints.Max;
import javax.validation.constraints.Min;

/**
 * 빈 밸리데이션을 사용하면 애플리케이션에 적용되는 제약 사항 준수 여부를 검증 할 수 있다.
 * 필드를 붙여 사용하는 하이버네이트 밸리데이터 애너테이션
 * @NotBlank                    문자열이 null이 아니고 앞뒤 공백 제거한 문자열 길이가 0보다 크다는 것을 검사
 * @NotEmpty                    null이 아니고 비어 있지 않음을 검사
 * @NotNull                     null이 아님을 검사
 * @Min(value = )               최솟값을 지정 후 크거나 같은지 검사
 * @Max(value = )               최댓값을 지정 후 작거나 같은지 검사
 * @Pattern(regex=, flags)      regex로 지정한 정규 표현식 준수하는지 검사
 * @Size(min=, max=)            최솟값, 최대값을 준수하는지 검사
 * @Email                       문자열이 유효한 이메일 주소를 나타내는지 검사
 */
public class Course {

    private long id;
    private String name;
    private String category;

    @Min(value = 1, message = "A course should have a minimum of 1 rating")
    @Max(value = 5, message = "A course should have a maximum of 5 rating")
    private int rating;

    private String description;

    public Course() {
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getCategory() {
        return category;
    }

    public void setCategory(String category) {
        this.category = category;
    }

    public int getRating() {
        return rating;
    }

    public void setRating(int rating) {
        this.rating = rating;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }
}
