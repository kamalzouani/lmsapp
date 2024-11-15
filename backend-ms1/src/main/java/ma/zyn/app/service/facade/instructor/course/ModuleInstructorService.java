package ma.zyn.app.service.facade.instructor.course;

import java.util.List;
import ma.zyn.app.bean.core.course.Module;
import ma.zyn.app.dao.criteria.core.course.ModuleCriteria;
import ma.zyn.app.zynerator.service.IService;



public interface ModuleInstructorService {



    List<Module> findByCurriculumId(Long id);
    int deleteByCurriculumId(Long id);
    long countByCurriculumId(Long id);




	Module create(Module t);

    Module update(Module t);

    List<Module> update(List<Module> ts,boolean createIfNotExist);

    Module findById(Long id);

    Module findOrSave(Module t);

    Module findByReferenceEntity(Module t);

    Module findWithAssociatedLists(Long id);

    List<Module> findAllOptimized();

    List<Module> findAll();

    List<Module> findByCriteria(ModuleCriteria criteria);

    List<Module> findPaginatedByCriteria(ModuleCriteria criteria, int page, int pageSize, String order, String sortField);

    int getDataSize(ModuleCriteria criteria);

    List<Module> delete(List<Module> ts);

    boolean deleteById(Long id);

    List<List<Module>> getToBeSavedAndToBeDeleted(List<Module> oldList, List<Module> newList);

    public String uploadFile(String checksumOld, String tempUpladedFile,String destinationFilePath) throws Exception ;

}
