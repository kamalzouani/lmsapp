package ma.zyn.app.service.facade.instructor.student;

import java.util.List;
import ma.zyn.app.bean.core.student.EnrollmentState;
import ma.zyn.app.dao.criteria.core.student.EnrollmentStateCriteria;
import ma.zyn.app.zynerator.service.IService;



public interface EnrollmentStateInstructorService {







	EnrollmentState create(EnrollmentState t);

    EnrollmentState update(EnrollmentState t);

    List<EnrollmentState> update(List<EnrollmentState> ts,boolean createIfNotExist);

    EnrollmentState findById(Long id);

    EnrollmentState findOrSave(EnrollmentState t);

    EnrollmentState findByReferenceEntity(EnrollmentState t);

    EnrollmentState findWithAssociatedLists(Long id);

    List<EnrollmentState> findAllOptimized();

    List<EnrollmentState> findAll();

    List<EnrollmentState> findByCriteria(EnrollmentStateCriteria criteria);

    List<EnrollmentState> findPaginatedByCriteria(EnrollmentStateCriteria criteria, int page, int pageSize, String order, String sortField);

    int getDataSize(EnrollmentStateCriteria criteria);

    List<EnrollmentState> delete(List<EnrollmentState> ts);

    boolean deleteById(Long id);

    List<List<EnrollmentState>> getToBeSavedAndToBeDeleted(List<EnrollmentState> oldList, List<EnrollmentState> newList);

    public String uploadFile(String checksumOld, String tempUpladedFile,String destinationFilePath) throws Exception ;

}
