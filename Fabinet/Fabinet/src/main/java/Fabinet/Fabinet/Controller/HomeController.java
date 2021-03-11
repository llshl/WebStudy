package Fabinet.Fabinet.Controller;

import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@Slf4j
public class HomeController {
    @RequestMapping("/")    //첫 화면
    public String home(){
        log.info("home controller");
        return "index";  //home.html 템플릿으로 찾아감
    }
}
