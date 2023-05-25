package hideonbush3.springboot.semiprojectv7.service;

import hideonbush3.springboot.semiprojectv7.model.PdsAttach;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.core.io.UrlResource;
import org.springframework.http.HttpHeaders;
import org.springframework.stereotype.Component;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.util.UriUtils;

import java.io.File;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.time.LocalDate;
import java.time.LocalTime;
import java.util.Map;

@Component("pdsUtils")
public class PdsUtils{

    // 첨부파일 저장위치
    @Value("${saveDir}")private String saveDir; // ${변수명} 이렇게 쓰면 이 변수는 application.properties에서 찾는다.

    public String makeUUID() {
        String uuid = LocalDate.now() + "" + LocalTime.now();
        uuid = uuid.replace("-","").replace(".","").replace(":","");
        return uuid;
    }

    public PdsAttach processUpload(
            MultipartFile attach, Map<String, Object> pinfo) {

        // 업로드할 파일 정보 얻기
        PdsAttach pa = new PdsAttach();
        pa.setPno((Integer) pinfo.get("pno"));

        pa.setFname(attach.getOriginalFilename());

        // 파일명1 : abc123.png -> 파일종류 : png
//        pa.setFtype(attach.getContentType());
        // 파일명 : abc123.987xyz.jpg -> 파일종류 : jpg
        int pos = pa.getFname().lastIndexOf(".") + 1;
        String ftype = pa.getFname().substring(pos);
        pa.setFtype(ftype);

        pa.setFsize(String.valueOf(attach.getSize()/1024));

        // 첨부파일을 파일시스템에 저장
        // 시스템에 저장시 사용할 파일명 : 파일명UUID.확장자
        String fname = pa.getFname().substring(0, pos - 1);
        String savefname = saveDir + fname +
                pinfo.get("uuid") + "." + pa.getFtype();

        try {
            attach.transferTo(new File(savefname));
        }catch (Exception ex){
            System.out.println("업로드중 오류발생!!");
            ex.printStackTrace();
        }

        return pa;
    }

    public HttpHeaders getHeader(String fname, String uuid) {
        fname = UriUtils.encode(fname, StandardCharsets.UTF_8);

        // 파일명, uuid 값을 넘겨주면
        // 저장될경로/파일명uuid.확장자 형태를 반환하는 메서드
        String dfname = makeDfname(fname, uuid);

        // MIME 타입 지정
        // 브라우저에 다운로드할 파일에 대한 정보 제공
        HttpHeaders header = new HttpHeaders();
        try {
            header.add("Content-Type",
                    Files.probeContentType(Paths.get(dfname)));
            header.add("Content-Disposition",
                    "attachment; filename=" + fname + "");
        } catch (Exception ex){
            ex.printStackTrace();
        }

        return header;
    }

    private String makeDfname(String fname, String uuid) {
        
        int pos = fname.lastIndexOf(".");
        String name = fname.substring(0, pos);
        String ext = fname.substring(pos + 1);
        
        return saveDir + name + uuid + "." + ext;
    }

    public UrlResource getResource(String fname, String uuid) {
        fname = UriUtils.encode(fname, StandardCharsets.UTF_8);

        // 다운로드할 파일 객체 생성
        UrlResource resource = null;
        try{
            resource = new UrlResource("file:" + makeDfname(fname, uuid) + "");
        }catch (Exception ex){
            ex.printStackTrace();
        }

        return resource;
    }
}
