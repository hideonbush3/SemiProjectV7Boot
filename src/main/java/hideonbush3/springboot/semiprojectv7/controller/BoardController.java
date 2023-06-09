package hideonbush3.springboot.semiprojectv7.controller;

import hideonbush3.springboot.semiprojectv7.model.Board;
import hideonbush3.springboot.semiprojectv7.service.BoardService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

import javax.validation.Valid;
import java.util.Map;

@Controller
@RequestMapping("/board")
public class BoardController {

    @Autowired
    private BoardService bdsrv;

    @GetMapping("/list")
    public ModelAndView list(Integer cpg) {
        ModelAndView mv = new ModelAndView();
        mv.setViewName("board/list");

        if(cpg == null || cpg == 0) cpg = 1;
        Map<String, Object> bds = bdsrv.readBoard(cpg);

        mv.addObject("bdlist", bds.get("bdlist"));   // 현재페이지에 출력할 게시글리스트
        mv.addObject("cpg", cpg);   // 현재페이지 번호
        mv.addObject("stpg", ((cpg - 1) / 10) * 10 + 1);
        mv.addObject("cntpg", bds.get("cntpg"));  // 총페이지수

        return mv;
    }

    @GetMapping("/find")    // 검색처리
    public ModelAndView find(int cpg, String ftype, String fkey){
        ModelAndView mv = new ModelAndView();

        Map<String, Object> bds = bdsrv.readBoard(cpg, ftype, fkey);

        mv.setViewName("board/list");
        mv.addObject("bdlist", bds.get("bdlist"));
        mv.addObject("cpg", cpg);   // 현재페이지 번호
        mv.addObject("stpg", ((cpg - 1) / 10) * 10 + 1);
        mv.addObject("cntpg", bds.get("cntpg"));  // 총페이지수
        return mv;
    }

    @GetMapping("/write")
    public String write(Model m) {

        // validation을 위한 첫번째 코드
        m.addAttribute("board", new Board());

        return "board/write";
    }

    @PostMapping("/write")
    public String writeok(@Valid Board board,
                          BindingResult br) {   // 오류 메세지를 저장할 객체 BindingResult
        String viewPage = "redirect:/board/list?cpg=1";

        if(br.hasErrors()){ // 오류 메세지 저장 객체에 오류 메세지가 있다면
            viewPage = "board/write";
        }else
            bdsrv.newBoard(board);
        return viewPage;
    }
    @GetMapping("/view")
    public ModelAndView view(int bno) {

        ModelAndView mv = new ModelAndView();
        mv.addObject("bd", bdsrv.readOneBoard(bno));
        mv.setViewName("board/view");

        return mv;
    }
}