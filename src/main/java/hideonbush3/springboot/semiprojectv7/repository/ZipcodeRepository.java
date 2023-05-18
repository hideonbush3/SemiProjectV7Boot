package hideonbush3.springboot.semiprojectv7.repository;

import hideonbush3.springboot.semiprojectv7.model.Zipcode;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface ZipcodeRepository
        extends JpaRepository<Zipcode, Long> {

    // 메서드 쿼리 : 메서드를 정해주면 나중에 자동으로 sql문으로 변환됨
    // 주로 select 쿼리에 최적화돼있음
    // 작성법 - find엔티티명by컬럼명
    // select * from ZIPCODE - findZipcodebyZipcodeAll

    @Query("from Zipcode where dong like %:dong%")
    List<Zipcode> findZipcodeByDong(@Param("dong") String dong);
}
