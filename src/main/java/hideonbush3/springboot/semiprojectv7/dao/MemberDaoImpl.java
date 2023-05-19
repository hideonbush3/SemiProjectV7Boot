package hideonbush3.springboot.semiprojectv7.dao;

import hideonbush3.springboot.semiprojectv7.model.Member;
import hideonbush3.springboot.semiprojectv7.repository.MemberRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

@Repository("mdao")
public class MemberDaoImpl implements MemberDao{

    @Autowired
    MemberRepository memberRepository;

    @Override
    public int selectLogin(Member m) {
        return memberRepository
                .countByUseridAndPasswd(m.getUserid(), m.getPasswd());
    }
}
