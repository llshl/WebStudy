package jpabook.jpashop.controller;

import jpabook.jpashop.domain.Member;
import jpabook.jpashop.domain.Order;
import jpabook.jpashop.domain.item.Item;
import jpabook.jpashop.repository.OrderSearch;
import jpabook.jpashop.service.ItemService;
import jpabook.jpashop.service.MemberService;
import jpabook.jpashop.service.OrderService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Controller
@RequiredArgsConstructor
public class OrderController {
    private final OrderService orderService;
    private final MemberService memberService;
    private final ItemService itemService;

    @GetMapping("/order")
    public String createForm(Model model) {
        List<Member> members = memberService.findMembers(); //상품주문시 어떤 회원으로 주문할지 고르기위해 모든 멤버들을 가져온다
        List<Item> items = itemService.findItems();     //상품주문시 어떤 상품을 주문할지 고르기위해 모든 상품들을 가져온다

        model.addAttribute("members", members);
        model.addAttribute("items", items);

        return "order/orderForm";
    }

    @PostMapping(value = "/order")
    public String order(@RequestParam("memberId") Long memberId, @RequestParam("itemId") Long itemId, @RequestParam("count") int count) {
        //orderForm에서 사용자가 입력한 정보들을 받아온다
        orderService.order(memberId, itemId, count);    //상용자가 입력한 정보들을 order메서드에 넣어서 주문을 만들어준다
        return "redirect:/orders";
    }

    @GetMapping("/orders")
    public String orderList(@ModelAttribute("orderSearch") OrderSearch orderSearch, Model model) {
                            //orderSearch는 orderList.html에서 사용자가 검색창에 입력한 내용을 받아오는것이다
        List<Order> orders = orderService.findOrders(orderSearch);  //바로 디비에 접근(리포지토리로)해도되지만 
                                                                    // 전체적인 코드가 다 컨트롤러->서비스->리포지토리 이므로 
                                                                    //여기서도 마찬가지로 서비스를 경유해서 리포지토리(디비)접근
        model.addAttribute("orders", orders);
        // model.addAttribute("orderSearch", orderSearch);  //이 코드는 생략해도됨 자동으로 model에 담기기때문
        return "order/orderList";
    }

    @PostMapping(value = "/orders/{orderId}/cancel")    //데이터를 조회하는게 아닌 상태변화(주문취소)를 주는것이기때문에 post메핑을 한다
    public String cancelOrder(@PathVariable("orderId") Long orderId) {
        orderService.cancelOrder(orderId);
        return "redirect:/orders";
    }
}
