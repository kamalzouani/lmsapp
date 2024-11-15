package  ma.zyn.app.dao.specification.core.course;

import ma.zyn.app.dao.criteria.core.course.CourseCriteria;
import ma.zyn.app.bean.core.course.Course;
import ma.zyn.app.zynerator.specification.AbstractSpecification;


public class CourseSpecification extends  AbstractSpecification<CourseCriteria, Course>  {

    @Override
    public void constructPredicates() {
        addPredicateId("id", criteria);
        addPredicate("code", criteria.getCode(),criteria.getCodeLike());
        addPredicate("label", criteria.getLabel(),criteria.getLabelLike());
        addPredicate("startDate", criteria.getStartDate(), criteria.getStartDateFrom(), criteria.getStartDateTo());
        addPredicate("endDate", criteria.getEndDate(), criteria.getEndDateFrom(), criteria.getEndDateTo());
        addPredicateBigDecimal("duration", criteria.getDuration(), criteria.getDurationMin(), criteria.getDurationMax());
        addPredicateBigDecimal("price", criteria.getPrice(), criteria.getPriceMin(), criteria.getPriceMax());
        addPredicate("requirements", criteria.getRequirements(),criteria.getRequirementsLike());
        addPredicate("learningOutcomes", criteria.getLearningOutcomes(),criteria.getLearningOutcomesLike());
        addPredicateFk("instructor","id", criteria.getInstructor()==null?null:criteria.getInstructor().getId());
        addPredicateFk("instructor","id", criteria.getInstructors());
        addPredicateFk("instructor","email", criteria.getInstructor()==null?null:criteria.getInstructor().getEmail());
        addPredicateFk("category","id", criteria.getCategory()==null?null:criteria.getCategory().getId());
        addPredicateFk("category","id", criteria.getCategorys());
        addPredicateFk("category","code", criteria.getCategory()==null?null:criteria.getCategory().getCode());
        addPredicateFk("level","id", criteria.getLevel()==null?null:criteria.getLevel().getId());
        addPredicateFk("level","id", criteria.getLevels());
        addPredicateFk("level","code", criteria.getLevel()==null?null:criteria.getLevel().getCode());
        addPredicateFk("language","id", criteria.getLanguage()==null?null:criteria.getLanguage().getId());
        addPredicateFk("language","id", criteria.getLanguages());
        addPredicateFk("language","code", criteria.getLanguage()==null?null:criteria.getLanguage().getCode());
        addPredicateFk("curriculum","id", criteria.getCurriculum()==null?null:criteria.getCurriculum().getId());
        addPredicateFk("curriculum","id", criteria.getCurriculums());
    }

    public CourseSpecification(CourseCriteria criteria) {
        super(criteria);
    }

    public CourseSpecification(CourseCriteria criteria, boolean distinct) {
        super(criteria, distinct);
    }

}
