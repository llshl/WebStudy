package jpabook.jpashop.domain;

import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

@Entity //엔티티로설정, 엔티티란 하나의 데이터 집합체, 쇼핑몰에선 주문, 회원, 배송 등등 ,이 어노테이션이 붙으면 JPA가 관리한다
@Getter @Setter
public class Member {

    @Id @GeneratedValue     //@Id는 JPA가 객체를 관리할대 식별할 기본키를 지정한것
    @Column(name = "member_id")     //객체필드(여기의 id)와 DB테이블의 컬럼(member_id)를 매핑
    private Long id;

    private String name;

    @Embedded   //내장타입이다. 즉 Embeddable인 클래스의 객체를 사용했다 이말이다. 즉 다른클래스의 객체를 사용했다 이말
    private Address address;

    @OneToMany(mappedBy = "member")  //멤버:오더 = 일:다       (오더클래스에서와 반대) 오더테이블에있는 멤버필드에 의해서 매핑이 됏단소리
    private List<Order> orders = new ArrayList<>();

}
