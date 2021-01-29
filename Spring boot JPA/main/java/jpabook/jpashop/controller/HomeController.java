package jpabook.jpashop.controller;

import com.p6spy.engine.common.Loggable;
import lombok.extern.slf4j.Slf4j;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@Slf4j  //로그출력 어노테이션
public class HomeController {

    @RequestMapping("/")    //첫 화면
    public String home(){
        log.info("home controller");
        return "home";  //home.html 템플릿으로 찾아감
    }
}
