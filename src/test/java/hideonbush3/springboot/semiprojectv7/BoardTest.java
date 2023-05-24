package hideonbush3.springboot.semiprojectv7;

import hideonbush3.springboot.semiprojectv7.model.Board;
import hideonbush3.springboot.semiprojectv7.repository.BoardRepository;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.List;

@SpringBootTest
public class BoardTest {
    @Autowired
    BoardRepository boardRepository;
    @Test
    @DisplayName("게시글 작성")
    public void saveBoard(){
        Board b = new Board(null, "탈마요르카",
                "강인맘04", null, null, "드가자~", null);

        boardRepository.save(b);
    }
}
