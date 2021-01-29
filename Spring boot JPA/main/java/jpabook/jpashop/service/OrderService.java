package jpabook.jpashop.service;

import jpabook.jpashop.domain.Delivery;
import jpabook.jpashop.domain.Member;
import jpabook.jpashop.domain.Order;
import jpabook.jpashop.domain.OrderItem;
import jpabook.jpashop.domain.item.Item;
import jpabook.jpashop.repository.ItemRepository;
import jpabook.jpashop.repository.MemberRepository;
import jpabook.jpashop.repository.OrderRepository;
import jpabook.jpashop.repository.OrderSearch;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@Transactional(readOnly = true)
@RequiredArgsConstructor
public class OrderService {

    private final OrderRepository orderRepository;
    private final MemberRepository memberRepository;
    private final ItemRepository itemRepository;

    /*
    * 주문하기
    * */
    @Transactional  //데이터를 변경하는것이기에 트랜잭셔널을 다시 선언하여 이 메서드에서만 리드온니 false로 설정한다
    public Long order(Long memberId, Long itemId, int count){   //createOrderItem과 createOrder가 중요
        //엔티티조회
        Member member = memberRepository.findOne(memberId);
        Item item = itemRepository.findOne(itemId);

        //배송정보 생성
        Delivery delivery = new Delivery();
        delivery.setAddress(member.getAddress());

        //주문상품 생성
        OrderItem orderItem = OrderItem.createOrderItem(item, item.getPrice(), count);//만든 메서드로 주문상품을 생성

        //주문생성
        Order order = Order.createOrder(member, delivery, orderItem);

        //주문 저장
        orderRepository.save(order);    //Order의 Cascade속성으로 인해 이거 하나만 사용해도된다
        return order.getId();
    }

    /*
    * 취소하기
    * */
    @Transactional  //얘도 수정하는 메서드기때문에 리드온니 false로 다시선언
    public void cancelOrder(Long orderId) {
        //주문 엔티티 조회
        Order order = orderRepository.findOne(orderId);
        //주문 조회
        order.cancel();
    }

    //주문검색하기
    public List<Order> findOrders(OrderSearch orderSearch) {
        return orderRepository.findAll(orderSearch);        //findAll이거는 그 크리테리아방식이랑 스트링방식 두가지로 했던 동적쿼리생성하는 메서드
    }
    
}
