package hideonbush3.springboot.semiprojectv7.repository;

import hideonbush3.springboot.semiprojectv7.model.Pds;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.transaction.annotation.Transactional;

public interface PdsRepository extends PagingAndSortingRepository<Pds, Long>{

    @Modifying@Transactional
    @Query("update Pds set views = views + 1 where pno = :pno")
    void countViewById(@Param("pno") long pno);
}
