package ma.zyn.app.dao.facade.core.course;

import ma.zyn.app.zynerator.repository.AbstractRepository;
import ma.zyn.app.bean.core.course.Curriculum;
import org.springframework.stereotype.Repository;
import java.util.List;


@Repository
public interface CurriculumDao extends AbstractRepository<Curriculum,Long>  {

    List<Curriculum> findByCourseId(Long id);
    int deleteByCourseId(Long id);
    long countByCourseCode(String code);


}
