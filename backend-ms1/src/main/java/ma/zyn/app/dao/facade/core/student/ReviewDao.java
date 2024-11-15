package ma.zyn.app.dao.facade.core.student;

import ma.zyn.app.zynerator.repository.AbstractRepository;
import ma.zyn.app.bean.core.student.Review;
import org.springframework.stereotype.Repository;
import java.util.List;


@Repository
public interface ReviewDao extends AbstractRepository<Review,Long>  {

    List<Review> findByStudentId(Long id);
    int deleteByStudentId(Long id);
    long countByStudentEmail(String email);
    List<Review> findByCourseId(Long id);
    int deleteByCourseId(Long id);
    long countByCourseCode(String code);


}
