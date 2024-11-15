package ma.zyn.app.service.facade.instructor.student;

import java.util.List;
import ma.zyn.app.bean.core.student.Review;
import ma.zyn.app.dao.criteria.core.student.ReviewCriteria;
import ma.zyn.app.zynerator.service.IService;



public interface ReviewInstructorService {



    List<Review> findByStudentId(Long id);
    int deleteByStudentId(Long id);
    long countByStudentEmail(String email);
    List<Review> findByCourseId(Long id);
    int deleteByCourseId(Long id);
    long countByCourseCode(String code);




	Review create(Review t);

    Review update(Review t);

    List<Review> update(List<Review> ts,boolean createIfNotExist);

    Review findById(Long id);

    Review findOrSave(Review t);

    Review findByReferenceEntity(Review t);

    Review findWithAssociatedLists(Long id);

    List<Review> findAllOptimized();

    List<Review> findAll();

    List<Review> findByCriteria(ReviewCriteria criteria);

    List<Review> findPaginatedByCriteria(ReviewCriteria criteria, int page, int pageSize, String order, String sortField);

    int getDataSize(ReviewCriteria criteria);

    List<Review> delete(List<Review> ts);

    boolean deleteById(Long id);

    List<List<Review>> getToBeSavedAndToBeDeleted(List<Review> oldList, List<Review> newList);

    public String uploadFile(String checksumOld, String tempUpladedFile,String destinationFilePath) throws Exception ;

}
