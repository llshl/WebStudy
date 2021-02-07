package hello.core.singletonTest;

public class SingletonService {

    //static으로 자기자신을 생성했기에 이 객체를 공유하게된다
    private static final SingletonService instance = new SingletonService();
    
    //이 객체 인스턴스가 필요하면 이 메서드를 통해서만 조회하도록 한다
    public static SingletonService getInstance(){
        return instance;
    }
    
    //생성자를 private으로 만들어서 외부에서 새로 new 하지 못하도록 한다
    private SingletonService(){
    }
    
    public void logic(){
        System.out.println("싱글톤 객체 호출");
    }
}
