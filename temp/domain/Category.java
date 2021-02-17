package com.example.BootStrap_AdminTest.domain;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

@Entity
public class Category {

    @Id
    @GeneratedValue
    private Long id;
    private String name;
    private String chuga;

    @ManyToOne
    @JoinColumn(name = "PARENT_ID") //이거랑
    private Category parent;

    @OneToMany(mappedBy = "parent") //이거가 내부적으로 묶임
    private List<Category> child = new ArrayList<>();

    @ManyToMany
    @JoinTable(name = "CATEGORY_ITEM",
            joinColumns = @JoinColumn(name = "CATEGORY_ID"),
            inverseJoinColumns = @JoinColumn(name = "ITEM_IN")  //이렇게 다대다는 하지말자. 중간테이블 하나 만들어서 매핑하자
    )
    private List<Item> items = new ArrayList<>();
}
