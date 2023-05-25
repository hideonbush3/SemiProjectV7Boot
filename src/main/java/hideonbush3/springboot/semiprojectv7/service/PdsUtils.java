package hideonbush3.springboot.semiprojectv7.service;

import hideonbush3.springboot.semiprojectv7.model.PdsAttach;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;
import org.springframework.web.multipart.MultipartFile;

import java.io.File;
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
}
