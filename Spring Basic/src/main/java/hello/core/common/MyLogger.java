package hello.core.common;

import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Component;

import javax.annotation.PostConstruct;
import javax.annotation.PreDestroy;
import java.util.UUID;

@Component
@Scope(value = "request")
public class MyLogger {

    private String uuid;
    private String requestURL;

    public void setRequestURL(String requestURL) {
        this.requestURL = requestURL;
    }

    public void log(String message){
        System.out.println("["+uuid+"]"+"["+requestURL+"] : "+message);
    }

    @PostConstruct
    public void init(){
        String uuid = UUID.randomUUID().toString(); //UUID라는것으로 절대 겹치지 않는 id부여
        System.out.println("["+uuid+"] request scope bean create: " + this );   //this그냥쓰면 이거주소 반환
    }

    @PreDestroy
    public void close(){
        System.out.println("["+uuid+"] request scope bean close: " + this );   //this그냥쓰면 이거주소 반환
    }
}
