package  ma.zyn.app.dao.specification.core.student;

import ma.zyn.app.dao.criteria.core.student.EnrollmentCriteria;
import ma.zyn.app.bean.core.student.Enrollment;
import ma.zyn.app.zynerator.specification.AbstractSpecification;


public class EnrollmentSpecification extends  AbstractSpecification<EnrollmentCriteria, Enrollment>  {

    @Override
    public void constructPredicates() {
        addPredicateId("id", criteria);
        addPredicate("enrollmentDate", criteria.getEnrollmentDate(), criteria.getEnrollmentDateFrom(), criteria.getEnrollmentDateTo());
        addPredicateFk("student","id", criteria.getStudent()==null?null:criteria.getStudent().getId());
        addPredicateFk("student","id", criteria.getStudents());
        addPredicateFk("student","email", criteria.getStudent()==null?null:criteria.getStudent().getEmail());
        addPredicateFk("course","id", criteria.getCourse()==null?null:criteria.getCourse().getId());
        addPredicateFk("course","id", criteria.getCourses());
        addPredicateFk("course","code", criteria.getCourse()==null?null:criteria.getCourse().getCode());
        addPredicateFk("enrollmentState","id", criteria.getEnrollmentState()==null?null:criteria.getEnrollmentState().getId());
        addPredicateFk("enrollmentState","id", criteria.getEnrollmentStates());
        addPredicateFk("enrollmentState","code", criteria.getEnrollmentState()==null?null:criteria.getEnrollmentState().getCode());
    }

    public EnrollmentSpecification(EnrollmentCriteria criteria) {
        super(criteria);
    }

    public EnrollmentSpecification(EnrollmentCriteria criteria, boolean distinct) {
        super(criteria, distinct);
    }

}
