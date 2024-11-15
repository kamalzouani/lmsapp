package ma.zyn.app.service.facade.instructor.student;

import java.util.List;
import ma.zyn.app.bean.core.student.PaymentState;
import ma.zyn.app.dao.criteria.core.student.PaymentStateCriteria;
import ma.zyn.app.zynerator.service.IService;



public interface PaymentStateInstructorService {







	PaymentState create(PaymentState t);

    PaymentState update(PaymentState t);

    List<PaymentState> update(List<PaymentState> ts,boolean createIfNotExist);

    PaymentState findById(Long id);

    PaymentState findOrSave(PaymentState t);

    PaymentState findByReferenceEntity(PaymentState t);

    PaymentState findWithAssociatedLists(Long id);

    List<PaymentState> findAllOptimized();

    List<PaymentState> findAll();

    List<PaymentState> findByCriteria(PaymentStateCriteria criteria);

    List<PaymentState> findPaginatedByCriteria(PaymentStateCriteria criteria, int page, int pageSize, String order, String sortField);

    int getDataSize(PaymentStateCriteria criteria);

    List<PaymentState> delete(List<PaymentState> ts);

    boolean deleteById(Long id);

    List<List<PaymentState>> getToBeSavedAndToBeDeleted(List<PaymentState> oldList, List<PaymentState> newList);

    public String uploadFile(String checksumOld, String tempUpladedFile,String destinationFilePath) throws Exception ;

}
