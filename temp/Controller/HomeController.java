package com.example.BootStrap_AdminTest.Controller;

import com.example.BootStrap_AdminTest.Service.MemberService;
import com.example.BootStrap_AdminTest.domain.Member;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import javax.validation.Valid;

@Controller
@Slf4j
@RequiredArgsConstructor
public class HomeController {

    private final MemberService memberService;

    @RequestMapping("/")  //첫 화면(로그인부터)
    public String home(Model model) {
        model.addAttribute("loginForm", new LoginForm());
        log.info("home controller");
        return "login";  //home.html 템플릿으로 찾아감
    }
}