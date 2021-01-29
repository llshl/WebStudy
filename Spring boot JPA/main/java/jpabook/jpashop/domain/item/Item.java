package jpabook.jpashop.domain.item;

import jpabook.jpashop.domain.Category;
import jpabook.jpashop.exception.NotEnoughStockException;
import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

@Entity
@Inheritance(strategy = InheritanceType.SINGLE_TABLE)   //상속관계매핑에서는 부모클래스에 상속전략을 지정해줘야함
@DiscriminatorColumn(name = "dtype")    //하위 클래스를 구분하는 용도의 컬럼(dtype이 디폴트), 하위클래스들에서 value를 지정해주면 그 값으로 구분가능, (A,M,B로 했다.
@Getter @Setter
public abstract class Item {    //추상클래스로 한다, 이 클래스를 상속받아서 사용하는 구현클래스를 따로만들어서 사용한다(Book,Album,Movie클래스)

    @Id
    @GeneratedValue
    @Column(name = "item_id")   //객체필드멤버(여기의 id)와 디비의 item_id를 매핑
    private Long id;

    private String name;
    private int price;
    private int stockQuantity;

    @ManyToMany(mappedBy = "items")
    private List<Category> categories = new ArrayList<>();

    //==비즈니스로직==//
    /*
    * 재고수량 증가
    * */
    public void addStock(int quantity){
        this.stockQuantity += quantity;
    }

    public void removeStock(int quantity){
        int restStock = this.stockQuantity - quantity;
        if(restStock < 0){
            throw new NotEnoughStockException("need more stock");   //예외를 우리가 만들어준다.
        }
        this.stockQuantity = restStock;
    }
}
