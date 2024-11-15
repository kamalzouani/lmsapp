package ma.zyn.app.service.facade.admin.course;

import java.util.List;
import ma.zyn.app.bean.core.course.Level;
import ma.zyn.app.dao.criteria.core.course.LevelCriteria;
import ma.zyn.app.zynerator.service.IService;



public interface LevelAdminService {







	Level create(Level t);

    Level update(Level t);

    List<Level> update(List<Level> ts,boolean createIfNotExist);

    Level findById(Long id);

    Level findOrSave(Level t);

    Level findByReferenceEntity(Level t);

    Level findWithAssociatedLists(Long id);

    List<Level> findAllOptimized();

    List<Level> findAll();

    List<Level> findByCriteria(LevelCriteria criteria);

    List<Level> findPaginatedByCriteria(LevelCriteria criteria, int page, int pageSize, String order, String sortField);

    int getDataSize(LevelCriteria criteria);

    List<Level> delete(List<Level> ts);

    boolean deleteById(Long id);

    List<List<Level>> getToBeSavedAndToBeDeleted(List<Level> oldList, List<Level> newList);

    public String uploadFile(String checksumOld, String tempUpladedFile,String destinationFilePath) throws Exception ;

}
