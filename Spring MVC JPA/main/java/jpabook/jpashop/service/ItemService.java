package jpabook.jpashop.service;

import jpabook.jpashop.domain.item.Book;
import jpabook.jpashop.domain.item.Item;
import jpabook.jpashop.repository.ItemRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@Transactional(readOnly = true)
@RequiredArgsConstructor                                            //컨트롤러 -> 서비스 -> 리포지토리
public class ItemService {      //그저 ItemRepository의 메서드를 통해 위임만 하는 클래스다, 컨트롤러에서 리포지토리에 바로 접근해서 메서드 사용해도 되긴 됨

    private final ItemRepository itemRepository;

    @Transactional  //위에 전체클래스에 트랜섹셔널이 readOnly로 돼있어서 저장이 안된다. 이 메서드만 예외로 하기위해 한버더 트랜섹셔널 어노테이션 붙임
    public void saveItem(Item item){
        itemRepository.save(item);
    }


    //이 메서드가 merge와 같은 역할, merge는 수정하기로 설정한 값이 없는것은 null되는 반면 이렇게하면 수정값 설정하지 않은애는 수정하지 않는다
    @Transactional  //트랜섹션이 커밋되면 flush실행(더티체킹) 후  변경사항이 감지되어 DB로 update쿼리보내진다. 이게 변경감지기능사용
    public void updateItem(Long itemId, String name, int price, int stockQuantity) {
        Item findItem = itemRepository.findOne(itemId); //해당 item의 id를 찾음
        findItem.setName(name);
        findItem.setPrice(price);
        findItem.setStockQuantity(stockQuantity);
    }


    public List<Item> findItems(){
        return itemRepository.findAll();
    }

    public Item findOne(Long itemId){
        return itemRepository.findOne(itemId);
    }
}
