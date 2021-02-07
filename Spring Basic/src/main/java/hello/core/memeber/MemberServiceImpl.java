package hello.core.memeber;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

//인터페이스를 상속받는 구현체클래스가 한개뿐이라면 이름뒤에 Impl 붙이는게 관례
@Component
public class MemberServiceImpl implements MemberService{

    //private final MemberRepository memberRepository = new MemorymemberRepository();   //DIP위반(인터페이스만 의존해야하는데 그러지 못했다)
    private final MemberRepository memberRepository;

    @Autowired  //ac.getBean(MemberRepository.class)과 같은 역할
    public MemberServiceImpl(MemberRepository memberRepository) {
        this.memberRepository = memberRepository;
    }

    @Override
    public void join(Member member) {
        memberRepository.save(member);
    }

    @Override
    public Member findMember(Long memberId) {
        return memberRepository.findById(memberId);
    }

    //테스트용도
    public MemberRepository getMemberRepository(){
        return memberRepository;
    }
}
