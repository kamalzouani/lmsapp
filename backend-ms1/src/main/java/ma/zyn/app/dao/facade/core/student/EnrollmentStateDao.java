package ma.zyn.app.dao.facade.core.student;

import org.springframework.data.jpa.repository.Query;
import ma.zyn.app.zynerator.repository.AbstractRepository;
import ma.zyn.app.bean.core.student.EnrollmentState;
import org.springframework.stereotype.Repository;
import ma.zyn.app.bean.core.student.EnrollmentState;
import java.util.List;


@Repository
public interface EnrollmentStateDao extends AbstractRepository<EnrollmentState,Long>  {
    EnrollmentState findByCode(String code);
    int deleteByCode(String code);


    @Query("SELECT NEW EnrollmentState(item.id,item.label) FROM EnrollmentState item")
    List<EnrollmentState> findAllOptimized();

}
