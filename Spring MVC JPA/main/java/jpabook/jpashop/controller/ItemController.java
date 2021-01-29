package jpabook.jpashop.controller;

import jpabook.jpashop.domain.item.Book;
import jpabook.jpashop.domain.item.Item;
import jpabook.jpashop.service.ItemService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;

import java.util.List;

@Controller
@RequiredArgsConstructor
public class ItemController {

    private final ItemService itemService;

    @GetMapping("/items/new")
    public String createForm(Model model) {
        model.addAttribute("form", new BookForm());
        return "items/createItemForm";
    }

    @PostMapping("items/new")
    public String create(BookForm form) {
        Book book = new Book();         //책 생성
        book.setName(form.getName());
        book.setStockQuantity(form.getStockQuantity());
        book.setPrice(form.getPrice());
        book.setAuthor(form.getAuthor());
        book.setIsbn(form.getIsbn());

        itemService.saveItem(book); //책 저장
        return "redirect:/";
    }

    @GetMapping("/items")
    public String lisy(Model model) {
        List<Item> items = itemService.findItems();
        model.addAttribute("items", items);
        return "items/itemList";
    }

    //상품 수정 폼
    @GetMapping("items/{itemId}/edit")  //itemId는 변하기때문에
    public String updateItemForm(@PathVariable("itemId") Long itemId, Model model) {
        Book item = (Book)itemService.findOne(itemId);  //캐스팅

        BookForm form = new BookForm();
        form.setId(item.getId());
        form.setName(item.getName());
        form.setPrice(item.getPrice());
        form.setStockQuantity(item.getStockQuantity());
        form.setAuthor(item.getAuthor());
        form.setIsbn(item.getIsbn());

        model.addAttribute("form", form);   //model.addAttribute("form", item);이렇게 그냥 엔티티 자체를 넘겨도 되긴하지만
                                                        //form과 엔티티의 데이터가 맞지 않을수가 있으므로 새로운form이라는걸 만들어서 필요한 정보를 넣어서 model로 전달한다
        return "items/updateItemForm";
    }

    //상품 수정
    @PostMapping(value = "/items/{itemId}/edit")
    public String updateItem(@PathVariable("itemId") Long itemId, @ModelAttribute("form") BookForm form) {

    /*
        //이렇게 해도 되긴하는데 saveItem을 사용하면 결과적으로 merge를 사용하게 된다. 이건 좀 위험하다. 그러므로 변경기능감지 방법으로 하자
        Book book = new Book();     //여기처럼 컨트롤러에서 어설프게 엔티티를 생성하지 말자
        book.setId(form.getId());
        book.setName(form.getName());
        book.setPrice(form.getPrice());
        book.setStockQuantity(form.getStockQuantity());
        book.setAuthor(form.getAuthor());
        book.setIsbn(form.getIsbn());
        itemService.saveItem(book);
     */
        //이게 Itemservice에 있는 변경기능감지방법을 사용하는것
        //form.뭐뭐는 html폼에서 들어온 값들이다. 이거를 넘겨서 처리
        itemService.updateItem(itemId, form.getName(), form.getPrice(), form.getStockQuantity());
        return "redirect:/items";
    }
}
