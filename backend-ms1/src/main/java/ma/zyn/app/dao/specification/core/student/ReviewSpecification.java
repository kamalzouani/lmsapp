package  ma.zyn.app.dao.specification.core.student;

import ma.zyn.app.dao.criteria.core.student.ReviewCriteria;
import ma.zyn.app.bean.core.student.Review;
import ma.zyn.app.zynerator.specification.AbstractSpecification;


public class ReviewSpecification extends  AbstractSpecification<ReviewCriteria, Review>  {

    @Override
    public void constructPredicates() {
        addPredicateId("id", criteria);
        addPredicateBigDecimal("rating", criteria.getRating(), criteria.getRatingMin(), criteria.getRatingMax());
        addPredicate("reviewDate", criteria.getReviewDate(), criteria.getReviewDateFrom(), criteria.getReviewDateTo());
        addPredicateFk("student","id", criteria.getStudent()==null?null:criteria.getStudent().getId());
        addPredicateFk("student","id", criteria.getStudents());
        addPredicateFk("student","email", criteria.getStudent()==null?null:criteria.getStudent().getEmail());
        addPredicateFk("course","id", criteria.getCourse()==null?null:criteria.getCourse().getId());
        addPredicateFk("course","id", criteria.getCourses());
        addPredicateFk("course","code", criteria.getCourse()==null?null:criteria.getCourse().getCode());
    }

    public ReviewSpecification(ReviewCriteria criteria) {
        super(criteria);
    }

    public ReviewSpecification(ReviewCriteria criteria, boolean distinct) {
        super(criteria, distinct);
    }

}
