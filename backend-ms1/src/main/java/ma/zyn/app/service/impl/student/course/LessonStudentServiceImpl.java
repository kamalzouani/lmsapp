package ma.zyn.app.service.impl.student.course;


import ma.zyn.app.zynerator.exception.EntityNotFoundException;
import ma.zyn.app.bean.core.course.Lesson;
import ma.zyn.app.dao.criteria.core.course.LessonCriteria;
import ma.zyn.app.dao.facade.core.course.LessonDao;
import ma.zyn.app.dao.specification.core.course.LessonSpecification;
import ma.zyn.app.service.facade.student.course.LessonStudentService;
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

import ma.zyn.app.service.facade.student.course.ModuleStudentService ;
import ma.zyn.app.bean.core.course.Module ;
import ma.zyn.app.service.facade.student.course.ResourceStudentService ;
import ma.zyn.app.bean.core.course.Resource ;

import java.util.List;
@Service
public class LessonStudentServiceImpl implements LessonStudentService {

    @Transactional(propagation = Propagation.REQUIRED, rollbackFor = Exception.class, readOnly = false)
    public Lesson update(Lesson t) {
        Lesson loadedItem = dao.findById(t.getId()).orElse(null);
        if (loadedItem == null) {
            throw new EntityNotFoundException("errors.notFound", new String[]{Lesson.class.getSimpleName(), t.getId().toString()});
        } else {
            updateWithAssociatedLists(t);
            dao.save(t);
            return loadedItem;
        }
    }

    public Lesson findById(Long id) {
        return dao.findById(id).orElse(null);
    }


    public Lesson findOrSave(Lesson t) {
        if (t != null) {
            findOrSaveAssociatedObject(t);
            Lesson result = findByReferenceEntity(t);
            if (result == null) {
                return dao.save(t);
            } else {
                return result;
            }
        }
        return null;
    }

    public List<Lesson> findAll() {
        return dao.findAll();
    }

    public List<Lesson> findByCriteria(LessonCriteria criteria) {
        List<Lesson> content = null;
        if (criteria != null) {
            LessonSpecification mySpecification = constructSpecification(criteria);
            content = dao.findAll(mySpecification);
        } else {
            content = dao.findAll();
        }
        return content;

    }


    private LessonSpecification constructSpecification(LessonCriteria criteria) {
        LessonSpecification mySpecification =  (LessonSpecification) RefelexivityUtil.constructObjectUsingOneParam(LessonSpecification.class, criteria);
        return mySpecification;
    }

    public List<Lesson> findPaginatedByCriteria(LessonCriteria criteria, int page, int pageSize, String order, String sortField) {
        LessonSpecification mySpecification = constructSpecification(criteria);
        order = (order != null && !order.isEmpty()) ? order : "desc";
        sortField = (sortField != null && !sortField.isEmpty()) ? sortField : "id";
        Pageable pageable = PageRequest.of(page, pageSize, Sort.Direction.fromString(order), sortField);
        return dao.findAll(mySpecification, pageable).getContent();
    }

    public int getDataSize(LessonCriteria criteria) {
        LessonSpecification mySpecification = constructSpecification(criteria);
        mySpecification.setDistinct(true);
        return ((Long) dao.count(mySpecification)).intValue();
    }

    public List<Lesson> findByModuleId(Long id){
        return dao.findByModuleId(id);
    }
    public int deleteByModuleId(Long id){
        return dao.deleteByModuleId(id);
    }
    public long countByModuleId(Long id){
        return dao.countByModuleId(id);
    }
    @Transactional(propagation = Propagation.REQUIRED, rollbackFor = Exception.class, readOnly = false)
	public boolean deleteById(Long id) {
        boolean condition = (id != null);
        if (condition) {
            deleteAssociatedLists(id);
            dao.deleteById(id);
        }
        return condition;
    }

    public void deleteAssociatedLists(Long id) {
        resourceService.deleteByLessonId(id);
    }




