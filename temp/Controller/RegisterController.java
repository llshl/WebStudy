package com.example.BootStrap_AdminTest.Controller;

import com.example.BootStrap_AdminTest.Service.MemberService;
import com.example.BootStrap_AdminTest.domain.Member;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;

@Controller
@RequiredArgsConstructor
@Slf4j
public class RegisterController {

    private final MemberService memberService;

    //회원가입폼으로
    @GetMapping("/createAccount")
    public String toCreateAccount(Model model){
        model.addAttribute("accountForm", new AccountForm());
        log.info("toCreateAccount");
        return "register";
    }

    //회원정보 DB등록
    @PostMapping("/doRegister")    //login페이지에서 login누르면 이게 실행됨, 페이지에서 타임리프로 데이터가 들어간 memberForm객체를 받아옴
    public String doRegister(AccountForm form){

        if(!form.getPassword().equals(form.getPasswordCheck())){
            //비밀번호확인 틀리면 경고
        }

        Member member = new Member();   //신규 회원객체를 만들고
        member.setName(form.getName()); //이름과 주소 설정 후
        member.setLoginId(form.getLoginId());
        member.setEmail(form.getEmail());
        member.setTel(form.getTel());
        member.setPassword(form.getPassword());
        log.info("doRegister");

        memberService.join(member); //저장
        return "redirect:/";    //저장후엔 리로딩되면 좋지 않기때문에 리다이렉트로 홈으로 보냄
    }
}
