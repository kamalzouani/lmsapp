package ma.zyn.app.service.impl.instructor.student;


import ma.zyn.app.zynerator.exception.EntityNotFoundException;
import ma.zyn.app.bean.core.student.EnrollmentState;
import ma.zyn.app.dao.criteria.core.student.EnrollmentStateCriteria;
import ma.zyn.app.dao.facade.core.student.EnrollmentStateDao;
import ma.zyn.app.dao.specification.core.student.EnrollmentStateSpecification;
import ma.zyn.app.service.facade.instructor.student.EnrollmentStateInstructorService;
import ma.zyn.app.zynerator.service.AbstractServiceImpl;
import static ma.zyn.app.zynerator.util.ListUtil.*;

import org.springframework.stereotype.Service;
import java.util.List;
import java.util.ArrayList;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;

import ma.zyn.app.zynerator.util.RefelexivityUtil;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;


import java.util.List;
@Service
public class EnrollmentStateInstructorServiceImpl implements EnrollmentStateInstructorService {

    @Transactional(propagation = Propagation.REQUIRED, rollbackFor = Exception.class, readOnly = false)
    public EnrollmentState update(EnrollmentState t) {
        EnrollmentState loadedItem = dao.findById(t.getId()).orElse(null);
        if (loadedItem == null) {
            throw new EntityNotFoundException("errors.notFound", new String[]{EnrollmentState.class.getSimpleName(), t.getId().toString()});
        } else {
            dao.save(t);
            return loadedItem;
        }
    }

    public EnrollmentState findById(Long id) {
        return dao.findById(id).orElse(null);
    }


    public EnrollmentState findOrSave(EnrollmentState t) {
        if (t != null) {
            EnrollmentState result = findByReferenceEntity(t);
            if (result == null) {
                return dao.save(t);
            } else {
                return result;
            }
        }
        return null;
    }

    public List<EnrollmentState> findAll() {
        return dao.findAll();
    }

    public List<EnrollmentState> findByCriteria(EnrollmentStateCriteria criteria) {
        List<EnrollmentState> content = null;
        if (criteria != null) {
            EnrollmentStateSpecification mySpecification = constructSpecification(criteria);
            content = dao.findAll(mySpecification);
        } else {
            content = dao.findAll();
        }
        return content;

    }


    private EnrollmentStateSpecification constructSpecification(EnrollmentStateCriteria criteria) {
        EnrollmentStateSpecification mySpecification =  (EnrollmentStateSpecification) RefelexivityUtil.constructObjectUsingOneParam(EnrollmentStateSpecification.class, criteria);
        return mySpecification;
    }

    public List<EnrollmentState> findPaginatedByCriteria(EnrollmentStateCriteria criteria, int page, int pageSize, String order, String sortField) {
        EnrollmentStateSpecification mySpecification = constructSpecification(criteria);
        order = (order != null && !order.isEmpty()) ? order : "desc";
        sortField = (sortField != null && !sortField.isEmpty()) ? sortField : "id";
        Pageable pageable = PageRequest.of(page, pageSize, Sort.Direction.fromString(order), sortField);
        return dao.findAll(mySpecification, pageable).getContent();
    }

    public int getDataSize(EnrollmentStateCriteria criteria) {
        EnrollmentStateSpecification mySpecification = constructSpecification(criteria);
        mySpecification.setDistinct(true);
        return ((Long) dao.count(mySpecification)).intValue();
    }

    @Transactional(propagation = Propagation.REQUIRED, rollbackFor = Exception.class, readOnly = false)
	public boolean deleteById(Long id) {
        boolean condition = (id != null);
        if (condition) {
            dao.deleteById(id);
        }
        return condition;
    }




    @Transactional(propagation = Propagation.REQUIRED, rollbackFor = Exception.class, readOnly = false)
    public List<EnrollmentState> delete(List<EnrollmentState> list) {
		List<EnrollmentState> result = new ArrayList();
        if (list != null) {
            for (EnrollmentState t : list) {
                if(dao.findById(t.getId()).isEmpty()){
					result.add(t);
				}else{
                    dao.deleteById(t.getId());
                }
            }
        }
		return result;
    }

