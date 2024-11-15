package  ma.zyn.app.dao.specification.core.course;

import ma.zyn.app.dao.criteria.core.course.CategoryCriteria;
import ma.zyn.app.bean.core.course.Category;
import ma.zyn.app.zynerator.specification.AbstractSpecification;


public class CategorySpecification extends  AbstractSpecification<CategoryCriteria, Category>  {

    @Override
    public void constructPredicates() {
        addPredicateId("id", criteria);
        addPredicate("code", criteria.getCode(),criteria.getCodeLike());
        addPredicate("label", criteria.getLabel(),criteria.getLabelLike());
        addPredicate("style", criteria.getStyle(),criteria.getStyleLike());
    }

    public CategorySpecification(CategoryCriteria criteria) {
        super(criteria);
    }

    public CategorySpecification(CategoryCriteria criteria, boolean distinct) {
        super(criteria, distinct);
    }

}
