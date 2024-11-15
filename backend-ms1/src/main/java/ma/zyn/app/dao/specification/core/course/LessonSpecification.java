package  ma.zyn.app.dao.specification.core.course;

import ma.zyn.app.dao.criteria.core.course.LessonCriteria;
import ma.zyn.app.bean.core.course.Lesson;
import ma.zyn.app.zynerator.specification.AbstractSpecification;


public class LessonSpecification extends  AbstractSpecification<LessonCriteria, Lesson>  {

    @Override
    public void constructPredicates() {
        addPredicateId("id", criteria);
        addPredicate("label", criteria.getLabel(),criteria.getLabelLike());
        addPredicate("description", criteria.getDescription(),criteria.getDescriptionLike());
        addPredicateFk("module","id", criteria.getModule()==null?null:criteria.getModule().getId());
        addPredicateFk("module","id", criteria.getModules());
    }

    public LessonSpecification(LessonCriteria criteria) {
        super(criteria);
    }

    public LessonSpecification(LessonCriteria criteria, boolean distinct) {
        super(criteria, distinct);
    }

}
