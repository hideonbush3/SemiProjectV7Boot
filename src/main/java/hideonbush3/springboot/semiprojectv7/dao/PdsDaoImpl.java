package hideonbush3.springboot.semiprojectv7.dao;

import hideonbush3.springboot.semiprojectv7.model.Pds;
import hideonbush3.springboot.semiprojectv7.model.PdsAttach;
import hideonbush3.springboot.semiprojectv7.repository.PdsaRepository;
import hideonbush3.springboot.semiprojectv7.repository.PdsRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Repository;

import java.util.HashMap;
import java.util.Map;

@Repository("pdsdao")
public class PdsDaoImpl implements PdsDao{
    @Autowired
    private PdsRepository pdsRepository;

    @Autowired
    private PdsaRepository pdsaRepository;
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

}
