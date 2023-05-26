package hideonbush3.springboot.semiprojectv7.dao;

import hideonbush3.springboot.semiprojectv7.model.Pds;
import hideonbush3.springboot.semiprojectv7.model.PdsAttach;
import hideonbush3.springboot.semiprojectv7.model.PdsReply;

import java.util.List;
import java.util.Map;

public interface PdsDao {
    int insertPds(Pds pds);

    int insertPdsAttach(PdsAttach pa);

    Map<String, Object> selectPds(int i);

    Pds selectOnePds(int pno);

    PdsAttach selectOnePdsAttach(int pno);

    void downfile(int pno);

    List<String> selectFtype();

    List<PdsReply> selectPdsReply(int pno);
}
