package ma.zyn.app.dao.facade.core.course;

import org.springframework.data.jpa.repository.Query;
import ma.zyn.app.zynerator.repository.AbstractRepository;
import ma.zyn.app.bean.core.course.Lesson;
import org.springframework.stereotype.Repository;
import java.util.List;


@Repository
public interface LessonDao extends AbstractRepository<Lesson,Long>  {

    List<Lesson> findByModuleId(Long id);
    int deleteByModuleId(Long id);
    long countByModuleId(Long id);

    @Query("SELECT NEW Lesson(item.id,item.label) FROM Lesson item")
    List<Lesson> findAllOptimized();

}
