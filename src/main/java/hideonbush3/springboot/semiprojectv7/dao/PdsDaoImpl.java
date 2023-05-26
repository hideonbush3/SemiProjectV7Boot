package hideonbush3.springboot.semiprojectv7.dao;

import hideonbush3.springboot.semiprojectv7.model.Pds;
import hideonbush3.springboot.semiprojectv7.model.PdsAttach;
import hideonbush3.springboot.semiprojectv7.model.PdsReply;
import hideonbush3.springboot.semiprojectv7.repository.PdsaRepository;
import hideonbush3.springboot.semiprojectv7.repository.PdsRepository;
import hideonbush3.springboot.semiprojectv7.repository.PdsReplyRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Repository;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Repository("pdsdao")
public class PdsDaoImpl implements PdsDao{
    @Autowired
    private PdsRepository pdsRepository;

    @Autowired
    private PdsaRepository pdsaRepository;

    @Autowired
    private PdsReplyRepository pdsReplyRepository;
    @Override
    public int insertPds(Pds pds) {
        // 제목, 작성자, 본문을 pds 테이블에 저장한 뒤
        // 저장시 생성된 pno 를 리턴함 - 그것을 PdsAttach 클래스의 pno값에 저장해서 쓰면 됨
        return Math.toIntExact(pdsRepository.save(pds).getPno());
    }

    // 파일정보 저장
    @Override
    public int insertPdsAttach(PdsAttach pa) {
        return Math.toIntExact(pdsaRepository.save(pa).getPano());
    }

    @Override
    public Map<String, Object> selectPds(int cpg) {
        // 페이징 시 정렬 순서 지정
        Pageable paging = PageRequest.of(cpg, 25, Sort.Direction.DESC, "pno");

        // getContent()는 findAll()의 결과값을
        // Page<T>에서 List<T> 타입으로 변환한다
        Map<String, Object> pds = new HashMap<>();
        pds.put("pdslist",pdsRepository.findAll(paging).getContent());
        pds.put("cntpg",pdsRepository.findAll(paging).getTotalPages());
        return pds;
    }

    @Override
    public Pds selectOnePds(int pno) {
        pdsRepository.countViewById(pno);   // 조회수 증가
        return pdsRepository.findById((long) pno).get();
    }

    @Override
    public PdsAttach selectOnePdsAttach(int pno) {
        return pdsaRepository.findByPno(pno);
    }

    @Override
    public void downfile(int pno) {
        pdsaRepository.countDownById(pno);
    }

    @Override
    public List<String> selectFtype(){
        return pdsaRepository.findByFtypes();
    }

    @Override
    public List<PdsReply> selectPdsReply(int pno){
        return pdsReplyRepository.findByPnoOrderByRefnoAscRegdateAsc((long)pno);
    }

    @Override
    public boolean insertPdsReply(PdsReply reply){
        // 댓글 저장하는 시점에는 없던 댓글번호(rpno)를 (이 시점에 refno의 값은 null로 저장됨)
        // refno에도 대입하기 위해 update 문을 한번 더 실행
        boolean result = false;
        PdsReply p = pdsReplyRepository.save(reply);
        pdsReplyRepository.updateRefno(p.getRpno());
        if(p.getRpno() > 0) result = true;
        return result;
    }

    @Override
    public int insertPdsRreply(PdsReply reply){
        // 대댓글 저장시 이미 링크로 전달받은 댓글번호가
        // refno에 대입되어 있음 - 댓글 저장과는 달리 한번에 처리
        long rpno = pdsReplyRepository.save(reply).getRpno();

        return (int) rpno;
    }

}
