package hideonbush3.springboot.semiprojectv7.dao;

import hideonbush3.springboot.semiprojectv7.model.Pds;
import hideonbush3.springboot.semiprojectv7.model.PdsAttach;

import java.util.Map;

public interface PdsDao {
    int insertPds(Pds pds);

    int insertPdsAttach(PdsAttach pa);

    Map<String, Object> selectPds(int i);

    Pds selectOnePds(int pno);

    PdsAttach selectOnePdsAttach(int pno);
}
