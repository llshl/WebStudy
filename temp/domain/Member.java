package com.example.BootStrap_AdminTest.domain;

import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;
import java.sql.Blob;
import java.util.ArrayList;
import java.util.List;

//강의자료 4장 42페이지 43페이지를 만들기
@Entity
@Getter @Setter
public class Member {

    @GeneratedValue
    @Column(name = "MEMBER_ID") //name = "MEMBER_ID"는 이 이름으로 컬럼생성, @Column(length = 10)이라고하면 10자 미만으로 제한두기
    private Long id;
    @Id
    private String loginId; //회원가입시 아이디비번 조회를 위해 이걸 기본키로 지정했다. 기본키로 지정 안하면 em.find에 넣을수가 없더라
    private String name;    
    private String email;    
    private String tel;
    private String password;

//    @Lob
//    @Column(name = "PICTURE")
//    private Blob pic;

//    @Lob
//    @Column(name = "PICTURE")
//    private byte[] pic;

    @Lob
    private String pic;

    //Member 와 Order(주인), 이런식의 양방향 연관관계는 권장되지 않음, 앵간하면 다 단방향이 좋다
    @OneToMany(mappedBy = "member") //연관관계의 주인을 Order의 member로 지정
    private List<Order> orders = new ArrayList<>();

}
