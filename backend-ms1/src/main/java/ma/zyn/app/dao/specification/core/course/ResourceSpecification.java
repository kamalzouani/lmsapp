package  ma.zyn.app.dao.specification.core.course;

import ma.zyn.app.dao.criteria.core.course.ResourceCriteria;
import ma.zyn.app.bean.core.course.Resource;
import ma.zyn.app.zynerator.specification.AbstractSpecification;


public class ResourceSpecification extends  AbstractSpecification<ResourceCriteria, Resource>  {

    @Override
    public void constructPredicates() {
        addPredicateId("id", criteria);
        addPredicate("type", criteria.getType(),criteria.getTypeLike());
        addPredicate("url", criteria.getUrl(),criteria.getUrlLike());
        addPredicate("file", criteria.getFile(),criteria.getFileLike());
        addPredicateFk("lesson","id", criteria.getLesson()==null?null:criteria.getLesson().getId());
        addPredicateFk("lesson","id", criteria.getLessons());
    }

    public ResourceSpecification(ResourceCriteria criteria) {
        super(criteria);
    }

    public ResourceSpecification(ResourceCriteria criteria, boolean distinct) {
        super(criteria, distinct);
    }

}
