package jpabook.jpashop.domain;

import jpabook.jpashop.domain.item.Item;
import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;

@Entity
@Getter @Setter
public class OrderItem {

    @Id @GeneratedValue
    @Column(name = "order_item_id")
    private Long id;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "item_id")
    private Item item;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "order_id")  //외래키매핑할때 사용 , 매핑할 외래키는 order_id
    private Order order;

    private int orderPrice; //주문가격
    private int count;  //주문수량

    //==OrderItem 생성 메서드==//
    //상품의 주문정보
    //왜 상품이 원래 가지고있는 정보들(어떤상품인지, 가격은 어떤지)를 안쓰고 주문시 새롭게 주문정보를 만들어주는 이유는
    //할인과 같이 원래가격에서 변동이 있을 수 있기때문에 초기가격과 주문시 가격을 분리해서 만드는게 맞다
    public static OrderItem createOrderItem(Item item, int orderPrice, int count) {
        OrderItem orderItem = new OrderItem();  //새로운 주문상품을 만들고
        orderItem.setItem(item);                //그 상품의 주문정보를 세팅
        orderItem.setOrderPrice(orderPrice);
        orderItem.setCount(count);

        item.removeStock(count);    //주문한 만큼 상품의 재고를 감소시킨다
        return orderItem;
    }

    //비즈니스 로직//
    public void cancel() {
        getItem().addStock(count);  //취소했을때 수량 원상복구(빠졌던 수량만큼 다시 더해주기)
    }

    //==조회 로직==//
    /*
    * 주문상품 전체 가격조회
    * */
    public int getTotalPrice() {
        return getOrderPrice() * getCount();    //개당 가격 * 개수 , 참고로 getOrserPrice와 같은 메서드가 안적혀있는 이유는 롬복으로 만든것이기 때문
    }
}
