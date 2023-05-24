package hideonbush3.springboot.semiprojectv7;

import hideonbush3.springboot.semiprojectv7.model.Member;
import hideonbush3.springboot.semiprojectv7.repository.MemberRepository;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
public class MemberTest {
    @Autowired
    MemberRepository memberRepository;

    @Test
    @DisplayName("모든 회원 조회")
    public void findAllMember(){
        List<Member> mbs = memberRepository.findAll();
        System.out.println(mbs);
    }



    @Test
    @DisplayName("회원정보수정")
    public void updateMember(){
        Member m = memberRepository.findMemberByMbno(5L);
        m.setName("한조희");

//        Member m = new Member(5L, "한소희", "950508", "2114454", "sohee95", "sohee95"
//                , "123-456", "서울특별시 강남구 논현동", "타워팰리스 1701호", "sohee95@naver.com"
//                , "010-8238-1170", null);
        memberRepository.save(m);
    }

    @Test
    @DisplayName("회원정보삭제")
    public void deleteMember(){
        Member m = new Member();
        m.setMbno(2L);

        memberRepository.delete(m);
    }

    @Test
    @DisplayName("로그인 시도")
    public void loginMember(){
        Member m = new Member();
        m.setUserid("ehgus94");
        m.setPasswd("ehgus94");

        // 로그인 처리 1
        // 실행결과 반환값(Member 객체)가 null이 아니라면(조회 결과가 있다면)
        // 테스트 통과됨
        // 반환값이 null 일 경우 통과되게 하려면 assertNull() 사용
//        assertNotNull(
//                memberRepository.findByUseridAndPasswd(m.getUserid(), m.getPasswd()));

        // 로그인 처리 2 countBy
        assertEquals(1, memberRepository.countByUseridAndPasswd(m.getUserid(), m.getPasswd()));
    }

}
