package hideonbush3.springboot.semiprojectv7.service;

import hideonbush3.springboot.semiprojectv7.model.Pds;
import hideonbush3.springboot.semiprojectv7.model.PdsAttach;
import org.springframework.web.multipart.MultipartFile;

public interface PdsService {
    int newPds(Pds pds);
    boolean newPdsAttach(MultipartFile attach, int pno);
}
