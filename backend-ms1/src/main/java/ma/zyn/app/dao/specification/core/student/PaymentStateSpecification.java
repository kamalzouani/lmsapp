package  ma.zyn.app.dao.specification.core.student;

import ma.zyn.app.dao.criteria.core.student.PaymentStateCriteria;
import ma.zyn.app.bean.core.student.PaymentState;
import ma.zyn.app.zynerator.specification.AbstractSpecification;


public class PaymentStateSpecification extends  AbstractSpecification<PaymentStateCriteria, PaymentState>  {

    @Override
    public void constructPredicates() {
        addPredicateId("id", criteria);
        addPredicate("code", criteria.getCode(),criteria.getCodeLike());
        addPredicate("label", criteria.getLabel(),criteria.getLabelLike());
        addPredicate("style", criteria.getStyle(),criteria.getStyleLike());
    }

    public PaymentStateSpecification(PaymentStateCriteria criteria) {
        super(criteria);
    }

    public PaymentStateSpecification(PaymentStateCriteria criteria, boolean distinct) {
        super(criteria, distinct);
    }

}
