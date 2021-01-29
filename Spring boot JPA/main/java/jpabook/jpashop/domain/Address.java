package jpabook.jpashop.domain;

import lombok.Getter;

import javax.persistence.Embeddable;

@Embeddable //어딘가에 내장이 될수 있다, 즉 이 클래스의 객체가 다른 클래스의 멤버변수로로 들어갈수있다는말
@Getter
public class Address {

    private String city;
    private String street;
    private String zipcode;

    protected Address(){    //jpa스펙상 아무것도 없는 생성자를 만들어야한다. public으로하면 막 접근할수있으니까 프로텍티드로

    }

    public Address(String city, String street, String zipcode) {    //setter대신에 생성자로 초기화해야한다. 한번 초기화되면 중간에 다시 바꿀수없도록
        this.city = city;
        this.street = street;
        this.zipcode = zipcode;
    }
}
