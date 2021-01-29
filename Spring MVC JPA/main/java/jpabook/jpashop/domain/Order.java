package jpabook.jpashop.domain;

import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

@Entity
@Table(name = "orders") //이 클래스와 DB의 orders테이블과 매핑
@Getter @Setter
public class Order {
    @Id @GeneratedValue
    @Column(name = "id")
    private Long id;

    @ManyToOne(fetch = FetchType.LAZY)  //오더:멤버 = 다:일
    @JoinColumn(name = "member_id") //member_id로 매핑, 외래키가 member_id가됨
    private Member member;

    @OneToMany(mappedBy = "order", cascade = CascadeType.ALL)  //order에의해서 매핑됨, 캐스케이드옵션 order를 persist하면 다른것도? persist
    private List<OrderItem> orderItems = new ArrayList<>();

    @OneToOne(fetch = FetchType.LAZY, cascade = CascadeType.ALL)
    @JoinColumn(name = "delivery_id")   //Order와 Delivery사이에서 이게 연관관계의 주인이 됨
    private Delivery  delivery;

    private LocalDateTime orderDate;    //주문시간, 자바8에서 알아서 지원해줌 로컬데이트타임은.

    @Enumerated(EnumType.STRING)
    private OrderStatus status;     //ORDER, CANCEL상태 (주문상태,캔슬상태)

    //==연관관계 메서드==//
    public void setMember(Member member) {
        this.member = member;
        member.getOrders().add(this);
    }
    public void addOrderItem(OrderItem orderItem) {
        orderItems.add(orderItem);
        orderItem.setOrder(this);
    }
    public void setDelivery(Delivery delivery) {
        this.delivery = delivery;
        delivery.setOrder(this);
    }

    //==주문 생성 메서드==//
    public static Order createOrder(Member member, Delivery delivery, OrderItem... orderItems){ //varargs문법, 주문상품(orderItems)은 여러개 들어올수있으니까
        Order order = new Order();
        order.setMember(member);
        order.setDelivery(delivery);
        for(OrderItem orderItem : orderItems){
            order.addOrderItem(orderItem);
        }
        order.setStatus(OrderStatus.ORDER); //해당 주문을 Enum의 ORDER상태로 설정
        order.setOrderDate(LocalDateTime.now());
        return order;
    }

    //==비즈니스 로직==//
    /*
    * 주문취소
    * */
    public void cancel(){
        if(delivery.getStatus() == DeliveryStatus.COMP){    //Enum상태가 배송시작이라면
            throw new IllegalStateException("이미 배송이 시작된 상품은 취소가 불가능합니다.");
        }
        this.setStatus(OrderStatus.CANCEL); //위의 if를 통과하면 주문상태를 Cancel로 바꿔주면된다
        for(OrderItem orderItem : orderItems){
            orderItem.cancel(); //주문 취소시 수량 원상복구
        }
    }

    //==조회 로직==//
    /*
    * 전체 주문 가격조회
    * */
    public int getTotalPrice(){
        int totalPrice = 0;
        for(OrderItem orderItem : orderItems){
            totalPrice += orderItem.getTotalPrice();    //OrderItem의 getTotalPrice를 쓰는이유는 OrderItem에 주문수량과 가격이 있기때문 이거 곱해야한다
        }
        return totalPrice;
    }
}
