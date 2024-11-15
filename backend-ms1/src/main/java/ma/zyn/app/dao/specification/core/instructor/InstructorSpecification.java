package  ma.zyn.app.dao.specification.core.instructor;

import ma.zyn.app.dao.criteria.core.instructor.InstructorCriteria;
import ma.zyn.app.bean.core.instructor.Instructor;
import ma.zyn.app.zynerator.specification.AbstractSpecification;


public class InstructorSpecification extends  AbstractSpecification<InstructorCriteria, Instructor>  {

    @Override
    public void constructPredicates() {
        addPredicateId("id", criteria);
        addPredicateBool("credentialsNonExpired", criteria.getCredentialsNonExpired());
        addPredicateBool("accountNonExpired", criteria.getAccountNonExpired());
        addPredicate("username", criteria.getUsername(),criteria.getUsernameLike());
        addPredicateBool("passwordChanged", criteria.getPasswordChanged());
        addPredicateBool("accountNonLocked", criteria.getAccountNonLocked());
        addPredicate("password", criteria.getPassword(),criteria.getPasswordLike());
        addPredicate("email", criteria.getEmail(),criteria.getEmailLike());
        addPredicateBool("enabled", criteria.getEnabled());
    }

    public InstructorSpecification(InstructorCriteria criteria) {
        super(criteria);
    }

    public InstructorSpecification(InstructorCriteria criteria, boolean distinct) {
        super(criteria, distinct);
    }

}
