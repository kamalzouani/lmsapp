package  ma.zyn.app.dao.specification.core.course;

import ma.zyn.app.dao.criteria.core.course.ModuleCriteria;
import ma.zyn.app.bean.core.course.Module;
import ma.zyn.app.zynerator.specification.AbstractSpecification;


public class ModuleSpecification extends  AbstractSpecification<ModuleCriteria, Module>  {

    @Override
    public void constructPredicates() {
        addPredicateId("id", criteria);
        addPredicate("label", criteria.getLabel(),criteria.getLabelLike());
        addPredicate("description", criteria.getDescription(),criteria.getDescriptionLike());
        addPredicateFk("curriculum","id", criteria.getCurriculum()==null?null:criteria.getCurriculum().getId());
        addPredicateFk("curriculum","id", criteria.getCurriculums());
    }

    public ModuleSpecification(ModuleCriteria criteria) {
        super(criteria);
    }

    public ModuleSpecification(ModuleCriteria criteria, boolean distinct) {
        super(criteria, distinct);
    }

}
