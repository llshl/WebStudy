package jpabook.jpashop.service;

import jpabook.jpashop.domain.Member;
import jpabook.jpashop.repository.MemberRepository;
import lombok.AllArgsConstructor;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@Transactional(readOnly = true)  //리드온니를 true로 하면 데이터 조회만 가능 //begin, commit을 자동으로 해주고 예외발생시 자동 롤백
//@AllArgsConstructor //롬복에서 제공하는 생성자 자동 생성(getter setter처럼)
@RequiredArgsConstructor    //얘도 롬복 생성자 자동 생성, 단 final키워드 붙은 애만 생성해줌 
public class MemberService {

   /* @Autowired  //자동주입, 이거말고도 setter인젝션, 생성자인젝션도 있다.
    private MemberRepository memberRepository;  
    //Controller -> Service -> Repository -> DB이므로 Service에선 Repository가 필요하다
    */

    private final MemberRepository memberRepository;    //변경할일 없기때문에 final로 
/*
    @Autowired  //생성자 인젝션션, 중간에 set해서 바꿀수 없다(장점임). 테스트케이스 작성 시에는 내가, 그리고 이거처럼 생성자 한개일경우에는 스프링이 알아서 주입해줌 @Autowired없어도됨
    public MemberService(MemberRepository memberRepository) {
        this.memberRepository = memberRepository;
    }
*/

    
    /*
    * 회원가입
    * */
    @Transactional  //얘 처럼 데이터 추가메서드는 트랜젝션에 리드온니를 false로 해야하기때문에 한번더씀
    public Long join(Member member){
        validateDuplicateMember(member);//중복회원검증
        memberRepository.save(member);  //그냥 리포지토리의 save로 멤버 넘기면 됨
        return member.getId();
    }

    private void validateDuplicateMember(Member member) {
        //중복회원이면 예외발생
        List<Member> findMembers = memberRepository.findByName(member.getName());   //DB에 해당이름의 멤버가 존재한다면 그걸 리스트에 넣음
        if(!findMembers.isEmpty()){ //리스트에 뭔가 있다는건 중복회원이라는것
            throw new IllegalStateException("이미 존재하는 회원입니다.");
        }

    }

    //전체회원보기
    //readOnly = true로 돼있기에 읽기전용. 얘네는 읽기 메서드다
    public List<Member> findMembers(){
        return  memberRepository.findAll();
    }

    public Member findOne(Long memberId){
        return memberRepository.findOne(memberId);
    }
}
