package hideonbush3.springboot.semiprojectv7.service;

import hideonbush3.springboot.semiprojectv7.dao.PdsDao;
import hideonbush3.springboot.semiprojectv7.model.Pds;
import hideonbush3.springboot.semiprojectv7.model.PdsAttach;
import hideonbush3.springboot.semiprojectv7.model.PdsReply;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.UrlResource;
import org.springframework.http.HttpHeaders;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Service("pdssrv")
public class PdsServiceImpl implements PdsService{
    @Autowired
    private PdsDao pdsdao;

    @Autowired
    private PdsUtils pdsUtils;

    @Override
    public Map<String, Object> newPds(Pds pds) {
        pds.setUuid(pdsUtils.makeUUID());   // 유니크한 문자열 생성
        int pno = pdsdao.insertPds(pds);    // 일반폼 데이터 디비에 저장

        // 첨부파일을 시스템에 저장할때 사용할 정보를 Map에 저장
        Map<String, Object> pinfo = new HashMap<>();
        pinfo.put("pno", pno);
        pinfo.put("uuid", pds.getUuid());
        return pinfo;
    }

    @Override
    public boolean newPdsAttach(
            MultipartFile attach, Map<String, Object> pinfo) {

        // 첨부파일 업로드 처리
        PdsAttach pa = pdsUtils.processUpload(attach, pinfo);
        // 첨부파일 정보 디비에 저장
        int pano = pdsdao.insertPdsAttach(pa);

        return (pano > 0) ? true : false;
    }

    @Override
    public Map<String, Object> readPds(int cpg) {
        return pdsdao.selectPds(cpg - 1);
    }

    @Override
    public Pds readOnePds(int pno) {
        return pdsdao.selectOnePds(pno);
    }

    @Override
    public PdsAttach readOnePdsAttach(int pno) {
        return pdsdao.selectOnePdsAttach(pno);
    }

    @Override
    public HttpHeaders getHeader(String fname, String uuid) {
        return pdsUtils.getHeader(fname, uuid);
    }

    @Override
    public UrlResource getResource(String fname, String uuid) {
        return pdsUtils.getResource(fname, uuid);
    }

    @Override
    public void downfile(int pno) {
        pdsdao.downfile(pno);
    }

    @Override
    public List<String> readFtype(){
        return pdsdao.selectFtype();
    }

    @Override
    public List<PdsReply> readPdsReply(int pno){
        return pdsdao.selectPdsReply(pno);
    }

    @Override
    public boolean newReply(PdsReply reply){
        boolean result = false;
        if(pdsdao.insertPdsReply(reply)) result = true;
        return result;
    }

    @Override
    public boolean newRreply(PdsReply reply){
        boolean result = false;

        if(pdsdao.insertPdsRreply(reply) > 0) result = true;
        return result;
    }
}
