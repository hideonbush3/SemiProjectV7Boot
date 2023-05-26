package hideonbush3.springboot.semiprojectv7.repository;

import hideonbush3.springboot.semiprojectv7.model.PdsReply;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.transaction.annotation.Transactional;


import java.util.List;

public interface PdsReplyRepository extends JpaRepository<PdsReply, Long> {
    List<PdsReply> findByPnoOrderByRefnoAscRegdateAsc(Long pno);

    @Modifying@Transactional
    @Query(value = "update pdsreply set refno = :rpno where rpno = :rpno", nativeQuery = true)
    void updateRefno(@Param("rpno") Long rpno);
}
