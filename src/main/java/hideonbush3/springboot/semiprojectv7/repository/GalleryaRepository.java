package hideonbush3.springboot.semiprojectv7.repository;

import hideonbush3.springboot.semiprojectv7.model.GalAttach;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;

public interface GalleryaRepository extends JpaRepository<GalAttach, Long> {

}
