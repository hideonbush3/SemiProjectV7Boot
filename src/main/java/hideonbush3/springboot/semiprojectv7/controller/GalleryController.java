package hideonbush3.springboot.semiprojectv7.controller;

import hideonbush3.springboot.semiprojectv7.model.Gallery;
import hideonbush3.springboot.semiprojectv7.model.Pds;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.multipart.MultipartFile;

import javax.validation.Valid;
import java.util.List;
import java.util.Map;

@Controller
@RequestMapping("/gallery")
public class GalleryController {

    @GetMapping("/list")
    public String list() {
        return "gallery/list";
    }

    @GetMapping("/write")   // 입력폼
    public String write(Gallery gallery, List<MultipartFile> attachs){
        return "gallery/write";
    }

    @GetMapping("/view")
    public String view(int pno, Model m){
//        m.addAttribute("gallery", pdssrv.readOnePds(pno));
//        m.addAttribute("attach", pdssrv.readOnePdsAttach(pno));
//        m.addAttribute("rplist", pdssrv.readPdsReply(pno));

        return "gallery/view";
    }

}