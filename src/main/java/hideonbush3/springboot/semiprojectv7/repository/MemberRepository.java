package hideonbush3.springboot.semiprojectv7.repository;

import hideonbush3.springboot.semiprojectv7.model.Member;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface MemberRepository
        extends JpaRepository<Member, Long> {

    @Query("from Member")
    List<Member> findAllMember();

    @Query("from Member where mbno = :mbno")
    Member findMemberByMbno(@Param("mbno") Long mbno);


}
