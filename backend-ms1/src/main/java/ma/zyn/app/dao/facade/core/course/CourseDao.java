package ma.zyn.app.dao.facade.core.course;

import org.springframework.data.jpa.repository.Query;
import ma.zyn.app.zynerator.repository.AbstractRepository;
import ma.zyn.app.bean.core.course.Course;
import org.springframework.stereotype.Repository;
import ma.zyn.app.bean.core.course.Course;
import java.util.List;


@Repository
public interface CourseDao extends AbstractRepository<Course,Long>  {
    Course findByCode(String code);
    int deleteByCode(String code);

    List<Course> findByInstructorId(Long id);
    int deleteByInstructorId(Long id);
    long countByInstructorEmail(String email);
    List<Course> findByCategoryCode(String code);
    List<Course> findByCategoryId(Long id);
    int deleteByCategoryId(Long id);
    int deleteByCategoryCode(String code);
    long countByCategoryCode(String code);
    List<Course> findByLevelCode(String code);
    List<Course> findByLevelId(Long id);
    int deleteByLevelId(Long id);
    int deleteByLevelCode(String code);
    long countByLevelCode(String code);
    List<Course> findByLanguageCode(String code);
    List<Course> findByLanguageId(Long id);
    int deleteByLanguageId(Long id);
    int deleteByLanguageCode(String code);
    long countByLanguageCode(String code);
    List<Course> findByCurriculumId(Long id);
    int deleteByCurriculumId(Long id);
    long countByCurriculumId(Long id);

    @Query("SELECT NEW Course(item.id,item.label) FROM Course item")
    List<Course> findAllOptimized();

}
