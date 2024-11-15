package ma.zyn.app.service.impl.admin.student;


import ma.zyn.app.zynerator.exception.EntityNotFoundException;
import ma.zyn.app.bean.core.student.PaymentState;
import ma.zyn.app.dao.criteria.core.student.PaymentStateCriteria;
import ma.zyn.app.dao.facade.core.student.PaymentStateDao;
import ma.zyn.app.dao.specification.core.student.PaymentStateSpecification;
import ma.zyn.app.service.facade.admin.student.PaymentStateAdminService;
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
public class PaymentStateAdminServiceImpl implements PaymentStateAdminService {

    @Transactional(propagation = Propagation.REQUIRED, rollbackFor = Exception.class, readOnly = false)
    public PaymentState update(PaymentState t) {
        PaymentState loadedItem = dao.findById(t.getId()).orElse(null);
        if (loadedItem == null) {
            throw new EntityNotFoundException("errors.notFound", new String[]{PaymentState.class.getSimpleName(), t.getId().toString()});
        } else {
            dao.save(t);
            return loadedItem;
        }
    }

    public PaymentState findById(Long id) {
        return dao.findById(id).orElse(null);
    }


    public PaymentState findOrSave(PaymentState t) {
        if (t != null) {
            PaymentState result = findByReferenceEntity(t);
            if (result == null) {
                return dao.save(t);
            } else {
                return result;
            }
        }
        return null;
    }

    public List<PaymentState> findAll() {
        return dao.findAll();
    }

    public List<PaymentState> findByCriteria(PaymentStateCriteria criteria) {
        List<PaymentState> content = null;
        if (criteria != null) {
            PaymentStateSpecification mySpecification = constructSpecification(criteria);
            content = dao.findAll(mySpecification);
        } else {
            content = dao.findAll();
        }
        return content;

    }


    private PaymentStateSpecification constructSpecification(PaymentStateCriteria criteria) {
        PaymentStateSpecification mySpecification =  (PaymentStateSpecification) RefelexivityUtil.constructObjectUsingOneParam(PaymentStateSpecification.class, criteria);
        return mySpecification;
    }

    public List<PaymentState> findPaginatedByCriteria(PaymentStateCriteria criteria, int page, int pageSize, String order, String sortField) {
        PaymentStateSpecification mySpecification = constructSpecification(criteria);
        order = (order != null && !order.isEmpty()) ? order : "desc";
        sortField = (sortField != null && !sortField.isEmpty()) ? sortField : "id";
        Pageable pageable = PageRequest.of(page, pageSize, Sort.Direction.fromString(order), sortField);
        return dao.findAll(mySpecification, pageable).getContent();
    }

    public int getDataSize(PaymentStateCriteria criteria) {
        PaymentStateSpecification mySpecification = constructSpecification(criteria);
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
    public List<PaymentState> delete(List<PaymentState> list) {
		List<PaymentState> result = new ArrayList();
        if (list != null) {
            for (PaymentState t : list) {
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
    public PaymentState create(PaymentState t) {
        PaymentState loaded = findByReferenceEntity(t);
        PaymentState saved;
        if (loaded == null) {
            saved = dao.save(t);
        }else {
            saved = null;
        }
        return saved;
    }

    public PaymentState findWithAssociatedLists(Long id){
        PaymentState result = dao.findById(id).orElse(null);
        return result;
    }

	@Transactional(propagation = Propagation.REQUIRED, rollbackFor = Exception.class, readOnly = false)
    public List<PaymentState> update(List<PaymentState> ts, boolean createIfNotExist) {
        List<PaymentState> result = new ArrayList<>();
        if (ts != null) {
            for (PaymentState t : ts) {
                if (t.getId() == null) {
                    dao.save(t);
                } else {
                    PaymentState loadedItem = dao.findById(t.getId()).orElse(null);
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


    private boolean isEligibleForCreateOrUpdate(boolean createIfNotExist, PaymentState t, PaymentState loadedItem) {
        boolean eligibleForCreateCrud = t.getId() == null;
        boolean eligibleForCreate = (createIfNotExist && (t.getId() == null || loadedItem == null));
        boolean eligibleForUpdate = (t.getId() != null && loadedItem != null);
        return (eligibleForCreateCrud || eligibleForCreate || eligibleForUpdate);
    }









    public PaymentState findByReferenceEntity(PaymentState t){
        return t==null? null : dao.findByCode(t.getCode());
    }



    public List<PaymentState> findAllOptimized() {
        return dao.findAllOptimized();
    }

    @Override
    public List<List<PaymentState>> getToBeSavedAndToBeDeleted(List<PaymentState> oldList, List<PaymentState> newList) {
        List<List<PaymentState>> result = new ArrayList<>();
        List<PaymentState> resultDelete = new ArrayList<>();
        List<PaymentState> resultUpdateOrSave = new ArrayList<>();
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

    private void extractToBeSaveOrDelete(List<PaymentState> oldList, List<PaymentState> newList, List<PaymentState> resultUpdateOrSave, List<PaymentState> resultDelete) {
		for (int i = 0; i < oldList.size(); i++) {
                PaymentState myOld = oldList.get(i);
                PaymentState t = newList.stream().filter(e -> myOld.equals(e)).findFirst().orElse(null);
                if (t != null) {
                    resultUpdateOrSave.add(t); // update
                } else {
                    resultDelete.add(myOld);
                }
            }
            for (int i = 0; i < newList.size(); i++) {
                PaymentState myNew = newList.get(i);
                PaymentState t = oldList.stream().filter(e -> myNew.equals(e)).findFirst().orElse(null);
                if (t == null) {
                    resultUpdateOrSave.add(myNew); // create
                }
            }
	}

    @Override
    public String uploadFile(String checksumOld, String tempUpladedFile, String destinationFilePath) throws Exception {
        return null;
    }








    public PaymentStateAdminServiceImpl(PaymentStateDao dao) {
        this.dao = dao;
    }

    private PaymentStateDao dao;
}
