package hideonbush3.springboot.semiprojectv7.dao;

import hideonbush3.springboot.semiprojectv7.model.GalAttach;
import hideonbush3.springboot.semiprojectv7.model.Gallery;
import org.springframework.stereotype.Repository;

@Repository("galdao")
public class GalleryDAOImpl implements GalleryDAO{
    @Override
    public int insertGal(Gallery gallery) {
        return 0;
    }

    @Override
    public int insertGalAttach(GalAttach ga) {
        return 0;
    }
}
