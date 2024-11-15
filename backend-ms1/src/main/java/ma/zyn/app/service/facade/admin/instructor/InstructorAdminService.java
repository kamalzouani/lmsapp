package ma.zyn.app.service.facade.admin.instructor;

import java.util.List;
import ma.zyn.app.bean.core.instructor.Instructor;
import ma.zyn.app.dao.criteria.core.instructor.InstructorCriteria;
import ma.zyn.app.zynerator.service.IService;



public interface InstructorAdminService {


    Instructor findByUsername(String username);
    boolean changePassword(String username, String newPassword);





	Instructor create(Instructor t);

    Instructor update(Instructor t);

    List<Instructor> update(List<Instructor> ts,boolean createIfNotExist);

    Instructor findById(Long id);

    Instructor findOrSave(Instructor t);

    Instructor findByReferenceEntity(Instructor t);

    Instructor findWithAssociatedLists(Long id);

    List<Instructor> findAllOptimized();

    List<Instructor> findAll();

    List<Instructor> findByCriteria(InstructorCriteria criteria);

    List<Instructor> findPaginatedByCriteria(InstructorCriteria criteria, int page, int pageSize, String order, String sortField);

    int getDataSize(InstructorCriteria criteria);

    List<Instructor> delete(List<Instructor> ts);

    boolean deleteById(Long id);

    List<List<Instructor>> getToBeSavedAndToBeDeleted(List<Instructor> oldList, List<Instructor> newList);

    public String uploadFile(String checksumOld, String tempUpladedFile,String destinationFilePath) throws Exception ;

}
