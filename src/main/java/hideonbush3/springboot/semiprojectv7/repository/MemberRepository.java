package hideonbush3.springboot.semiprojectv7.repository;

import hideonbush3.springboot.semiprojectv7.model.Member;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface MemberRepository
        extends JpaRepository<Member, Long> {

    List<Member> findAll();
    Member findMemberByMbno(Long mbno);
    Member findByUseridAndPasswd(String userid, String passwd);
}
