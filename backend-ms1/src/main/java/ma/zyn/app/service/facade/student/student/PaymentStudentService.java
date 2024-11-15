package ma.zyn.app.service.facade.student.student;

import java.util.List;
import ma.zyn.app.bean.core.student.Payment;
import ma.zyn.app.dao.criteria.core.student.PaymentCriteria;
import ma.zyn.app.zynerator.service.IService;



public interface PaymentStudentService {



    List<Payment> findByStudentId(Long id);
    int deleteByStudentId(Long id);
    long countByStudentEmail(String email);
    List<Payment> findByCourseId(Long id);
    int deleteByCourseId(Long id);
    long countByCourseCode(String code);
    List<Payment> findByPaymentStateCode(String code);
    List<Payment> findByPaymentStateId(Long id);
    int deleteByPaymentStateId(Long id);
    int deleteByPaymentStateCode(String code);
    long countByPaymentStateCode(String code);




	Payment create(Payment t);

    Payment update(Payment t);

    List<Payment> update(List<Payment> ts,boolean createIfNotExist);

    Payment findById(Long id);

    Payment findOrSave(Payment t);

    Payment findByReferenceEntity(Payment t);

    Payment findWithAssociatedLists(Long id);

    List<Payment> findAllOptimized();

    List<Payment> findAll();

    List<Payment> findByCriteria(PaymentCriteria criteria);

    List<Payment> findPaginatedByCriteria(PaymentCriteria criteria, int page, int pageSize, String order, String sortField);

    int getDataSize(PaymentCriteria criteria);

    List<Payment> delete(List<Payment> ts);

    boolean deleteById(Long id);

    List<List<Payment>> getToBeSavedAndToBeDeleted(List<Payment> oldList, List<Payment> newList);

    public String uploadFile(String checksumOld, String tempUpladedFile,String destinationFilePath) throws Exception ;

}
