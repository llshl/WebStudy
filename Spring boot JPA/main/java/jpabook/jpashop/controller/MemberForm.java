package jpabook.jpashop.controller;

import lombok.Getter;
import lombok.Setter;

import javax.validation.constraints.NotEmpty;

@Getter @Setter
public class MemberForm {
    @NotEmpty(message = "회원 이름은 필수입니다.")    //name은 필수로 입력받아야 한다, 만약 지키지 않으면 홈페이지에서 이 문구 출력됨
    private String name;    

    private String city;
    private String street;
    private String zipcode;
}
