package hello.core.web;

import hello.core.common.MyLogger;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.ObjectProvider;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.http.HttpServletRequest;

@Controller
@RequiredArgsConstructor
public class LogDemoController {

    private final LogDemoService logDemoService;
    private final ObjectProvider<MyLogger> myLoggerProvider;

    @RequestMapping("log-demo")
    @ResponseBody   //뷰 템플릿이 없어서 이거 사용
    public String logDemo(HttpServletRequest request){  //HttpServletRequest는  HTTP 리퀘스트 정보륿 받을수있따(요청고객의 고객정보)
        MyLogger myLogger = myLoggerProvider.getObject();
        String requestURL = request.getRequestURL().toString(); //어떤 url로 요쳥했는지
        myLogger.setRequestURL(requestURL); //myLogger에 고객 요청으로 들어온 url을 저장

        myLogger.log("controller test");
        logDemoService.logic("testId");
        return "OK";
    }
}
