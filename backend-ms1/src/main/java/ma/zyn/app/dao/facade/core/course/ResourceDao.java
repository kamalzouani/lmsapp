package ma.zyn.app.dao.facade.core.course;

import ma.zyn.app.zynerator.repository.AbstractRepository;
import ma.zyn.app.bean.core.course.Resource;
import org.springframework.stereotype.Repository;
import java.util.List;


@Repository
public interface ResourceDao extends AbstractRepository<Resource,Long>  {

    List<Resource> findByLessonId(Long id);
    int deleteByLessonId(Long id);
    long countByLessonId(Long id);


}
