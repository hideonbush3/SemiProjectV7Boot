package hideonbush3.springboot.semiprojectv7.service;

import hideonbush3.springboot.semiprojectv7.dao.PdsDao;
import hideonbush3.springboot.semiprojectv7.model.Pds;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

@Service("pdssrv")
public class PdsServiceImpl implements PdsService{
    @Autowired
    private PdsDao pdsdao;

    @Autowired
    private PdsUtils pdsUtils;

    @Override
    public int newPds(Pds pds) {
        pds.setUuid(pdsUtils.makeUUID());
        return pdsdao.insertPds(pds);
    }

    @Override
    public boolean newPdsAttach(MultipartFile attach, int pno) {
        return true;
    }
}