    @Transactional(propagation = Propagation.REQUIRED, rollbackFor = Exception.class, readOnly = false)
    public List<Lesson> delete(List<Lesson> list) {
		List<Lesson> result = new ArrayList();
        if (list != null) {
            for (Lesson t : list) {
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
    public Lesson create(Lesson t) {
        Lesson loaded = findByReferenceEntity(t);
        Lesson saved;
        if (loaded == null) {
            saved = dao.save(t);
            if (t.getResources() != null) {
                t.getResources().forEach(element-> {
                    element.setLesson(saved);
                    resourceService.create(element);
                });
            }
        }else {
            saved = null;
        }
        return saved;
    }

    public Lesson findWithAssociatedLists(Long id){
        Lesson result = dao.findById(id).orElse(null);
        if(result!=null && result.getId() != null) {
            result.setResources(resourceService.findByLessonId(id));
        }
        return result;
    }

	@Transactional(propagation = Propagation.REQUIRED, rollbackFor = Exception.class, readOnly = false)
    public List<Lesson> update(List<Lesson> ts, boolean createIfNotExist) {
        List<Lesson> result = new ArrayList<>();
        if (ts != null) {
            for (Lesson t : ts) {
                if (t.getId() == null) {
                    dao.save(t);
                } else {
                    Lesson loadedItem = dao.findById(t.getId()).orElse(null);
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


    private boolean isEligibleForCreateOrUpdate(boolean createIfNotExist, Lesson t, Lesson loadedItem) {
        boolean eligibleForCreateCrud = t.getId() == null;
        boolean eligibleForCreate = (createIfNotExist && (t.getId() == null || loadedItem == null));
        boolean eligibleForUpdate = (t.getId() != null && loadedItem != null);
        return (eligibleForCreateCrud || eligibleForCreate || eligibleForUpdate);
    }

    public void updateWithAssociatedLists(Lesson lesson){
    if(lesson !=null && lesson.getId() != null){
        List<List<Resource>> resultResources= resourceService.getToBeSavedAndToBeDeleted(resourceService.findByLessonId(lesson.getId()),lesson.getResources());
            resourceService.delete(resultResources.get(1));
        emptyIfNull(resultResources.get(0)).forEach(e -> e.setLesson(lesson));
        resourceService.update(resultResources.get(0),true);
        }
    }








    public Lesson findByReferenceEntity(Lesson t) {
        return t == null || t.getId() == null ? null : findById(t.getId());
    }
    public void findOrSaveAssociatedObject(Lesson t){
        if( t != null) {
        }
    }



    public List<Lesson> findAllOptimized() {
        return dao.findAllOptimized();
    }

    @Override
    public List<List<Lesson>> getToBeSavedAndToBeDeleted(List<Lesson> oldList, List<Lesson> newList) {
        List<List<Lesson>> result = new ArrayList<>();
        List<Lesson> resultDelete = new ArrayList<>();
        List<Lesson> resultUpdateOrSave = new ArrayList<>();
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

    private void extractToBeSaveOrDelete(List<Lesson> oldList, List<Lesson> newList, List<Lesson> resultUpdateOrSave, List<Lesson> resultDelete) {
		for (int i = 0; i < oldList.size(); i++) {
                Lesson myOld = oldList.get(i);
                Lesson t = newList.stream().filter(e -> myOld.equals(e)).findFirst().orElse(null);
                if (t != null) {
                    resultUpdateOrSave.add(t); // update
                } else {
                    resultDelete.add(myOld);
                }
            }
            for (int i = 0; i < newList.size(); i++) {
                Lesson myNew = newList.get(i);
                Lesson t = oldList.stream().filter(e -> myNew.equals(e)).findFirst().orElse(null);
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
    private ModuleStudentService moduleService ;
    @Autowired
    private ResourceStudentService resourceService ;

    public LessonStudentServiceImpl(LessonDao dao) {
        this.dao = dao;
    }

    private LessonDao dao;
}
