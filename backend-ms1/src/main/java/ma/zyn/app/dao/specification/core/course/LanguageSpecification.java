package  ma.zyn.app.dao.specification.core.course;

import ma.zyn.app.dao.criteria.core.course.LanguageCriteria;
import ma.zyn.app.bean.core.course.Language;
import ma.zyn.app.zynerator.specification.AbstractSpecification;


public class LanguageSpecification extends  AbstractSpecification<LanguageCriteria, Language>  {

    @Override
    public void constructPredicates() {
        addPredicateId("id", criteria);
        addPredicate("code", criteria.getCode(),criteria.getCodeLike());
        addPredicate("label", criteria.getLabel(),criteria.getLabelLike());
        addPredicate("style", criteria.getStyle(),criteria.getStyleLike());
    }

    public LanguageSpecification(LanguageCriteria criteria) {
        super(criteria);
    }

    public LanguageSpecification(LanguageCriteria criteria, boolean distinct) {
        super(criteria, distinct);
    }

}
