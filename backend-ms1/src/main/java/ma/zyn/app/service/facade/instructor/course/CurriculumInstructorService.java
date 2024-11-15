package ma.zyn.app.service.facade.instructor.course;

import java.util.List;
import ma.zyn.app.bean.core.course.Curriculum;
import ma.zyn.app.dao.criteria.core.course.CurriculumCriteria;
import ma.zyn.app.zynerator.service.IService;



public interface CurriculumInstructorService {



    List<Curriculum> findByCourseId(Long id);
    int deleteByCourseId(Long id);
    long countByCourseCode(String code);




	Curriculum create(Curriculum t);

    Curriculum update(Curriculum t);

    List<Curriculum> update(List<Curriculum> ts,boolean createIfNotExist);

    Curriculum findById(Long id);

    Curriculum findOrSave(Curriculum t);

    Curriculum findByReferenceEntity(Curriculum t);

    Curriculum findWithAssociatedLists(Long id);

    List<Curriculum> findAllOptimized();

    List<Curriculum> findAll();

    List<Curriculum> findByCriteria(CurriculumCriteria criteria);

    List<Curriculum> findPaginatedByCriteria(CurriculumCriteria criteria, int page, int pageSize, String order, String sortField);

    int getDataSize(CurriculumCriteria criteria);

    List<Curriculum> delete(List<Curriculum> ts);

    boolean deleteById(Long id);

    List<List<Curriculum>> getToBeSavedAndToBeDeleted(List<Curriculum> oldList, List<Curriculum> newList);

    public String uploadFile(String checksumOld, String tempUpladedFile,String destinationFilePath) throws Exception ;

}
