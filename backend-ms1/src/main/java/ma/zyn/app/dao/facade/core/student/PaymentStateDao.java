package ma.zyn.app.dao.facade.core.student;

import org.springframework.data.jpa.repository.Query;
import ma.zyn.app.zynerator.repository.AbstractRepository;
import ma.zyn.app.bean.core.student.PaymentState;
import org.springframework.stereotype.Repository;
import ma.zyn.app.bean.core.student.PaymentState;
import java.util.List;


@Repository
public interface PaymentStateDao extends AbstractRepository<PaymentState,Long>  {
    PaymentState findByCode(String code);
    int deleteByCode(String code);


    @Query("SELECT NEW PaymentState(item.id,item.label) FROM PaymentState item")
    List<PaymentState> findAllOptimized();

}
