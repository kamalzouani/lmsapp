package ma.zyn.app.service.impl.admin.course;


import ma.zyn.app.zynerator.exception.EntityNotFoundException;
import ma.zyn.app.bean.core.course.Curriculum;
import ma.zyn.app.dao.criteria.core.course.CurriculumCriteria;
import ma.zyn.app.dao.facade.core.course.CurriculumDao;
import ma.zyn.app.dao.specification.core.course.CurriculumSpecification;
import ma.zyn.app.service.facade.admin.course.CurriculumAdminService;
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

import ma.zyn.app.service.facade.admin.course.ModuleAdminService ;
import ma.zyn.app.bean.core.course.Module ;
import ma.zyn.app.service.facade.admin.course.CourseAdminService ;
import ma.zyn.app.bean.core.course.Course ;

import java.util.List;
@Service
public class CurriculumAdminServiceImpl implements CurriculumAdminService {

    @Transactional(propagation = Propagation.REQUIRED, rollbackFor = Exception.class, readOnly = false)
    public Curriculum update(Curriculum t) {
        Curriculum loadedItem = dao.findById(t.getId()).orElse(null);
        if (loadedItem == null) {
            throw new EntityNotFoundException("errors.notFound", new String[]{Curriculum.class.getSimpleName(), t.getId().toString()});
        } else {
            updateWithAssociatedLists(t);
            dao.save(t);
            return loadedItem;
        }
    }

    public Curriculum findById(Long id) {
        return dao.findById(id).orElse(null);
    }


    public Curriculum findOrSave(Curriculum t) {
        if (t != null) {
            findOrSaveAssociatedObject(t);
            Curriculum result = findByReferenceEntity(t);
            if (result == null) {
                return dao.save(t);
            } else {
                return result;
            }
        }
        return null;
    }

    public List<Curriculum> findAll() {
        return dao.findAll();
    }

    public List<Curriculum> findByCriteria(CurriculumCriteria criteria) {
        List<Curriculum> content = null;
        if (criteria != null) {
            CurriculumSpecification mySpecification = constructSpecification(criteria);
            content = dao.findAll(mySpecification);
        } else {
            content = dao.findAll();
        }
        return content;

    }


    private CurriculumSpecification constructSpecification(CurriculumCriteria criteria) {
        CurriculumSpecification mySpecification =  (CurriculumSpecification) RefelexivityUtil.constructObjectUsingOneParam(CurriculumSpecification.class, criteria);
        return mySpecification;
    }

    public List<Curriculum> findPaginatedByCriteria(CurriculumCriteria criteria, int page, int pageSize, String order, String sortField) {
        CurriculumSpecification mySpecification = constructSpecification(criteria);
        order = (order != null && !order.isEmpty()) ? order : "desc";
        sortField = (sortField != null && !sortField.isEmpty()) ? sortField : "id";
        Pageable pageable = PageRequest.of(page, pageSize, Sort.Direction.fromString(order), sortField);
        return dao.findAll(mySpecification, pageable).getContent();
    }

    public int getDataSize(CurriculumCriteria criteria) {
        CurriculumSpecification mySpecification = constructSpecification(criteria);
        mySpecification.setDistinct(true);
        return ((Long) dao.count(mySpecification)).intValue();
    }

    public List<Curriculum> findByCourseId(Long id){
        return dao.findByCourseId(id);
    }
    public int deleteByCourseId(Long id){
        return dao.deleteByCourseId(id);
    }
    public long countByCourseCode(String code){
        return dao.countByCourseCode(code);
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
        moduleService.deleteByCurriculumId(id);
    }




    @Transactional(propagation = Propagation.REQUIRED, rollbackFor = Exception.class, readOnly = false)
    public List<Curriculum> delete(List<Curriculum> list) {
		List<Curriculum> result = new ArrayList();
        if (list != null) {
            for (Curriculum t : list) {
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
    public Curriculum create(Curriculum t) {
        Curriculum loaded = findByReferenceEntity(t);
        Curriculum saved;
        if (loaded == null) {
            saved = dao.save(t);
            if (t.getModules() != null) {
                t.getModules().forEach(element-> {
                    element.setCurriculum(saved);
                    moduleService.create(element);
                });
            }
        }else {
            saved = null;
        }
        return saved;
    }

    public Curriculum findWithAssociatedLists(Long id){
        Curriculum result = dao.findById(id).orElse(null);
        if(result!=null && result.getId() != null) {
            result.setModules(moduleService.findByCurriculumId(id));
        }
        return result;
    }

	@Transactional(propagation = Propagation.REQUIRED, rollbackFor = Exception.class, readOnly = false)
    public List<Curriculum> update(List<Curriculum> ts, boolean createIfNotExist) {
        List<Curriculum> result = new ArrayList<>();
        if (ts != null) {
            for (Curriculum t : ts) {
                if (t.getId() == null) {
                    dao.save(t);
                } else {
                    Curriculum loadedItem = dao.findById(t.getId()).orElse(null);
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


    private boolean isEligibleForCreateOrUpdate(boolean createIfNotExist, Curriculum t, Curriculum loadedItem) {
        boolean eligibleForCreateCrud = t.getId() == null;
        boolean eligibleForCreate = (createIfNotExist && (t.getId() == null || loadedItem == null));
        boolean eligibleForUpdate = (t.getId() != null && loadedItem != null);
        return (eligibleForCreateCrud || eligibleForCreate || eligibleForUpdate);
    }

    public void updateWithAssociatedLists(Curriculum curriculum){
    if(curriculum !=null && curriculum.getId() != null){
        List<List<Module>> resultModules= moduleService.getToBeSavedAndToBeDeleted(moduleService.findByCurriculumId(curriculum.getId()),curriculum.getModules());
            moduleService.delete(resultModules.get(1));
        emptyIfNull(resultModules.get(0)).forEach(e -> e.setCurriculum(curriculum));
        moduleService.update(resultModules.get(0),true);
        }
    }








    public Curriculum findByReferenceEntity(Curriculum t) {
        return t == null || t.getId() == null ? null : findById(t.getId());
    }
    public void findOrSaveAssociatedObject(Curriculum t){
        if( t != null) {
            t.setCourse(courseService.findOrSave(t.getCourse()));
        }
    }



    public List<Curriculum> findAllOptimized() {
        return dao.findAll();
    }

    @Override
    public List<List<Curriculum>> getToBeSavedAndToBeDeleted(List<Curriculum> oldList, List<Curriculum> newList) {
        List<List<Curriculum>> result = new ArrayList<>();
        List<Curriculum> resultDelete = new ArrayList<>();
        List<Curriculum> resultUpdateOrSave = new ArrayList<>();
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

    private void extractToBeSaveOrDelete(List<Curriculum> oldList, List<Curriculum> newList, List<Curriculum> resultUpdateOrSave, List<Curriculum> resultDelete) {
		for (int i = 0; i < oldList.size(); i++) {
                Curriculum myOld = oldList.get(i);
                Curriculum t = newList.stream().filter(e -> myOld.equals(e)).findFirst().orElse(null);
                if (t != null) {
                    resultUpdateOrSave.add(t); // update
                } else {
                    resultDelete.add(myOld);
                }
            }
            for (int i = 0; i < newList.size(); i++) {
                Curriculum myNew = newList.get(i);
                Curriculum t = oldList.stream().filter(e -> myNew.equals(e)).findFirst().orElse(null);
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
    private ModuleAdminService moduleService ;
    @Autowired
    private CourseAdminService courseService ;

    public CurriculumAdminServiceImpl(CurriculumDao dao) {
        this.dao = dao;
    }

    private CurriculumDao dao;
}
