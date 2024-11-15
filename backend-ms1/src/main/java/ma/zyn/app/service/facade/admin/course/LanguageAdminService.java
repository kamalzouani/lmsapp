package ma.zyn.app.service.facade.admin.course;

import java.util.List;
import ma.zyn.app.bean.core.course.Language;
import ma.zyn.app.dao.criteria.core.course.LanguageCriteria;
import ma.zyn.app.zynerator.service.IService;



public interface LanguageAdminService {







	Language create(Language t);

    Language update(Language t);

    List<Language> update(List<Language> ts,boolean createIfNotExist);

    Language findById(Long id);

    Language findOrSave(Language t);

    Language findByReferenceEntity(Language t);

    Language findWithAssociatedLists(Long id);

    List<Language> findAllOptimized();

    List<Language> findAll();

    List<Language> findByCriteria(LanguageCriteria criteria);

    List<Language> findPaginatedByCriteria(LanguageCriteria criteria, int page, int pageSize, String order, String sortField);

    int getDataSize(LanguageCriteria criteria);

    List<Language> delete(List<Language> ts);

    boolean deleteById(Long id);

    List<List<Language>> getToBeSavedAndToBeDeleted(List<Language> oldList, List<Language> newList);

    public String uploadFile(String checksumOld, String tempUpladedFile,String destinationFilePath) throws Exception ;

}
