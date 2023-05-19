package hideonbush3.springboot.semiprojectv7.repository;

import hideonbush3.springboot.semiprojectv7.model.Member;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface MemberRepository
        extends JpaRepository<Member, Long> {

    List<Member> findAll();
    Member findMemberByMbno(Long mbno);

    // 로그인 처리 1
//    Member findByUseridAndPasswd(String userid, String passwd);

    // 로그인 처리 2
    int countByUseridAndPasswd(String userid, String passwd);
}
