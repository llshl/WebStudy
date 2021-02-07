package hello.core.lifeCycle;

import org.springframework.beans.factory.DisposableBean;
import org.springframework.beans.factory.InitializingBean;

import javax.annotation.PostConstruct;
import javax.annotation.PreDestroy;

public class NetworkClient {

    private String url;

    //생성자
    public NetworkClient(){
        System.out.println("생성자 호출, url = "+ url);
        connect();
        call("초기화 연결 메시지");
    }

    public void setUrl(String url) {
        this.url = url;
    }

    //서비스 시작 시 호출
    public void connect(){
        System.out.println("connect: "+url);
    }

    public void call(String message){
        System.out.println("url: "+ url + " message: "+ message);
    }

    //서비스 종료 시 호출
    public void disconnect(){
        System.out.println("close: "+url);
    }
    @PostConstruct
    public void init() { //의존관계주입이 끝나면호출
        System.out.println("NetworkClient.init");
        connect();
        call("초기화 연결 메시지");
    }
    @PreDestroy
    public void close(){    // 빈이 종료될때 호출
        System.out.println("NetworkClient.close");
        disconnect();
    }

/*
    //Bean(init = "inti")이런식으로 지정
    //Bean어노테이션에서 이닛 디스트로이 지정한다
    public void init() { //의존관계주입이 끝나면호출
        System.out.println("NetworkClient.init");
        connect();
        call("초기화 연결 메시지");
    }
    public void close(){    // 빈이 종료될때 호출
        System.out.println("NetworkClient.close");
        disconnect();
    }
*/
    /*
    @Override   //InitializingBean 인터페이스
    public void afterPropertiesSet() throws Exception { //의존관계주입이 끝나면호출
        System.out.println("NetworkClient.afterPropertiesSet");
        connect();
        call("초기화 연결 메시지");
    }

    @Override   //DisposableBean 인터페이스
    public void destroy() throws Exception {    // 빈이 종료될때 호출
        System.out.println("NetworkClient.destroy");
        disconnect();
    }
    */
}
