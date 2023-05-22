package hideonbush3.springboot.semiprojectv7.repository;

import hideonbush3.springboot.semiprojectv7.model.Board;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

//public interface BoardRepository extends JpaRepository<Board, Long> {
public interface BoardRepository extends PagingAndSortingRepository<Board, Long> {

    // JpaRepository 에서는 DML 지원 안함
    // 단, Modifying, Transactional등을 추가하면 사용가능!
    @Modifying@Transactional
    @Query("update Board set views = views + 1 where bno = :bno")
    int countViewBoard(@Param("bno") long bno);

    // 게시글 전체 수 조회
    //@Query("select ceil(count(bno)/25) from board")
    int countBoardBy();

    // 게시글 검색
    List<Board> findByTitle(Pageable paging, String fkey);

    List<Board> findByTitleOrContent(Pageable paging, String fkey1, String fkey2);

    List<Board> findByUserid(Pageable paging, String fkey);

    List<Board> findByContent(Pageable paging, String fkey);
}