    @Transactional(propagation = Propagation.REQUIRED, rollbackFor = Exception.class, readOnly = false)
    public EnrollmentState create(EnrollmentState t) {
        EnrollmentState loaded = findByReferenceEntity(t);
        EnrollmentState saved;
        if (loaded == null) {
            saved = dao.save(t);
        }else {
            saved = null;
        }
        return saved;
    }

    public EnrollmentState findWithAssociatedLists(Long id){
        EnrollmentState result = dao.findById(id).orElse(null);
        return result;
    }

	@Transactional(propagation = Propagation.REQUIRED, rollbackFor = Exception.class, readOnly = false)
    public List<EnrollmentState> update(List<EnrollmentState> ts, boolean createIfNotExist) {
        List<EnrollmentState> result = new ArrayList<>();
        if (ts != null) {
            for (EnrollmentState t : ts) {
                if (t.getId() == null) {
                    dao.save(t);
                } else {
                    EnrollmentState loadedItem = dao.findById(t.getId()).orElse(null);
                    if (isEligibleForCreateOrUpdate(createIfNotExist, t, loadedItem)) {
                        dao.save(t);
                    } else {
                        result.add(t);
                    }
                }
            }
        }
        return result;
    }


    private boolean isEligibleForCreateOrUpdate(boolean createIfNotExist, EnrollmentState t, EnrollmentState loadedItem) {
        boolean eligibleForCreateCrud = t.getId() == null;
        boolean eligibleForCreate = (createIfNotExist && (t.getId() == null || loadedItem == null));
        boolean eligibleForUpdate = (t.getId() != null && loadedItem != null);
        return (eligibleForCreateCrud || eligibleForCreate || eligibleForUpdate);
    }









    public EnrollmentState findByReferenceEntity(EnrollmentState t){
        return t==null? null : dao.findByCode(t.getCode());
    }



    public List<EnrollmentState> findAllOptimized() {
        return dao.findAllOptimized();
    }

    @Override
    public List<List<EnrollmentState>> getToBeSavedAndToBeDeleted(List<EnrollmentState> oldList, List<EnrollmentState> newList) {
        List<List<EnrollmentState>> result = new ArrayList<>();
        List<EnrollmentState> resultDelete = new ArrayList<>();
        List<EnrollmentState> resultUpdateOrSave = new ArrayList<>();
        if (isEmpty(oldList) && isNotEmpty(newList)) {
            resultUpdateOrSave.addAll(newList);
        } else if (isEmpty(newList) && isNotEmpty(oldList)) {
            resultDelete.addAll(oldList);
        } else if (isNotEmpty(newList) && isNotEmpty(oldList)) {
			extractToBeSaveOrDelete(oldList, newList, resultUpdateOrSave, resultDelete);
        }
        result.add(resultUpdateOrSave);
        result.add(resultDelete);
        return result;
    }

    private void extractToBeSaveOrDelete(List<EnrollmentState> oldList, List<EnrollmentState> newList, List<EnrollmentState> resultUpdateOrSave, List<EnrollmentState> resultDelete) {
		for (int i = 0; i < oldList.size(); i++) {
                EnrollmentState myOld = oldList.get(i);
                EnrollmentState t = newList.stream().filter(e -> myOld.equals(e)).findFirst().orElse(null);
                if (t != null) {
                    resultUpdateOrSave.add(t); // update
                } else {
                    resultDelete.add(myOld);
                }
            }
            for (int i = 0; i < newList.size(); i++) {
                EnrollmentState myNew = newList.get(i);
                EnrollmentState t = oldList.stream().filter(e -> myNew.equals(e)).findFirst().orElse(null);
                if (t == null) {
                    resultUpdateOrSave.add(myNew); // create
                }
            }
	}

    @Override
    public String uploadFile(String checksumOld, String tempUpladedFile, String destinationFilePath) throws Exception {
        return null;
    }








    public EnrollmentStateInstructorServiceImpl(EnrollmentStateDao dao) {
        this.dao = dao;
    }

    private EnrollmentStateDao dao;
}
