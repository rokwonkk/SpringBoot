package com.springboot.valid_exception.data.dto;

import jakarta.validation.constraints.*;
import lombok.*;

/**
 * 문자열 검증
 * @Null : null 값만 허용
 * @NotNull : null을 허용안함. "", " " ( 빈값 및 공백은 허용 )
 * @NotEmpty : null, "" (빈값) 허용안함. " " ( 공백은 허용 )
 * @NotBlank : null, "", " " 다 허용안함. ( 빈값, 공백 ) 허용안함.
 *
 * 최댓값, 최솟값 검증
 * BigDecimal, BigInteger, int, long 등의 타입 지원.
 * @DemicalMax( value = "$numberString" ) : $numberString보다 작은 값 허용
 * @DemicalMin( value = "$numberString" ) : $numberString보다 큰 값 허용
 * @Min ( value = $number ) : $number 이상의 값을 허용
 * @Max ( value = $number ) : $number 이하의 값을 허용
 *
 * 값의 범위 검증
 * BigDecimal, BigInteger, int, long 등의 타입 지원.
 * @Positive : 양수허용
 * @PositiveOrZero : 0을 포함한 양수허용
 * @Negative : 음수 허용
 * @NegativeOrZero : 0을 포함한 음수허용
 *
 * 시간에 대한 검증
 * Date, LocalDate, LocalDateTime 등의 타입 지원
 * @Future : 현재보다 미래의 날짜를 허용
 * @FutureOrPresent : 현재를 포함한 미래의 날짜를 허용
 * @Past : 현재보다 과거의 날짜를 허용
 * @PastOrPresent : 현재를 포함한 과거의 날짜를 허용
 *
 * 이메일검증
 * @Email : 이메일 형식을 검사, "" ( 빈값은 허용 )
 *
 * 자릿수 범위 검증
 * BigDecimal, BigInteger, int, long 등의 타입 지원.
 * @Digits(integer = $number1, fraction = $number2) : $number1의 정수 자릿수와 $number2의 소수 자릿수를 허용한다.
 *
 * Boolean 검증
 * @AssertTrue : ture인지 체크, null값은 체크하지 않음.
 * @AssertFalse : false인지 체크, null값은 체크하지 않음.
 *
 * 문자열 길이 검증
 * @Size(min = $number1, max = $number2) : $number1 이상 $number2 이하의 범위를 허용
 *
 * 정규식 검증
 * @Pattern(regexp = "$expression"): 정규식을 검사. 정규식은 자바 java.util.regex.Pattern패키지의 컨벤션을 따름.
 */

@Data
@NoArgsConstructor
@AllArgsConstructor
@ToString
@Builder
public class ValidRequestDto {

    @NotBlank
    String name;

    @Email
    String email;

    @Pattern(regexp = "^01(?:0|1|[6-9])[.-]?(\\d{3}|\\d{4})[.-]?(\\d{4})$")
    String phoneNumber;

    @Min(value = 20) @Max(value = 40)
    int age;

    @Size(min=0, max=40)
    String description;

    @Positive
    int count;

    @AssertTrue
    boolean booleanCheck;

}
