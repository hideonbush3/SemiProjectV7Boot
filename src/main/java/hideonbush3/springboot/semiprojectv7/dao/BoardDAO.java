package hideonbush3.springboot.semiprojectv7.dao;

import hideonbush3.springboot.semiprojectv7.model.Board;

import java.util.List;
import java.util.Map;

public interface BoardDAO {
    Map<String, Object> selectBoard(int cpage);

    Map<String, Object> selectBoard(Map<String, Object> params);

    int countBoard(Map<String, Object> params);

    Board selectOneBoard(int bno);

    int insertBoard(Board bd);
}