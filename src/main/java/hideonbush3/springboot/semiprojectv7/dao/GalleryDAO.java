package hideonbush3.springboot.semiprojectv7.dao;

import hideonbush3.springboot.semiprojectv7.model.GalAttach;
import hideonbush3.springboot.semiprojectv7.model.Gallery;

import java.util.Map;

public interface GalleryDAO {
    int insertGal(Gallery gallery);

    int insertGalAttach(GalAttach ga);

    Map<String, Object> selectGallery(Integer cpg);
}
