package ma.zyn.app.service.impl.instructor.course;


import ma.zyn.app.zynerator.exception.EntityNotFoundException;
import ma.zyn.app.bean.core.course.Language;
import ma.zyn.app.dao.criteria.core.course.LanguageCriteria;
import ma.zyn.app.dao.facade.core.course.LanguageDao;
import ma.zyn.app.dao.specification.core.course.LanguageSpecification;
import ma.zyn.app.service.facade.instructor.course.LanguageInstructorService;
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
public class LanguageInstructorServiceImpl implements LanguageInstructorService {

    @Transactional(propagation = Propagation.REQUIRED, rollbackFor = Exception.class, readOnly = false)
    public Language update(Language t) {
        Language loadedItem = dao.findById(t.getId()).orElse(null);
        if (loadedItem == null) {
            throw new EntityNotFoundException("errors.notFound", new String[]{Language.class.getSimpleName(), t.getId().toString()});
        } else {
            dao.save(t);
            return loadedItem;
        }
    }

    public Language findById(Long id) {
        return dao.findById(id).orElse(null);
    }


    public Language findOrSave(Language t) {
        if (t != null) {
            Language result = findByReferenceEntity(t);
            if (result == null) {
                return dao.save(t);
            } else {
                return result;
            }
        }
        return null;
    }

    public List<Language> findAll() {
        return dao.findAll();
    }

    public List<Language> findByCriteria(LanguageCriteria criteria) {
        List<Language> content = null;
        if (criteria != null) {
            LanguageSpecification mySpecification = constructSpecification(criteria);
            content = dao.findAll(mySpecification);
        } else {
            content = dao.findAll();
        }
        return content;

    }


    private LanguageSpecification constructSpecification(LanguageCriteria criteria) {
        LanguageSpecification mySpecification =  (LanguageSpecification) RefelexivityUtil.constructObjectUsingOneParam(LanguageSpecification.class, criteria);
        return mySpecification;
    }

    public List<Language> findPaginatedByCriteria(LanguageCriteria criteria, int page, int pageSize, String order, String sortField) {
        LanguageSpecification mySpecification = constructSpecification(criteria);
        order = (order != null && !order.isEmpty()) ? order : "desc";
        sortField = (sortField != null && !sortField.isEmpty()) ? sortField : "id";
        Pageable pageable = PageRequest.of(page, pageSize, Sort.Direction.fromString(order), sortField);
        return dao.findAll(mySpecification, pageable).getContent();
    }

    public int getDataSize(LanguageCriteria criteria) {
        LanguageSpecification mySpecification = constructSpecification(criteria);
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
    public List<Language> delete(List<Language> list) {
		List<Language> result = new ArrayList();
        if (list != null) {
            for (Language t : list) {
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
    public Language create(Language t) {
        Language loaded = findByReferenceEntity(t);
        Language saved;
        if (loaded == null) {
            saved = dao.save(t);
        }else {
            saved = null;
        }
        return saved;
    }

    public Language findWithAssociatedLists(Long id){
        Language result = dao.findById(id).orElse(null);
        return result;
    }

	@Transactional(propagation = Propagation.REQUIRED, rollbackFor = Exception.class, readOnly = false)
    public List<Language> update(List<Language> ts, boolean createIfNotExist) {
        List<Language> result = new ArrayList<>();
        if (ts != null) {
            for (Language t : ts) {
                if (t.getId() == null) {
                    dao.save(t);
                } else {
                    Language loadedItem = dao.findById(t.getId()).orElse(null);
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


    private boolean isEligibleForCreateOrUpdate(boolean createIfNotExist, Language t, Language loadedItem) {
        boolean eligibleForCreateCrud = t.getId() == null;
        boolean eligibleForCreate = (createIfNotExist && (t.getId() == null || loadedItem == null));
        boolean eligibleForUpdate = (t.getId() != null && loadedItem != null);
        return (eligibleForCreateCrud || eligibleForCreate || eligibleForUpdate);
    }









    public Language findByReferenceEntity(Language t){
        return t==null? null : dao.findByCode(t.getCode());
    }



    public List<Language> findAllOptimized() {
        return dao.findAllOptimized();
    }

    @Override
    public List<List<Language>> getToBeSavedAndToBeDeleted(List<Language> oldList, List<Language> newList) {
        List<List<Language>> result = new ArrayList<>();
        List<Language> resultDelete = new ArrayList<>();
        List<Language> resultUpdateOrSave = new ArrayList<>();
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

    private void extractToBeSaveOrDelete(List<Language> oldList, List<Language> newList, List<Language> resultUpdateOrSave, List<Language> resultDelete) {
		for (int i = 0; i < oldList.size(); i++) {
                Language myOld = oldList.get(i);
                Language t = newList.stream().filter(e -> myOld.equals(e)).findFirst().orElse(null);
                if (t != null) {
                    resultUpdateOrSave.add(t); // update
                } else {
                    resultDelete.add(myOld);
                }
            }
            for (int i = 0; i < newList.size(); i++) {
                Language myNew = newList.get(i);
                Language t = oldList.stream().filter(e -> myNew.equals(e)).findFirst().orElse(null);
                if (t == null) {
                    resultUpdateOrSave.add(myNew); // create
                }
            }
	}

    @Override
    public String uploadFile(String checksumOld, String tempUpladedFile, String destinationFilePath) throws Exception {
        return null;
    }








    public LanguageInstructorServiceImpl(LanguageDao dao) {
        this.dao = dao;
    }

    private LanguageDao dao;
}
