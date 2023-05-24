package hideonbush3.springboot.semiprojectv7.controller;

import hideonbush3.springboot.semiprojectv7.model.Pds;
import hideonbush3.springboot.semiprojectv7.service.PdsService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.multipart.MultipartFile;

import javax.validation.Valid;

@Controller
@RequestMapping("/pds")
public class PdsController {
    @Autowired
    private PdsService pdssrv;

    @GetMapping("/list")
    public String list(){
        return "pds/list";
    }

    @GetMapping("/write")   // 입력폼
    public String write(Model m){
        m.addAttribute("pds", new Pds());
        return "pds/write";
    }

    @PostMapping("/write")  // 전송된 데이터 처리
    public String writeok(@Valid Pds pds, MultipartFile attach) {
        String viewPage = "error";

        int pno = pdssrv.newPds(pds);
        if(pdssrv.newPdsAttach(attach, pno)) viewPage = "redirect:/pds/list";

        return viewPage;
    }
}