package jpabook.jpashop.repository;

import jpabook.jpashop.domain.item.Item;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Repository;

import javax.persistence.EntityManager;
import java.util.List;

@Repository
@RequiredArgsConstructor
public class ItemRepository {

    private final EntityManager em;

    public void save(Item item) {
        if(item.getId() == null){   //아이템은 디비에 저장하기 전에는 id가 없다
            em.persist(item);
        }else{  //아이템값이 있다면 이미 디비에 등록돼있다는것. 그리고 그 등록된 아이디가 가져와져있다는것
            em.merge(item); //ItemService에 있는 코드와 같은역할, 단!! 수정 할 모든 새로운 값을 설정하지 않으면 새로 설정 안한 값들은 null로 업데이트된다
                            //변경사항감지방법은 새롭게 바꿀 값만 바뀌는 반면 얘는 모든게 바뀜, 새롭게 바꿀 값 안넣으면 null된다는 뜻
        }
    }

    //아이템 하나 조회
    public Item findOne(Long id){
        return em.find(Item.class, id);
    }

    public List<Item> findAll(){
        return em.createQuery("select i from Item i", Item.class).getResultList();  //전체조회는 jpql작성해야한다
    }
}
