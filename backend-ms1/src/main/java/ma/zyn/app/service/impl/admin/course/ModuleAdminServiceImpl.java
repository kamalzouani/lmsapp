package ma.zyn.app.service.impl.admin.course;


import ma.zyn.app.zynerator.exception.EntityNotFoundException;
import ma.zyn.app.bean.core.course.Module;
import ma.zyn.app.dao.criteria.core.course.ModuleCriteria;
import ma.zyn.app.dao.facade.core.course.ModuleDao;
import ma.zyn.app.dao.specification.core.course.ModuleSpecification;
import ma.zyn.app.service.facade.admin.course.ModuleAdminService;
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

import ma.zyn.app.service.facade.admin.course.LessonAdminService ;
import ma.zyn.app.bean.core.course.Lesson ;
import ma.zyn.app.service.facade.admin.course.CurriculumAdminService ;
import ma.zyn.app.bean.core.course.Curriculum ;

import java.util.List;
@Service
public class ModuleAdminServiceImpl implements ModuleAdminService {

    @Transactional(propagation = Propagation.REQUIRED, rollbackFor = Exception.class, readOnly = false)
    public Module update(Module t) {
        Module loadedItem = dao.findById(t.getId()).orElse(null);
        if (loadedItem == null) {
            throw new EntityNotFoundException("errors.notFound", new String[]{Module.class.getSimpleName(), t.getId().toString()});
        } else {
            updateWithAssociatedLists(t);
            dao.save(t);
            return loadedItem;
        }
    }

    public Module findById(Long id) {
        return dao.findById(id).orElse(null);
    }


    public Module findOrSave(Module t) {
        if (t != null) {
            findOrSaveAssociatedObject(t);
            Module result = findByReferenceEntity(t);
            if (result == null) {
                return dao.save(t);
            } else {
                return result;
            }
        }
        return null;
    }

    public List<Module> findAll() {
        return dao.findAll();
    }

    public List<Module> findByCriteria(ModuleCriteria criteria) {
        List<Module> content = null;
        if (criteria != null) {
            ModuleSpecification mySpecification = constructSpecification(criteria);
            content = dao.findAll(mySpecification);
        } else {
            content = dao.findAll();
        }
        return content;

    }


    private ModuleSpecification constructSpecification(ModuleCriteria criteria) {
        ModuleSpecification mySpecification =  (ModuleSpecification) RefelexivityUtil.constructObjectUsingOneParam(ModuleSpecification.class, criteria);
        return mySpecification;
    }

    public List<Module> findPaginatedByCriteria(ModuleCriteria criteria, int page, int pageSize, String order, String sortField) {
        ModuleSpecification mySpecification = constructSpecification(criteria);
        order = (order != null && !order.isEmpty()) ? order : "desc";
        sortField = (sortField != null && !sortField.isEmpty()) ? sortField : "id";
        Pageable pageable = PageRequest.of(page, pageSize, Sort.Direction.fromString(order), sortField);
        return dao.findAll(mySpecification, pageable).getContent();
    }

    public int getDataSize(ModuleCriteria criteria) {
        ModuleSpecification mySpecification = constructSpecification(criteria);
        mySpecification.setDistinct(true);
        return ((Long) dao.count(mySpecification)).intValue();
    }

    public List<Module> findByCurriculumId(Long id){
        return dao.findByCurriculumId(id);
    }
    public int deleteByCurriculumId(Long id){
        return dao.deleteByCurriculumId(id);
    }
    public long countByCurriculumId(Long id){
        return dao.countByCurriculumId(id);
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
        lessonService.deleteByModuleId(id);
    }




    @Transactional(propagation = Propagation.REQUIRED, rollbackFor = Exception.class, readOnly = false)
    public List<Module> delete(List<Module> list) {
		List<Module> result = new ArrayList();
        if (list != null) {
            for (Module t : list) {
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
    public Module create(Module t) {
        Module loaded = findByReferenceEntity(t);
        Module saved;
        if (loaded == null) {
            saved = dao.save(t);
            if (t.getLessons() != null) {
                t.getLessons().forEach(element-> {
                    element.setModule(saved);
                    lessonService.create(element);
                });
            }
        }else {
            saved = null;
        }
        return saved;
    }

    public Module findWithAssociatedLists(Long id){
        Module result = dao.findById(id).orElse(null);
        if(result!=null && result.getId() != null) {
            result.setLessons(lessonService.findByModuleId(id));
        }
        return result;
    }

	@Transactional(propagation = Propagation.REQUIRED, rollbackFor = Exception.class, readOnly = false)
    public List<Module> update(List<Module> ts, boolean createIfNotExist) {
        List<Module> result = new ArrayList<>();
        if (ts != null) {
            for (Module t : ts) {
                if (t.getId() == null) {
                    dao.save(t);
                } else {
                    Module loadedItem = dao.findById(t.getId()).orElse(null);
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


    private boolean isEligibleForCreateOrUpdate(boolean createIfNotExist, Module t, Module loadedItem) {
        boolean eligibleForCreateCrud = t.getId() == null;
        boolean eligibleForCreate = (createIfNotExist && (t.getId() == null || loadedItem == null));
        boolean eligibleForUpdate = (t.getId() != null && loadedItem != null);
        return (eligibleForCreateCrud || eligibleForCreate || eligibleForUpdate);
    }

    public void updateWithAssociatedLists(Module module){
    if(module !=null && module.getId() != null){
        List<List<Lesson>> resultLessons= lessonService.getToBeSavedAndToBeDeleted(lessonService.findByModuleId(module.getId()),module.getLessons());
            lessonService.delete(resultLessons.get(1));
        emptyIfNull(resultLessons.get(0)).forEach(e -> e.setModule(module));
        lessonService.update(resultLessons.get(0),true);
        }
    }








    public Module findByReferenceEntity(Module t) {
        return t == null || t.getId() == null ? null : findById(t.getId());
    }
    public void findOrSaveAssociatedObject(Module t){
        if( t != null) {
            t.setCurriculum(curriculumService.findOrSave(t.getCurriculum()));
        }
    }



    public List<Module> findAllOptimized() {
        return dao.findAllOptimized();
    }

    @Override
    public List<List<Module>> getToBeSavedAndToBeDeleted(List<Module> oldList, List<Module> newList) {
        List<List<Module>> result = new ArrayList<>();
        List<Module> resultDelete = new ArrayList<>();
        List<Module> resultUpdateOrSave = new ArrayList<>();
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

    private void extractToBeSaveOrDelete(List<Module> oldList, List<Module> newList, List<Module> resultUpdateOrSave, List<Module> resultDelete) {
		for (int i = 0; i < oldList.size(); i++) {
                Module myOld = oldList.get(i);
                Module t = newList.stream().filter(e -> myOld.equals(e)).findFirst().orElse(null);
                if (t != null) {
                    resultUpdateOrSave.add(t); // update
                } else {
                    resultDelete.add(myOld);
                }
            }
            for (int i = 0; i < newList.size(); i++) {
                Module myNew = newList.get(i);
                Module t = oldList.stream().filter(e -> myNew.equals(e)).findFirst().orElse(null);
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
    private LessonAdminService lessonService ;
    @Autowired
    private CurriculumAdminService curriculumService ;

    public ModuleAdminServiceImpl(ModuleDao dao) {
        this.dao = dao;
    }

    private ModuleDao dao;
}
