package hello.core.memeber;

public interface MemberService {

    //기능정의
    void join(Member member);
    Member findMember(Long memberId);
}
