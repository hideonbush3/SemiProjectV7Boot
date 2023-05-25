package hideonbush3.springboot.semiprojectv7.service;

import hideonbush3.springboot.semiprojectv7.model.Pds;
import hideonbush3.springboot.semiprojectv7.model.PdsAttach;
import org.springframework.core.io.UrlResource;
import org.springframework.http.HttpHeaders;
import org.springframework.web.multipart.MultipartFile;

import java.util.Map;

public interface PdsService {
    Map<String, Object> newPds(Pds pds);
    boolean newPdsAttach(MultipartFile attach, Map<String, Object> pno);

    Map<String, Object> readPds(int cpg);

    Pds readOnePds(int pno);

    PdsAttach readOnePdsAttach(int pno);

    HttpHeaders getHeader(String fname, String uuid);

    UrlResource getResource(String fname, String uuid);
}
