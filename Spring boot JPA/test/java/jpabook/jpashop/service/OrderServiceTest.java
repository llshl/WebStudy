package jpabook.jpashop.service;

import jpabook.jpashop.domain.Address;
import jpabook.jpashop.domain.Member;
import jpabook.jpashop.domain.Order;
import jpabook.jpashop.domain.OrderStatus;
import jpabook.jpashop.domain.item.Book;
import jpabook.jpashop.domain.item.Item;
import jpabook.jpashop.exception.NotEnoughStockException;
import jpabook.jpashop.repository.OrderRepository;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.transaction.annotation.Transactional;

import javax.persistence.EntityManager;

import static org.junit.Assert.*;

@RunWith(SpringRunner.class)
@SpringBootTest
@Transactional
public class OrderServiceTest {

    @Autowired
    EntityManager em;

    @Autowired
    OrderService orderService;

    @Autowired
    OrderRepository orderRepository;

    @Test
    public void 상품주문() throws Exception{
        //given
        Member member = creatMember();  //이거랑
        Book book = createBook("시골 JPA", 10000, 10);  //이거는
                                        //여기 맨 밑에 빼두었다. 코드가 길어지고 다른 테스트케이스에서도 가져다 쓸수있도록.
                                        // 그저 멤버랑 책 객체 만들어서 persist하는 메서드다

        int orderCount = 2; //책 두권만 주문할거다라고 가정
        
        //when
        Long orderId = orderService.order(member.getId(), book.getId(), orderCount);//책 주문했을때
                                            //주문자Id ,   상품Id    ,   수량
        //then
        Order getOrder = orderRepository.findOne(orderId);  //주문목록에서 주문이 잘 들어갔는지 확인(상태가 ORDER인지 확인)
        assertEquals("상품 주문시 상태는 ORDER", OrderStatus.ORDER, getOrder.getStatus());
                                    //(message, 기대값, 실제값)   -> 기대값과 실제값이 같으면 테스트 통과
        assertEquals("주문한 상품 종류 수가 정확해야 한다", 1,getOrder.getOrderItems().size());
        assertEquals("주문 가격은 가격 * 수량이다", 10000 * orderCount, getOrder.getTotalPrice());
        assertEquals("주문 수량만큼 재고가 줄어야한다.",8, book.getStockQuantity());  //10권에서 2권주문했으니 8권이 예상됨
    }

    @Test(expected = NotEnoughStockException.class) //우리가 만든 이 예외가 발생해야한다. 그래야 테스트 통과
    public void 상품주문_재고수량초과() throws Exception{
        //given
        Member member = creatMember();
        Item item = createBook("시골 JPA",10000,10);
        int orderCount = 11;    //수량은 10개인데 주문수량은 11개다

        //when
        orderService.order(member.getId(), item.getId(), orderCount);

        //then
        fail("재고 수량 부족 예외가 발생해야한다");    //이 코드가 실행된다는것은 위에서 예외가 터지지 않았고 즉 테스트 실패
    }

    @Test
    public void 주문취소() throws Exception{
        //given
        Member member = creatMember();
        Book item = createBook("시골 JPA", 10000,10);
        int orderCount = 2; //2권 주문할거다
        Long orderId = orderService.order(member.getId(), item.getId(), orderCount);//멤버이름과 상품이름과 수량을 넣어서 주문을 만듬,
                                                                                    // 즉 주문이 주어졌을때

        //when
        orderService.cancelOrder(orderId);      //주문을 취소했다면

        //then
        Order getOrder = orderRepository.findOne(orderId);  //주문 들어간 상품의 id얻기

        assertEquals("주문 취소시 상태는 CANCEL이다", OrderStatus.CANCEL, getOrder.getStatus());
        assertEquals("주문이 취소된 상품은 재고가 증가해야 한다", 10, item.getStockQuantity());
    }



    //===============================================
    //멤버와 책 생성 메서드
    private Book createBook(String 시골_jpa, int price, int stockQuantity) {
        Book book = new Book();         //책 정보가 주어지고
        book.setName(시골_jpa);
        book.setPrice(price);
        book.setStockQuantity(stockQuantity);
        em.persist(book);
        return book;
    }

    private Member creatMember() {
        Member member = new Member();   //멤버정보와
        member.setName("회원1");
        member.setAddress(new Address("서울","도산대로","123-123"));
        em.persist(member);
        return member;
    }
}