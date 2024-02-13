package com.springboot.relationship.data.entity;

import jakarta.persistence.*;
import lombok.*;

@Entity
@Table(name = "product_detail")
@Getter
@Setter
@NoArgsConstructor
@ToString(callSuper = true)
@EqualsAndHashCode(callSuper = true)
public class ProductDetail extends BaseEntity{

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String description;

    //일대일 매핑
    /*
    *   JoinColumn 속성
    *   name                    - 매핑할 외래키 이름 설정
    *   referencedColumnName    - 외래키가 참조할 상대 테이블 컬럼명 지정
    *   foreignKey              - 외래키 생성하면서 지정할 제약조건 설정(unique, nullable, insertable, updatable 등)
    */
    @OneToOne
    @JoinColumn(name = "product_number")
    private Product product;
}
