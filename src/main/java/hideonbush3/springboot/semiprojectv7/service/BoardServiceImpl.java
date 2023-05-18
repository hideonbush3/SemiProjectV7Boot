package hideonbush3.springboot.semiprojectv7.service;

import hideonbush3.springboot.semiprojectv7.dao.BoardDAO;
import hideonbush3.springboot.semiprojectv7.model.Board;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Service("bdsrv")
public class BoardServiceImpl implements BoardService {

    @Autowired private BoardDAO bddao;

    @Override
    public List<Board> readBoard(int cpage) {
        int stbno = (cpage - 1) * 25;
        return bddao.selectBoard(stbno);
    }

    @Override
    public int countBoard() {
        return bddao.countBoard();
    }

    @Override
    public List<Board> readBoard(int cpg, String ftype, String fkey){
        int stbno = (cpg - 1) * 25;

        // 처리시 사용할 데이터들을 해쉬맵에 담아서 보냄
        Map<String, Object> params = new HashMap<>();
        params.put("stbno", stbno);
        params.put("ftype", ftype);
        params.put("fkey", fkey);

        return bddao.selectBoard(params);
    }

    @Override
    public int countBoard(String ftype, String fkey){
        Map<String, Object> params = new HashMap<>();
        params.put("ftype", ftype);
        params.put("fkey", fkey);

        return bddao.countBoard(params);
    }

    @Override
    public boolean newBoard(Board bd) {
        boolean result = false;
        if(bddao.insertBoard(bd) > 0) result = true;

        return result;
    }

    @Override
    public Board readOneBoard(String bno) {
        return bddao.selectOneBoard(bno);
    }
}