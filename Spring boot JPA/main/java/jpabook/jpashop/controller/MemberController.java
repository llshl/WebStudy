package jpabook.jpashop.controller;

import jpabook.jpashop.domain.Address;
import jpabook.jpashop.domain.Member;
import jpabook.jpashop.service.MemberService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;

import javax.validation.Valid;
import java.util.List;

@Controller
@RequiredArgsConstructor
public class MemberController {

    private final MemberService memberService;

    @GetMapping("/members/new") //겟매핑은 폼화면을 열어보기용도
    public String createForm(Model model){
        model.addAttribute("memberForm", new MemberForm()); //컨트롤러에서 뷰로 넘어갈때 모델에 데이터를 담아서 넘김
        return "members/createMemberForm";
    }

    @PostMapping("members/new")    //포스트메핑은 데이터를 실제로 등록하기 용도
    public String create(@Valid MemberForm form, BindingResult result){
        //Valid쓰면 MemberForm으로 넘어가서 거기있는 어노테이션을 검증(이 경우 NotEmpty를 검증해서 인자로 받느다)
        //BindingResult란 Valid에서 에러가 발생하면 result에 그 에러를 담고 코드를 실행

        if(result.hasErrors()){ //Valid 에러가 발생했다면
            return "members/createMemberForm";  //다시 createMemberForm으로 이동
        }
        
        Address address = new Address(form.getCity(), form.getStreet(), form.getZipcode());

        Member member = new Member();   //신규 회원객체를 만들고
        member.setName(form.getName()); //이름과 주소 설정 후
        member.setAddress(address);

        memberService.join(member); //저장
        return "redirect:/";    //저장후엔 리로딩되면 좋지 않기때문에 리다이렉트로 홈으로 보냄
    }

    @GetMapping("/members")
    public String list(Model model) {
        List<Member> members = memberService.findMembers(); //컨트롤러단이니 서비스단의 findMember를쓴다(리포지토리단의 findAll을 바로쓰는것이아니라)
        model.addAttribute("members", members);
        return "members/memberList";
    }
}
