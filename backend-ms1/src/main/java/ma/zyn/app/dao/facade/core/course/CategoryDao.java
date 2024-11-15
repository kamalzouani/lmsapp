package ma.zyn.app.dao.facade.core.course;

import org.springframework.data.jpa.repository.Query;
import ma.zyn.app.zynerator.repository.AbstractRepository;
import ma.zyn.app.bean.core.course.Category;
import org.springframework.stereotype.Repository;
import ma.zyn.app.bean.core.course.Category;
import java.util.List;


@Repository
public interface CategoryDao extends AbstractRepository<Category,Long>  {
    Category findByCode(String code);
    int deleteByCode(String code);


    @Query("SELECT NEW Category(item.id,item.label) FROM Category item")
    List<Category> findAllOptimized();

}
