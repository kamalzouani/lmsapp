package ma.zyn.app.dao.facade.core.course;

import org.springframework.data.jpa.repository.Query;
import ma.zyn.app.zynerator.repository.AbstractRepository;
import ma.zyn.app.bean.core.course.Level;
import org.springframework.stereotype.Repository;
import ma.zyn.app.bean.core.course.Level;
import java.util.List;


@Repository
public interface LevelDao extends AbstractRepository<Level,Long>  {
    Level findByCode(String code);
    int deleteByCode(String code);


    @Query("SELECT NEW Level(item.id,item.label) FROM Level item")
    List<Level> findAllOptimized();

}
