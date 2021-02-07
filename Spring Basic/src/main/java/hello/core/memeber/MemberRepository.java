package hello.core.memeber;

public interface MemberRepository {

    //기능정의
    void save(Member member);
    Member findById(Long memberId);
}
