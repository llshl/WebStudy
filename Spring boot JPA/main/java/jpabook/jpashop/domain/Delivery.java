package jpabook.jpashop.domain;

import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;

@Entity
@Getter @Setter
public class Delivery {

    @Id @GeneratedValue
    @Column(name = "delivery_id")   //여기있는 id와 디비의 delivery_id와 매핑
    private Long id;

    @OneToOne(mappedBy = "delivery",fetch = FetchType.LAZY)    //Order랑 Delivery사이에서 이게 거울이됨(연관관계 매핑됨)
    private Order order;

    @Embedded
    private Address address;

    @Enumerated(EnumType.STRING)    //ordinary로 하면 숫자로 들어간다. 즉 1일때는 무슨상태 2일때는 무슨상태 이렇게, 근데 이러면 새로운 상태가 추가되면 망한다 그래서 String으로 하자
    private DeliveryStatus status;  //READY, COMP준비상태 배송상태
}
