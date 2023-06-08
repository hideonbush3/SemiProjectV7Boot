package hideonbush3.springboot.semiprojectv7.service;

import hideonbush3.springboot.semiprojectv7.model.GalAttach;
import hideonbush3.springboot.semiprojectv7.model.PdsAttach;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;
import org.springframework.web.multipart.MultipartFile;

import java.io.File;
import java.time.LocalDate;
import java.time.LocalTime;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

@Component("galUtils")
public class GalleryUtils {
    @Value("${saveImgDir}")private String saveImgDir; // ${변수명} 이렇게 쓰면 이 변수는 application.properties에서 찾는다.

    public String makeUUID() {
        String uuid = LocalDate.now() + "" + LocalTime.now();
        uuid = uuid.replace("-","").replace(".","").replace(":","");
        return uuid;
    }

    public GalAttach processUpload(List<MultipartFile> attachs, Map<String, Object> ginfo) {
        // 첨부 이미지파일명과 사이즈는 먼저, 리스트로 저장한뒤 문자열로 변환
        // 업로드할 파일 정보 얻기
        GalAttach ga = new GalAttach();
        ga.setGno((Integer) ginfo.get("gno"));
        List<String> fnames = new ArrayList<>();
        List<String> fsizes = new ArrayList<>();

        // 첨부된 파일들에 대한 반복처리
    for(MultipartFile attach : attachs){
        String fname = attach.getOriginalFilename();

        // 파일 확장자와 크기 추출
        int pos = fname.lastIndexOf(".") + 1;
        String ext = fname.substring(pos);
        String fsize = String.valueOf(attach.getSize() / 1024);

        // 저장시 사용할 파일이름 생성
        // 파일명 형식 - 파일명UUID.확장자
        String savefname = fname.substring(0, pos - 1);
        String fullname = savefname + ginfo.get("uuid") + "." + ext;
        savefname = saveImgDir + fullname;

        try {
            // 첨부파일을 파일시스템에 저장
            attach.transferTo(new File(savefname));

            //첨부파일 정보를 리스트에 저장
            fnames.add(fullname);
            fsizes.add(fsize);
            System.out.println(
                    fullname + ", " + fsize + ", " + savefname
            );
        }catch (Exception ex){
            System.out.println("업로드중 오류발생!!");
            ex.printStackTrace();
        }
    }   // for

        // 수집된 첨부파일 정보를 GalAttach 객체에 저장
        // join(구분자, 리스트변수) - 요소1;요소2;요소3..
        ga.setFname(String.join(";", fnames));
        ga.setFsize(String.join(";", fsizes));
        System.out.println(ga.getFname() + " " + ga.getFsize());

    return ga;
}

    public void makeThumbnail(GalAttach ga, Object uuid) {
    }
}
