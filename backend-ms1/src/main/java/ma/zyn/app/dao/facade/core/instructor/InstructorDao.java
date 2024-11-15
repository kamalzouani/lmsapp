package ma.zyn.app.dao.facade.core.instructor;

import org.springframework.data.jpa.repository.Query;
import ma.zyn.app.zynerator.repository.AbstractRepository;
import ma.zyn.app.bean.core.instructor.Instructor;
import org.springframework.stereotype.Repository;
import ma.zyn.app.bean.core.instructor.Instructor;
import java.util.List;


@Repository
public interface InstructorDao extends AbstractRepository<Instructor,Long>  {
    Instructor findByEmail(String email);
    int deleteByEmail(String email);

    Instructor findByUsername(String username);

    @Query("SELECT NEW Instructor(item.id,item.email) FROM Instructor item")
    List<Instructor> findAllOptimized();

}
