package hideonbush3.springboot.semiprojectv7;

import hideonbush3.springboot.semiprojectv7.model.Member;
import hideonbush3.springboot.semiprojectv7.repository.MemberRepository;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.time.LocalDateTime;
import java.util.List;

@SpringBootTest
public class MemberTest {
    @Autowired
    MemberRepository memberRepository;

    @Test
    @DisplayName("모든 회원 조회")
    public void findAllMember(){
        List<Member> mbs = memberRepository.findAllMember();
        System.out.println(mbs);
    }

    @Test
    @DisplayName("회원 추가")
    public void saveMember(){
        LocalDateTime localDateTime = LocalDateTime.now();
        Member m = new Member(null, "김득열", "950508", "1234567", "hideonbush3", "hideonbush3"
        , "123-456", "서울특별시 강남구 논현동", "타워팰리스 1701호", "ppoii0961@naver.com"
        , "010-8238-1170", localDateTime);


        memberRepository.save(m);
    }

    @Test
    @DisplayName("회원정보수정")
    public void updateMember(){
        Member m = new Member(5L, "한소희", "950508", "2114454", "sohee95", "sohee95"
                , "123-456", "서울특별시 강남구 논현동", "타워팰리스 1701호", "sohee95@naver.com"
                , "010-8238-1170", null);
        memberRepository.save(m);
    }

    @Test
    @DisplayName("회원정보삭제")
        public void deleteMember(){
        Member m = new Member();
        m.setMbno(2L);

        memberRepository.delete(m);
        }

}
