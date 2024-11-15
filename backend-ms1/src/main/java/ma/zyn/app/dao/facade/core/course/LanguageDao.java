package ma.zyn.app.dao.facade.core.course;

import org.springframework.data.jpa.repository.Query;
import ma.zyn.app.zynerator.repository.AbstractRepository;
import ma.zyn.app.bean.core.course.Language;
import org.springframework.stereotype.Repository;
import ma.zyn.app.bean.core.course.Language;
import java.util.List;


@Repository
public interface LanguageDao extends AbstractRepository<Language,Long>  {
    Language findByCode(String code);
    int deleteByCode(String code);


    @Query("SELECT NEW Language(item.id,item.label) FROM Language item")
    List<Language> findAllOptimized();

}
