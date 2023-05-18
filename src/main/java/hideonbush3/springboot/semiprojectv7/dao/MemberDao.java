package hideonbush3.springboot.semiprojectv7.dao;

import hideonbush3.springboot.semiprojectv7.model.Member;

public interface MemberDao {
    int selectLogin(Member m);
}
