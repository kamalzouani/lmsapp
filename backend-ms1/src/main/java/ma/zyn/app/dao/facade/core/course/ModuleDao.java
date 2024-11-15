package ma.zyn.app.dao.facade.core.course;

import org.springframework.data.jpa.repository.Query;
import ma.zyn.app.zynerator.repository.AbstractRepository;
import ma.zyn.app.bean.core.course.Module;
import org.springframework.stereotype.Repository;
import java.util.List;


@Repository
public interface ModuleDao extends AbstractRepository<Module,Long>  {

    List<Module> findByCurriculumId(Long id);
    int deleteByCurriculumId(Long id);
    long countByCurriculumId(Long id);

    @Query("SELECT NEW Module(item.id,item.label) FROM Module item")
    List<Module> findAllOptimized();

}
