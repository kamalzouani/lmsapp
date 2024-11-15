package ma.zyn.app.service.facade.student.course;

import java.util.List;
import ma.zyn.app.bean.core.course.Lesson;
import ma.zyn.app.dao.criteria.core.course.LessonCriteria;
import ma.zyn.app.zynerator.service.IService;



public interface LessonStudentService {



    List<Lesson> findByModuleId(Long id);
    int deleteByModuleId(Long id);
    long countByModuleId(Long id);




	Lesson create(Lesson t);

    Lesson update(Lesson t);

    List<Lesson> update(List<Lesson> ts,boolean createIfNotExist);

    Lesson findById(Long id);

    Lesson findOrSave(Lesson t);

    Lesson findByReferenceEntity(Lesson t);

    Lesson findWithAssociatedLists(Long id);

    List<Lesson> findAllOptimized();

    List<Lesson> findAll();

    List<Lesson> findByCriteria(LessonCriteria criteria);

    List<Lesson> findPaginatedByCriteria(LessonCriteria criteria, int page, int pageSize, String order, String sortField);

    int getDataSize(LessonCriteria criteria);

    List<Lesson> delete(List<Lesson> ts);

    boolean deleteById(Long id);

    List<List<Lesson>> getToBeSavedAndToBeDeleted(List<Lesson> oldList, List<Lesson> newList);

    public String uploadFile(String checksumOld, String tempUpladedFile,String destinationFilePath) throws Exception ;

}
