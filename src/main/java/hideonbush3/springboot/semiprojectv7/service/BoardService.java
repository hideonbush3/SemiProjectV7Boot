package hideonbush3.springboot.semiprojectv7.service;

import hideonbush3.springboot.semiprojectv7.model.Board;

import java.util.List;
import java.util.Map;

public interface BoardService {

    Map<String, Object> readBoard(int cpage);

    Map<String, Object> readBoard(int cpg, String ftype, String fkey);

    Board readOneBoard(int bno);

    boolean newBoard(Board bd);
}
