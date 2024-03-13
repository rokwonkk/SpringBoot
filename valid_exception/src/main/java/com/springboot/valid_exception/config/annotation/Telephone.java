package com.springboot.valid_exception.config.annotation;


import jakarta.validation.Constraint;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

/**
 * @Target
 * 이 어노테이션을 어디서 선언 할 수 있는지 정의
 * ElementType
 *      .PACKGE
 *      .TYPE
 *      .CONSTRUCTOR
 *      .FIELD
 *      .METHOD
 *      .ANNOTATION_TYPE
 *      .LOCAL_VARIBLE
 *      .PARAMETER
 *      .TYPE_PARAMETER
 *      .TYPE_USE 등이 있음
 *
 * @Retention
 * 실제로 적용되고 유지되는 범위를 의미
 * RetentionPolicy
 *          .RUNTIME    -> 컴파일 이후에도 JVM에 의해 계속 참조, 리플렉션이나 로깅에 많이 사용됨
 *          .CLASS      -> 컴파일러가 클래스를 참조할 때 까지 유지
 *          .SOURCE     -> 컴파일 전까지만 유지, 이후에는 사라짐
 *
 * @Constraint
 * TelephoneValidator와 매핑하는 작업을 수행
 *
 * message() : 유효성 검사가 실패할 경우 반환되는 메시지를 의미.
 * groups() : 유효성 검사를 사용하는 그룹으로 설정.
 * payload() : 사용자가 추가 정보를 위해 전달하는 값
 *
 */

@Target(ElementType.FIELD)
@Retention(RetentionPolicy.RUNTIME)
@Constraint(validatedBy = TelephoneValidator.class)
public @interface Telephone {

    String message() default "전화번호 형식이 일치하지 않습니다.";
    Class[] groups() default {};
    Class[] payload() default {};


}
