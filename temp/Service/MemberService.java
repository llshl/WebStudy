package com.example.BootStrap_AdminTest.Service;

import com.example.BootStrap_AdminTest.Repository.MemberRepository;
import com.example.BootStrap_AdminTest.domain.Member;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.io.PrintWriter;
import java.util.List;

@Service
@Transactional(readOnly = true) //트랜섹셔널해줘야함
@RequiredArgsConstructor
public class MemberService {

    private final MemberRepository memberRepository;

    @Transactional  //트랜섹셔널해줘야함, (쓰기는 해야하나?)영속성컨텍스트 뭐시기때문에?
    public Long join(Member member){
        //validateDuplicateMember(member) ;//중복회원검증
        memberRepository.save(member);  //그냥 리포지토리의 save로 멤버 넘기면 됨
        return member.getId();
    }

    //DB읽기이기에 트랜색셔널 안해도됨?
    public Member findOne(String loginId) { //loginID는 PK의 id와 다름
        return memberRepository.findOne(loginId);
    }

    public List<Member> findMembers(){
        return  memberRepository.findAll();
    }

    private void validateDuplicateMember(Member member) {
        //중복회원이면 예외발생
//        List<Member> findMembers = memberRepository.findByName(member.getName());   //DB에 해당이름의 멤버가 존재한다면 그걸 리스트에 넣음
//        if(!findMembers.isEmpty()){ //리스트에 뭔가 있다는건 중복회원이라는것
//            throw new IllegalStateException("이미 존재하는 회원입니다.");
//        }
    }

    public String isLoginAvailable(Member member, String id, String pw){
        if(member == null){   //if(member.getLoginId == null)
            return "F-1";   //Fail 1, 해당 아이디 없음
        }
        else if(!member.getPassword().equals(pw)){
            return "F-2";   //Fail 2, 비밀번호 불일치
        }
        else{
            return "S-1";   //정상로그인 완료
        }
    }
}
