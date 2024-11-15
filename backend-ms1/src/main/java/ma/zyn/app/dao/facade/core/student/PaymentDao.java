package ma.zyn.app.dao.facade.core.student;

import ma.zyn.app.zynerator.repository.AbstractRepository;
import ma.zyn.app.bean.core.student.Payment;
import org.springframework.stereotype.Repository;
import java.util.List;


@Repository
public interface PaymentDao extends AbstractRepository<Payment,Long>  {

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


}
