package hideonbush3.springboot.semiprojectv7.repository;

import hideonbush3.springboot.semiprojectv7.model.Gallery;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;

public interface GalleryRepository extends JpaRepository<Gallery, Long> {
    Page<Object> findAllBy(Pageable paging);
    /*@Modifying
    @Transactional
    @Query("update Gallery set views = views + 1 where gno = :gno")
    void countViewById(@Param("gno") long gno);*/
}
