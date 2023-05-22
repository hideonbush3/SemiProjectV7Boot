package hideonbush3.springboot.semiprojectv7.dao;

import hideonbush3.springboot.semiprojectv7.model.Board;
import hideonbush3.springboot.semiprojectv7.repository.BoardRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Map;

@Repository("bddao")
public class BoardDAOImpl implements BoardDAO {

    @Autowired
    BoardRepository boardRepository;

    // 게시판 게시글 출력
    @Override
    public List<Board> selectBoard(int cpage) {
        Pageable paging = // PageRequest.of(cpage, 25, Sort.by("bno").descending());
                             PageRequest.of(cpage, 25, Sort.Direction.DESC, "bno");
        return boardRepository.findAll(paging).getContent();
        // getContent()는 findAll()의 결과값을
        // Page<T>에서 List<T> 타입으로 변환한다
    }

    @Override
    public List<Board> selectBoard(Map<String, Object> params) {
        String ftype = params.get("ftype").toString();
        String fkey = params.get("fkey").toString();
        int cpage = (int) params.get("stbno");

        Pageable paging = PageRequest.of(cpage, 25, Sort.by("bno").descending());

        List<Board> result = null;

        switch(ftype){
            case "title": // 제목으로 검색
                 result = boardRepository.findByTitle(paging, fkey); break;
            case "titcont": // 제목 + 본문으로 검색
                result = boardRepository.findByTitleOrContent(paging, fkey, fkey); break;
            case "userid": // 작성자로 검색
                result = boardRepository.findByUserid(paging, fkey); break;
            case "content": // 본문으로 검색
                result = boardRepository.findByContent(paging, fkey);
        }
        return result;
    }

    @Override
    public int countBoard() {
        // select ceil(count(bno)/25) from board
        int allcnt = boardRepository.countBoardBy();    // 게시글 전체 개수
        return (int) Math.ceil(allcnt / 25);            // 전체 페이지 개수
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