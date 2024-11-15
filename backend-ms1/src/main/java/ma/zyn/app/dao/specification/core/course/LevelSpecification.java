package  ma.zyn.app.dao.specification.core.course;

import ma.zyn.app.dao.criteria.core.course.LevelCriteria;
import ma.zyn.app.bean.core.course.Level;
import ma.zyn.app.zynerator.specification.AbstractSpecification;


public class LevelSpecification extends  AbstractSpecification<LevelCriteria, Level>  {

    @Override
    public void constructPredicates() {
        addPredicateId("id", criteria);
        addPredicate("code", criteria.getCode(),criteria.getCodeLike());
        addPredicate("label", criteria.getLabel(),criteria.getLabelLike());
        addPredicate("style", criteria.getStyle(),criteria.getStyleLike());
    }

    public LevelSpecification(LevelCriteria criteria) {
        super(criteria);
    }

    public LevelSpecification(LevelCriteria criteria, boolean distinct) {
        super(criteria, distinct);
    }

}
