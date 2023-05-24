package hideonbush3.springboot.semiprojectv7.dao;

import hideonbush3.springboot.semiprojectv7.model.Pds;
import hideonbush3.springboot.semiprojectv7.repository.PdsRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

@Repository("pdsdao")
public class PdsDaoImpl implements PdsDao{
    @Autowired
    private PdsRepository pdsRepository;
    @Override
    public int insertPds(Pds pds) {
        // 제목, 작성자, 본문을 pds 테이블에 저장한 뒤
        // 저장시 생성된 pno 를 리턴함 - 그것을 PdsAttach 클래스의 pno값에 저장해서 쓰면 됨
        return Math.toIntExact(pdsRepository.save(pds).getPno());
    }
}
