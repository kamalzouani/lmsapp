package  ma.zyn.app.dao.specification.core.student;

import ma.zyn.app.dao.criteria.core.student.PaymentCriteria;
import ma.zyn.app.bean.core.student.Payment;
import ma.zyn.app.zynerator.specification.AbstractSpecification;


public class PaymentSpecification extends  AbstractSpecification<PaymentCriteria, Payment>  {

    @Override
    public void constructPredicates() {
        addPredicateId("id", criteria);
        addPredicateBigDecimal("amount", criteria.getAmount(), criteria.getAmountMin(), criteria.getAmountMax());
        addPredicate("paymentDate", criteria.getPaymentDate(), criteria.getPaymentDateFrom(), criteria.getPaymentDateTo());
        addPredicateFk("student","id", criteria.getStudent()==null?null:criteria.getStudent().getId());
        addPredicateFk("student","id", criteria.getStudents());
        addPredicateFk("student","email", criteria.getStudent()==null?null:criteria.getStudent().getEmail());
        addPredicateFk("course","id", criteria.getCourse()==null?null:criteria.getCourse().getId());
        addPredicateFk("course","id", criteria.getCourses());
        addPredicateFk("course","code", criteria.getCourse()==null?null:criteria.getCourse().getCode());
        addPredicateFk("paymentState","id", criteria.getPaymentState()==null?null:criteria.getPaymentState().getId());
        addPredicateFk("paymentState","id", criteria.getPaymentStates());
        addPredicateFk("paymentState","code", criteria.getPaymentState()==null?null:criteria.getPaymentState().getCode());
    }

    public PaymentSpecification(PaymentCriteria criteria) {
        super(criteria);
    }

    public PaymentSpecification(PaymentCriteria criteria, boolean distinct) {
        super(criteria, distinct);
    }

}
