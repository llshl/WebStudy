package Fabinet.Fabinet.Controller;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

import javax.persistence.Column;

@Controller
@RequiredArgsConstructor
@Slf4j
public class CabinetController {

    //사물함 선택 페이지로
    @GetMapping("/toChooseCabinet")
    public String toCreateAccount(){
        log.info("사물함 선택 페이지로 이동");
        return "cabinet";
    }
}
