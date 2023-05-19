package hideonbush3.springboot.semiprojectv7.service;

import hideonbush3.springboot.semiprojectv7.model.Board;

import java.util.List;

public interface BoardService {

    List<Board> readBoard(int cpage);

    int countBoard();

    List<Board> readBoard(int cpg, String ftype, String fkey);

    int countBoard(String ftype, String fkey);

    Board readOneBoard(int bno);

    boolean newBoard(Board bd);
}
