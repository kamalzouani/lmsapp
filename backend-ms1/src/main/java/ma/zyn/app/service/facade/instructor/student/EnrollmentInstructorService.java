package ma.zyn.app.service.facade.instructor.student;

import java.util.List;
import ma.zyn.app.bean.core.student.Enrollment;
import ma.zyn.app.dao.criteria.core.student.EnrollmentCriteria;
import ma.zyn.app.zynerator.service.IService;



public interface EnrollmentInstructorService {



    List<Enrollment> findByStudentId(Long id);
    int deleteByStudentId(Long id);
    long countByStudentEmail(String email);
    List<Enrollment> findByCourseId(Long id);
    int deleteByCourseId(Long id);
    long countByCourseCode(String code);
    List<Enrollment> findByEnrollmentStateCode(String code);
    List<Enrollment> findByEnrollmentStateId(Long id);
    int deleteByEnrollmentStateId(Long id);
    int deleteByEnrollmentStateCode(String code);
    long countByEnrollmentStateCode(String code);




	Enrollment create(Enrollment t);

    Enrollment update(Enrollment t);

    List<Enrollment> update(List<Enrollment> ts,boolean createIfNotExist);

    Enrollment findById(Long id);

    Enrollment findOrSave(Enrollment t);

    Enrollment findByReferenceEntity(Enrollment t);

    Enrollment findWithAssociatedLists(Long id);

    List<Enrollment> findAllOptimized();

    List<Enrollment> findAll();

    List<Enrollment> findByCriteria(EnrollmentCriteria criteria);

    List<Enrollment> findPaginatedByCriteria(EnrollmentCriteria criteria, int page, int pageSize, String order, String sortField);

    int getDataSize(EnrollmentCriteria criteria);

    List<Enrollment> delete(List<Enrollment> ts);

    boolean deleteById(Long id);

    List<List<Enrollment>> getToBeSavedAndToBeDeleted(List<Enrollment> oldList, List<Enrollment> newList);

    public String uploadFile(String checksumOld, String tempUpladedFile,String destinationFilePath) throws Exception ;

}
