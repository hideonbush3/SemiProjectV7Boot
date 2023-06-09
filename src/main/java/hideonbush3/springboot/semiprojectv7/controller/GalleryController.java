package hideonbush3.springboot.semiprojectv7.controller;

import hideonbush3.springboot.semiprojectv7.model.Gallery;
import hideonbush3.springboot.semiprojectv7.service.GalleryService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.ModelAndView;

import java.util.List;
import java.util.Map;


@Controller
@RequestMapping("/gallery")
public class GalleryController {

    @Autowired
    private GalleryService galsrv;

    @GetMapping("/list")
    public ModelAndView list(Integer cpg) {
        ModelAndView mv = new ModelAndView();
        mv.setViewName("gallery/list");

        if(cpg == null || cpg == 0) cpg = 1;
        Map<String, Object> gals = galsrv.readGallery(cpg);

        mv.addObject("gallist", gals.get("gallist"));   // 현재페이지에 출력할 게시글리스트
        mv.addObject("cpg", cpg);   // 현재페이지 번호
        mv.addObject("stpg", ((cpg - 1) / 10) * 10 + 1);
        mv.addObject("cntpg", gals.get("cntpg"));  // 총페이지수

        return mv;
    }

    @GetMapping("/write")
    public String write(Model m) {
        m.addAttribute("gallery", new Gallery());

        return "gallery/write";
    }

    @PostMapping("/write")
    public String writeok(Gallery gallery, List<MultipartFile> attachs) {
        String viewPage = "error";

        if (!attachs.isEmpty()) {// 첨부파일이 존재한다면
            Map<String, Object> ginfo = galsrv.newGallery(gallery);
            galsrv.newGalAttach(attachs, ginfo);
            viewPage = "redirect:/gallery/list";
        }

        return viewPage;
    }

}