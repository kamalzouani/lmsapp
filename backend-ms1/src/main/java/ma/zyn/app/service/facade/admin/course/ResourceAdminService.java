package ma.zyn.app.service.facade.admin.course;

import java.util.List;
import ma.zyn.app.bean.core.course.Resource;
import ma.zyn.app.dao.criteria.core.course.ResourceCriteria;
import ma.zyn.app.zynerator.service.IService;



public interface ResourceAdminService {



    List<Resource> findByLessonId(Long id);
    int deleteByLessonId(Long id);
    long countByLessonId(Long id);




	Resource create(Resource t);

    Resource update(Resource t);

    List<Resource> update(List<Resource> ts,boolean createIfNotExist);

    Resource findById(Long id);

    Resource findOrSave(Resource t);

    Resource findByReferenceEntity(Resource t);

    Resource findWithAssociatedLists(Long id);

    List<Resource> findAllOptimized();

    List<Resource> findAll();

    List<Resource> findByCriteria(ResourceCriteria criteria);

    List<Resource> findPaginatedByCriteria(ResourceCriteria criteria, int page, int pageSize, String order, String sortField);

    int getDataSize(ResourceCriteria criteria);

    List<Resource> delete(List<Resource> ts);

    boolean deleteById(Long id);

    List<List<Resource>> getToBeSavedAndToBeDeleted(List<Resource> oldList, List<Resource> newList);

    public String uploadFile(String checksumOld, String tempUpladedFile,String destinationFilePath) throws Exception ;

}
