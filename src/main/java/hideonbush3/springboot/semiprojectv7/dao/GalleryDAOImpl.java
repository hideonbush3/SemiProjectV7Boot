package hideonbush3.springboot.semiprojectv7.dao;

import hideonbush3.springboot.semiprojectv7.model.Board;
import hideonbush3.springboot.semiprojectv7.model.GalAttach;
import hideonbush3.springboot.semiprojectv7.model.Gallery;
import hideonbush3.springboot.semiprojectv7.repository.GalleryRepository;
import hideonbush3.springboot.semiprojectv7.repository.GalleryaRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Repository;

import java.util.HashMap;
import java.util.Map;

@Repository("galdao")
public class GalleryDAOImpl implements GalleryDAO{

    private final GalleryRepository galleryRepository;

    private final GalleryaRepository galleryaRepository;

    // 생성자를 이용한 스프링 빈 주입
    @Autowired
    public GalleryDAOImpl(GalleryRepository galleryRepository, GalleryaRepository galleryaRepository) {
        this.galleryRepository = galleryRepository;
        this.galleryaRepository = galleryaRepository;
    }

    @Override
    public int insertGal(Gallery gallery) {
        return Math.toIntExact(galleryRepository.save(gallery).getGno());
    }

    @Override
    public int insertGalAttach(GalAttach ga) {
        return Math.toIntExact(galleryaRepository.save(ga).getGano());
    }

    @Override
    public Map<String, Object> selectGallery(Integer cpg) {

        Pageable paging = PageRequest.of(cpg, 25, Sort.by("gno").descending());


        Map<String, Object> gals = new HashMap<>();
        // Gallery와 GalAttach를 조인해서 리스트로 가져옴
        gals.put("gallist", galleryaRepository.findAllBy(paging).getContent());
        gals.put("cntpg",galleryaRepository.findAllBy(paging).getTotalPages());
        return gals;
    }
}
