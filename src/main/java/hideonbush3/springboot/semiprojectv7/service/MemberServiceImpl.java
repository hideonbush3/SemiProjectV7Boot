package hideonbush3.springboot.semiprojectv7.service;

import hideonbush3.springboot.semiprojectv7.dao.MemberDao;
import hideonbush3.springboot.semiprojectv7.model.Member;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.servlet.http.HttpSession;

@Service("msrv")
public class MemberServiceImpl implements MemberService{

    @Autowired
    private MemberDao mdao;

    @Override
    public boolean checkLogin(Member m, HttpSession sess) {
        boolean isLogin = false;
        // member 테이블에서 해당하는 아이디의 개수를 조회하고 그것이 0 이상(있을 경우)일 경우
        if (mdao.selectLogin(m) > 0){
            // 로그인 가능하면 세션변수에 아이디를 저장
            sess.setAttribute("UID", m.getUserid());
            isLogin = true;
        }

        return isLogin;
    }
}
