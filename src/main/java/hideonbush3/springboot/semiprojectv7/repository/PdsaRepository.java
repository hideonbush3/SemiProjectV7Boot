package hideonbush3.springboot.semiprojectv7.repository;

import hideonbush3.springboot.semiprojectv7.model.PdsAttach;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

public interface PdsaRepository extends JpaRepository<PdsAttach, Long> {
    PdsAttach findByPno(int pno);

    @Transactional@Modifying
    @Query("update PdsAttach set fdown = fdown + 1 where pno = :pno")
    void countDownById(@Param("pno") int pno);

    // 파일확장자만 따로 조회해서 리스트에 저장
    @Query(value = "select ftype from pdsattach order by pno desc", nativeQuery = true)
    List<String> findByFtypes();
}
