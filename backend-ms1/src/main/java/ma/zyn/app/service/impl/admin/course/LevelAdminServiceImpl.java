package ma.zyn.app.service.impl.admin.course;


import ma.zyn.app.zynerator.exception.EntityNotFoundException;
import ma.zyn.app.bean.core.course.Level;
import ma.zyn.app.dao.criteria.core.course.LevelCriteria;
import ma.zyn.app.dao.facade.core.course.LevelDao;
import ma.zyn.app.dao.specification.core.course.LevelSpecification;
import ma.zyn.app.service.facade.admin.course.LevelAdminService;
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
public class LevelAdminServiceImpl implements LevelAdminService {

    @Transactional(propagation = Propagation.REQUIRED, rollbackFor = Exception.class, readOnly = false)
    public Level update(Level t) {
        Level loadedItem = dao.findById(t.getId()).orElse(null);
        if (loadedItem == null) {
            throw new EntityNotFoundException("errors.notFound", new String[]{Level.class.getSimpleName(), t.getId().toString()});
        } else {
            dao.save(t);
            return loadedItem;
        }
    }

    public Level findById(Long id) {
        return dao.findById(id).orElse(null);
    }


    public Level findOrSave(Level t) {
        if (t != null) {
            Level result = findByReferenceEntity(t);
            if (result == null) {
                return dao.save(t);
            } else {
                return result;
            }
        }
        return null;
    }

    public List<Level> findAll() {
        return dao.findAll();
    }

    public List<Level> findByCriteria(LevelCriteria criteria) {
        List<Level> content = null;
        if (criteria != null) {
            LevelSpecification mySpecification = constructSpecification(criteria);
            content = dao.findAll(mySpecification);
        } else {
            content = dao.findAll();
        }
        return content;

    }


    private LevelSpecification constructSpecification(LevelCriteria criteria) {
        LevelSpecification mySpecification =  (LevelSpecification) RefelexivityUtil.constructObjectUsingOneParam(LevelSpecification.class, criteria);
        return mySpecification;
    }

    public List<Level> findPaginatedByCriteria(LevelCriteria criteria, int page, int pageSize, String order, String sortField) {
        LevelSpecification mySpecification = constructSpecification(criteria);
        order = (order != null && !order.isEmpty()) ? order : "desc";
        sortField = (sortField != null && !sortField.isEmpty()) ? sortField : "id";
        Pageable pageable = PageRequest.of(page, pageSize, Sort.Direction.fromString(order), sortField);
        return dao.findAll(mySpecification, pageable).getContent();
    }

    public int getDataSize(LevelCriteria criteria) {
        LevelSpecification mySpecification = constructSpecification(criteria);
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
    public List<Level> delete(List<Level> list) {
		List<Level> result = new ArrayList();
        if (list != null) {
            for (Level t : list) {
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
    public Level create(Level t) {
        Level loaded = findByReferenceEntity(t);
        Level saved;
        if (loaded == null) {
            saved = dao.save(t);
        }else {
            saved = null;
        }
        return saved;
    }

    public Level findWithAssociatedLists(Long id){
        Level result = dao.findById(id).orElse(null);
        return result;
    }

	@Transactional(propagation = Propagation.REQUIRED, rollbackFor = Exception.class, readOnly = false)
    public List<Level> update(List<Level> ts, boolean createIfNotExist) {
        List<Level> result = new ArrayList<>();
        if (ts != null) {
            for (Level t : ts) {
                if (t.getId() == null) {
                    dao.save(t);
                } else {
                    Level loadedItem = dao.findById(t.getId()).orElse(null);
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


    private boolean isEligibleForCreateOrUpdate(boolean createIfNotExist, Level t, Level loadedItem) {
        boolean eligibleForCreateCrud = t.getId() == null;
        boolean eligibleForCreate = (createIfNotExist && (t.getId() == null || loadedItem == null));
        boolean eligibleForUpdate = (t.getId() != null && loadedItem != null);
        return (eligibleForCreateCrud || eligibleForCreate || eligibleForUpdate);
    }









    public Level findByReferenceEntity(Level t){
        return t==null? null : dao.findByCode(t.getCode());
    }



    public List<Level> findAllOptimized() {
        return dao.findAllOptimized();
    }

    @Override
    public List<List<Level>> getToBeSavedAndToBeDeleted(List<Level> oldList, List<Level> newList) {
        List<List<Level>> result = new ArrayList<>();
        List<Level> resultDelete = new ArrayList<>();
        List<Level> resultUpdateOrSave = new ArrayList<>();
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

    private void extractToBeSaveOrDelete(List<Level> oldList, List<Level> newList, List<Level> resultUpdateOrSave, List<Level> resultDelete) {
		for (int i = 0; i < oldList.size(); i++) {
                Level myOld = oldList.get(i);
                Level t = newList.stream().filter(e -> myOld.equals(e)).findFirst().orElse(null);
                if (t != null) {
                    resultUpdateOrSave.add(t); // update
                } else {
                    resultDelete.add(myOld);
                }
            }
            for (int i = 0; i < newList.size(); i++) {
                Level myNew = newList.get(i);
                Level t = oldList.stream().filter(e -> myNew.equals(e)).findFirst().orElse(null);
                if (t == null) {
                    resultUpdateOrSave.add(myNew); // create
                }
            }
	}

    @Override
    public String uploadFile(String checksumOld, String tempUpladedFile, String destinationFilePath) throws Exception {
        return null;
    }








    public LevelAdminServiceImpl(LevelDao dao) {
        this.dao = dao;
    }

    private LevelDao dao;
}
