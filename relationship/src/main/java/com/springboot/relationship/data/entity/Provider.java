package com.springboot.relationship.data.entity;

import jakarta.persistence.*;
import lombok.*;

import java.util.ArrayList;
import java.util.List;

@Entity
@Getter
@Setter
@NoArgsConstructor
@ToString(callSuper = true)
@EqualsAndHashCode(callSuper = true)
@Table(name = "provider")
public class Provider extends BaseEntity{

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String name;

    //다대일 양방향 매핑
    @OneToMany(mappedBy = "provider", fetch = FetchType.EAGER)
    @ToString.Exclude
    private List<Product> productList = new ArrayList<>();

}
