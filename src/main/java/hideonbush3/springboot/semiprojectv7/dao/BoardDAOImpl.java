package hideonbush3.springboot.semiprojectv7.dao;

import hideonbush3.springboot.semiprojectv7.model.Board;
import hideonbush3.springboot.semiprojectv7.repository.BoardRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Repository;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Repository("bddao")
public class BoardDAOImpl implements BoardDAO {

    @Autowired
    BoardRepository boardRepository;


    // 게시판 게시글 출력
    @Override
    public Map<String, Object> selectBoard(int cpage) {
        // 페이징 시 정렬 순서 지정
        Pageable paging = PageRequest.of(cpage, 25, Sort.Direction.DESC, "bno");

        // getContent()는 findAll()의 결과값을
        // Page<T>에서 List<T> 타입으로 변환한다
        Map<String, Object> bds = new HashMap<>();
        bds.put("bdlist",boardRepository.findAll(paging).getContent());
        bds.put("cntpg",boardRepository.findAll(paging).getTotalPages());
        return bds;
    }

    @Override
    public Map<String, Object> selectBoard(Map<String, Object> params) {

        // like 검색에 대한 query method
        // findByTitleLike       : %검색어% - 검색어 앞 뒤로 '%'를 붙여야함
        // findByTitleContains   : %검색어% - '%' 붙일 필요없음
        // findByTitleStartsWith : 검색어% - '%' 붙일 필요없음
        // findByTitleEndsWith   : %검색어 - '%' 붙일 필요없음

        String ftype = params.get("ftype").toString();
        String fkey = params.get("fkey").toString();
        int cpage = (int) params.get("stbno");

        Pageable paging = PageRequest.of(cpage, 25, Sort.by("bno").descending());

        Page<Board> result = null;

        switch(ftype){
            case "title": // 제목으로 검색
                 result = boardRepository.findByTitleContains(paging, fkey); break;
            case "titcont": // 제목 + 본문으로 검색
                result = boardRepository.findByTitleContainsOrContentContains(paging, fkey, fkey); break;
            case "userid": // 작성자로 검색
                result = boardRepository.findByUseridLike(paging, fkey); break;
            case "content": // 본문으로 검색
                result = boardRepository.findByContentContains(paging, fkey);
        }

        Map<String, Object> bds = new HashMap<>();
        bds.put("bdlist", result.getContent());
        bds.put("cntpg",result.getTotalPages());
        return bds;
    }

    @Override
    public int countBoard(Map<String, Object> params) {
        return 0;
    }

    @Override
    public Board selectOneBoard(int bno) {
        boardRepository.countViewBoard(bno);                // 게시글 상세보기 시
        return boardRepository.findById((long)bno).get();
    }

    @Override
    public int insertBoard(Board bd) {
        return Math.toIntExact(boardRepository.save(bd).getBno());
    }
}