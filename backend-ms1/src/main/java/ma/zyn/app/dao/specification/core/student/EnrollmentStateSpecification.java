package  ma.zyn.app.dao.specification.core.student;

import ma.zyn.app.dao.criteria.core.student.EnrollmentStateCriteria;
import ma.zyn.app.bean.core.student.EnrollmentState;
import ma.zyn.app.zynerator.specification.AbstractSpecification;


public class EnrollmentStateSpecification extends  AbstractSpecification<EnrollmentStateCriteria, EnrollmentState>  {

    @Override
    public void constructPredicates() {
        addPredicateId("id", criteria);
        addPredicate("code", criteria.getCode(),criteria.getCodeLike());
        addPredicate("label", criteria.getLabel(),criteria.getLabelLike());
        addPredicate("style", criteria.getStyle(),criteria.getStyleLike());
    }

    public EnrollmentStateSpecification(EnrollmentStateCriteria criteria) {
        super(criteria);
    }

    public EnrollmentStateSpecification(EnrollmentStateCriteria criteria, boolean distinct) {
        super(criteria, distinct);
    }

}
