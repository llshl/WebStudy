package com.example.BootStrap_AdminTest.domain;

import javax.persistence.*;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

@Entity
@Table(name = "ORDERS")
public class Order {

    @Id @GeneratedValue
    @Column(name = "ORDER_ID")
    private Long id;

    //Member와 Order(주인)
    @ManyToOne  //주문입장에선 멤버가 1
    @JoinColumn(name = "MEMBER_ID") //FK쪽이 주인이므로 여기가 주인("다"쪽이 주인)
    private Member member;

    //OrderItem(주인)과 Order
    @OneToMany(mappedBy = "order")  //이렇게 하면 양방향연관관계인데 권장하지 않음
    private List<OrderItem> orderItems = new ArrayList<>();

    private LocalDateTime orderDate;
    
    @Enumerated(EnumType.STRING)    //String으로 하는게 좋다, ORDER, CANCEL 문자 자체로 디비로 저장하는게 좋다
    private OrderStatus status;

    @OneToOne
    @JoinColumn(name = "DELIVERY_ID")
    private Delivery delivery;


    //===============================================================================
    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Member getMember() {
        return member;
    }

    public void setMember(Member member) {
        this.member = member;
    }

    public LocalDateTime getOrderDate() {
        return orderDate;
    }

    public void setOrderDate(LocalDateTime orderDate) {
        this.orderDate = orderDate;
    }

    public OrderStatus getStatus() {
        return status;
    }

    public void setStatus(OrderStatus status) {
        this.status = status;
    }
}
