package hideonbush3.springboot.semiprojectv7.repository;

import hideonbush3.springboot.semiprojectv7.model.PdsReply;
import org.springframework.data.jpa.repository.JpaRepository;


import java.util.List;

public interface PdsReplyRepository extends JpaRepository<PdsReply, Long> {
    List<PdsReply> findByPnoOrderByRefnoAscRegdateAsc(Long pno);
}
