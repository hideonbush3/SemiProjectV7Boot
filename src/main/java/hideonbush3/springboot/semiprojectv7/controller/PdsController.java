package hideonbush3.springboot.semiprojectv7.controller;

import hideonbush3.springboot.semiprojectv7.model.Pds;
import hideonbush3.springboot.semiprojectv7.model.PdsAttach;
import hideonbush3.springboot.semiprojectv7.service.PdsService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.Resource;
import org.springframework.core.io.UrlResource;
import org.springframework.http.HttpHeaders;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.ModelAndView;

import javax.validation.Valid;
import java.util.Map;

@Controller
@RequestMapping("/pds")
public class PdsController {
    @Autowired
    private PdsService pdssrv;

    @GetMapping("/list")
    public ModelAndView list(Integer cpg){
        ModelAndView mv = new ModelAndView();
        mv.setViewName("pds/list");

        if(cpg == null || cpg == 0) cpg = 1;
        Map<String, Object> pds = pdssrv.readPds(cpg);

        mv.addObject("pdslist", pds.get("pdslist"));   // 현재페이지에 출력할 게시글리스트
        mv.addObject("cpg", cpg);   // 현재페이지 번호
        mv.addObject("stpg", ((cpg - 1) / 10) * 10 + 1);
        mv.addObject("cntpg", pds.get("cntpg"));  // 총페이지수

        return mv;
    }

    @GetMapping("/write")   // 입력폼
    public String write(Model m){
        m.addAttribute("pds", new Pds());
        return "pds/write";
    }

    @PostMapping("/write")  // 전송된 데이터 처리
    public String writeok(@Valid Pds pds, MultipartFile attach) {
        String viewPage = "error";

        Map<String, Object> pinfo = pdssrv.newPds(pds);

        if(!attach.isEmpty()) // 첨부파일이 존재한다면
            pdssrv.newPdsAttach(attach, pinfo);

        viewPage = "redirect:/pds/list";

        return viewPage;
    }

    @GetMapping("/view")
    public String view(int pno, Model m){
        m.addAttribute("pds", pdssrv.readOnePds(pno));
        m.addAttribute("attach", pdssrv.readOnePdsAttach(pno));
        return "pds/view";
    }

    @GetMapping("/down")
    public ResponseEntity<Resource> down(int pno){

        // 업로드 파일의 uuid와 파일명 알아냄
        String uuid = pdssrv.readOnePds(pno).getUuid();
        String fname = pdssrv.readOnePdsAttach(pno).getFname();

        // 알아낸 uuid와 파일명을 이용해서 header와 리소스 객체 생성
        HttpHeaders header = pdssrv.getHeader(fname, uuid);
        UrlResource resource = pdssrv.getResource(fname, uuid);

        // 다운로드 수 증가
        pdssrv.downfile(pno);

        return ResponseEntity.ok().headers(header).body(resource);
    }
}