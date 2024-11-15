package ma.zyn.app.service.impl.student.course;


import ma.zyn.app.zynerator.exception.EntityNotFoundException;
import ma.zyn.app.bean.core.course.Resource;
import ma.zyn.app.dao.criteria.core.course.ResourceCriteria;
import ma.zyn.app.dao.facade.core.course.ResourceDao;
import ma.zyn.app.dao.specification.core.course.ResourceSpecification;
import ma.zyn.app.service.facade.student.course.ResourceStudentService;
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

import ma.zyn.app.service.facade.student.course.LessonStudentService ;
import ma.zyn.app.bean.core.course.Lesson ;

import java.util.List;
@Service
public class ResourceStudentServiceImpl implements ResourceStudentService {

    @Transactional(propagation = Propagation.REQUIRED, rollbackFor = Exception.class, readOnly = false)
    public Resource update(Resource t) {
        Resource loadedItem = dao.findById(t.getId()).orElse(null);
        if (loadedItem == null) {
            throw new EntityNotFoundException("errors.notFound", new String[]{Resource.class.getSimpleName(), t.getId().toString()});
        } else {
            dao.save(t);
            return loadedItem;
        }
    }

    public Resource findById(Long id) {
        return dao.findById(id).orElse(null);
    }


    public Resource findOrSave(Resource t) {
        if (t != null) {
            findOrSaveAssociatedObject(t);
            Resource result = findByReferenceEntity(t);
            if (result == null) {
                return dao.save(t);
            } else {
                return result;
            }
        }
        return null;
    }

    public List<Resource> findAll() {
        return dao.findAll();
    }

    public List<Resource> findByCriteria(ResourceCriteria criteria) {
        List<Resource> content = null;
        if (criteria != null) {
            ResourceSpecification mySpecification = constructSpecification(criteria);
            content = dao.findAll(mySpecification);
        } else {
            content = dao.findAll();
        }
        return content;

    }


    private ResourceSpecification constructSpecification(ResourceCriteria criteria) {
        ResourceSpecification mySpecification =  (ResourceSpecification) RefelexivityUtil.constructObjectUsingOneParam(ResourceSpecification.class, criteria);
        return mySpecification;
    }

    public List<Resource> findPaginatedByCriteria(ResourceCriteria criteria, int page, int pageSize, String order, String sortField) {
        ResourceSpecification mySpecification = constructSpecification(criteria);
        order = (order != null && !order.isEmpty()) ? order : "desc";
        sortField = (sortField != null && !sortField.isEmpty()) ? sortField : "id";
        Pageable pageable = PageRequest.of(page, pageSize, Sort.Direction.fromString(order), sortField);
        return dao.findAll(mySpecification, pageable).getContent();
    }

    public int getDataSize(ResourceCriteria criteria) {
        ResourceSpecification mySpecification = constructSpecification(criteria);
        mySpecification.setDistinct(true);
        return ((Long) dao.count(mySpecification)).intValue();
    }

    public List<Resource> findByLessonId(Long id){
        return dao.findByLessonId(id);
    }
    public int deleteByLessonId(Long id){
        return dao.deleteByLessonId(id);
    }
    public long countByLessonId(Long id){
        return dao.countByLessonId(id);
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
    public List<Resource> delete(List<Resource> list) {
		List<Resource> result = new ArrayList();
        if (list != null) {
            for (Resource t : list) {
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
    public Resource create(Resource t) {
        Resource loaded = findByReferenceEntity(t);
        Resource saved;
        if (loaded == null) {
            saved = dao.save(t);
        }else {
            saved = null;
        }
        return saved;
    }

    public Resource findWithAssociatedLists(Long id){
        Resource result = dao.findById(id).orElse(null);
        return result;
    }

	@Transactional(propagation = Propagation.REQUIRED, rollbackFor = Exception.class, readOnly = false)
    public List<Resource> update(List<Resource> ts, boolean createIfNotExist) {
        List<Resource> result = new ArrayList<>();
        if (ts != null) {
            for (Resource t : ts) {
                if (t.getId() == null) {
                    dao.save(t);
                } else {
                    Resource loadedItem = dao.findById(t.getId()).orElse(null);
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


    private boolean isEligibleForCreateOrUpdate(boolean createIfNotExist, Resource t, Resource loadedItem) {
        boolean eligibleForCreateCrud = t.getId() == null;
        boolean eligibleForCreate = (createIfNotExist && (t.getId() == null || loadedItem == null));
        boolean eligibleForUpdate = (t.getId() != null && loadedItem != null);
        return (eligibleForCreateCrud || eligibleForCreate || eligibleForUpdate);
    }









    public Resource findByReferenceEntity(Resource t) {
        return t == null || t.getId() == null ? null : findById(t.getId());
    }
    public void findOrSaveAssociatedObject(Resource t){
        if( t != null) {
        }
    }



    public List<Resource> findAllOptimized() {
        return dao.findAll();
    }

    @Override
    public List<List<Resource>> getToBeSavedAndToBeDeleted(List<Resource> oldList, List<Resource> newList) {
        List<List<Resource>> result = new ArrayList<>();
        List<Resource> resultDelete = new ArrayList<>();
        List<Resource> resultUpdateOrSave = new ArrayList<>();
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

    private void extractToBeSaveOrDelete(List<Resource> oldList, List<Resource> newList, List<Resource> resultUpdateOrSave, List<Resource> resultDelete) {
		for (int i = 0; i < oldList.size(); i++) {
                Resource myOld = oldList.get(i);
                Resource t = newList.stream().filter(e -> myOld.equals(e)).findFirst().orElse(null);
                if (t != null) {
                    resultUpdateOrSave.add(t); // update
                } else {
                    resultDelete.add(myOld);
                }
            }
            for (int i = 0; i < newList.size(); i++) {
                Resource myNew = newList.get(i);
                Resource t = oldList.stream().filter(e -> myNew.equals(e)).findFirst().orElse(null);
                if (t == null) {
                    resultUpdateOrSave.add(myNew); // create
                }
            }
	}

    @Override
    public String uploadFile(String checksumOld, String tempUpladedFile, String destinationFilePath) throws Exception {
        return null;
    }







    @Autowired
    private LessonStudentService lessonService ;

    public ResourceStudentServiceImpl(ResourceDao dao) {
        this.dao = dao;
    }

    private ResourceDao dao;
}
