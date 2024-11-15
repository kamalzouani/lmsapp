package  ma.zyn.app.dao.specification.core.course;

import ma.zyn.app.dao.criteria.core.course.CurriculumCriteria;
import ma.zyn.app.bean.core.course.Curriculum;
import ma.zyn.app.zynerator.specification.AbstractSpecification;


public class CurriculumSpecification extends  AbstractSpecification<CurriculumCriteria, Curriculum>  {

    @Override
    public void constructPredicates() {
        addPredicateId("id", criteria);
        addPredicateFk("course","id", criteria.getCourse()==null?null:criteria.getCourse().getId());
        addPredicateFk("course","id", criteria.getCourses());
        addPredicateFk("course","code", criteria.getCourse()==null?null:criteria.getCourse().getCode());
    }

    public CurriculumSpecification(CurriculumCriteria criteria) {
        super(criteria);
    }

    public CurriculumSpecification(CurriculumCriteria criteria, boolean distinct) {
        super(criteria, distinct);
    }

}
