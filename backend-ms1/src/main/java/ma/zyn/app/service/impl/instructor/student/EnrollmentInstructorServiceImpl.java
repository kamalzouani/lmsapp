package ma.zyn.app.service.impl.instructor.student;


import ma.zyn.app.zynerator.exception.EntityNotFoundException;
import ma.zyn.app.bean.core.student.Enrollment;
import ma.zyn.app.dao.criteria.core.student.EnrollmentCriteria;
import ma.zyn.app.dao.facade.core.student.EnrollmentDao;
import ma.zyn.app.dao.specification.core.student.EnrollmentSpecification;
import ma.zyn.app.service.facade.instructor.student.EnrollmentInstructorService;
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

import ma.zyn.app.service.facade.instructor.student.EnrollmentStateInstructorService ;
import ma.zyn.app.bean.core.student.EnrollmentState ;
import ma.zyn.app.service.facade.instructor.student.StudentInstructorService ;
import ma.zyn.app.bean.core.student.Student ;
import ma.zyn.app.service.facade.instructor.course.CourseInstructorService ;
import ma.zyn.app.bean.core.course.Course ;

import java.util.List;
@Service
public class EnrollmentInstructorServiceImpl implements EnrollmentInstructorService {

    @Transactional(propagation = Propagation.REQUIRED, rollbackFor = Exception.class, readOnly = false)
    public Enrollment update(Enrollment t) {
        Enrollment loadedItem = dao.findById(t.getId()).orElse(null);
        if (loadedItem == null) {
            throw new EntityNotFoundException("errors.notFound", new String[]{Enrollment.class.getSimpleName(), t.getId().toString()});
        } else {
            dao.save(t);
            return loadedItem;
        }
    }

    public Enrollment findById(Long id) {
        return dao.findById(id).orElse(null);
    }


    public Enrollment findOrSave(Enrollment t) {
        if (t != null) {
            findOrSaveAssociatedObject(t);
            Enrollment result = findByReferenceEntity(t);
            if (result == null) {
                return dao.save(t);
            } else {
                return result;
            }
        }
        return null;
    }

    public List<Enrollment> findAll() {
        return dao.findAll();
    }

    public List<Enrollment> findByCriteria(EnrollmentCriteria criteria) {
        List<Enrollment> content = null;
        if (criteria != null) {
            EnrollmentSpecification mySpecification = constructSpecification(criteria);
            content = dao.findAll(mySpecification);
        } else {
            content = dao.findAll();
        }
        return content;

    }


    private EnrollmentSpecification constructSpecification(EnrollmentCriteria criteria) {
        EnrollmentSpecification mySpecification =  (EnrollmentSpecification) RefelexivityUtil.constructObjectUsingOneParam(EnrollmentSpecification.class, criteria);
        return mySpecification;
    }

    public List<Enrollment> findPaginatedByCriteria(EnrollmentCriteria criteria, int page, int pageSize, String order, String sortField) {
        EnrollmentSpecification mySpecification = constructSpecification(criteria);
        order = (order != null && !order.isEmpty()) ? order : "desc";
        sortField = (sortField != null && !sortField.isEmpty()) ? sortField : "id";
        Pageable pageable = PageRequest.of(page, pageSize, Sort.Direction.fromString(order), sortField);
        return dao.findAll(mySpecification, pageable).getContent();
    }

    public int getDataSize(EnrollmentCriteria criteria) {
        EnrollmentSpecification mySpecification = constructSpecification(criteria);
        mySpecification.setDistinct(true);
        return ((Long) dao.count(mySpecification)).intValue();
    }

    public List<Enrollment> findByStudentId(Long id){
        return dao.findByStudentId(id);
    }
    public int deleteByStudentId(Long id){
        return dao.deleteByStudentId(id);
    }
    public long countByStudentEmail(String email){
        return dao.countByStudentEmail(email);
    }
    public List<Enrollment> findByCourseId(Long id){
        return dao.findByCourseId(id);
    }
    public int deleteByCourseId(Long id){
        return dao.deleteByCourseId(id);
    }
    public long countByCourseCode(String code){
        return dao.countByCourseCode(code);
    }
    public List<Enrollment> findByEnrollmentStateCode(String code){
        return dao.findByEnrollmentStateCode(code);
    }
    public List<Enrollment> findByEnrollmentStateId(Long id){
        return dao.findByEnrollmentStateId(id);
    }
    public int deleteByEnrollmentStateCode(String code){
        return dao.deleteByEnrollmentStateCode(code);
    }
    public int deleteByEnrollmentStateId(Long id){
        return dao.deleteByEnrollmentStateId(id);
    }
    public long countByEnrollmentStateCode(String code){
        return dao.countByEnrollmentStateCode(code);
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
    public List<Enrollment> delete(List<Enrollment> list) {
		List<Enrollment> result = new ArrayList();
        if (list != null) {
            for (Enrollment t : list) {
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
    public Enrollment create(Enrollment t) {
        Enrollment loaded = findByReferenceEntity(t);
        Enrollment saved;
        if (loaded == null) {
            saved = dao.save(t);
        }else {
            saved = null;
        }
        return saved;
    }

    public Enrollment findWithAssociatedLists(Long id){
        Enrollment result = dao.findById(id).orElse(null);
        return result;
    }

	@Transactional(propagation = Propagation.REQUIRED, rollbackFor = Exception.class, readOnly = false)
    public List<Enrollment> update(List<Enrollment> ts, boolean createIfNotExist) {
        List<Enrollment> result = new ArrayList<>();
        if (ts != null) {
            for (Enrollment t : ts) {
                if (t.getId() == null) {
                    dao.save(t);
                } else {
                    Enrollment loadedItem = dao.findById(t.getId()).orElse(null);
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


    private boolean isEligibleForCreateOrUpdate(boolean createIfNotExist, Enrollment t, Enrollment loadedItem) {
        boolean eligibleForCreateCrud = t.getId() == null;
        boolean eligibleForCreate = (createIfNotExist && (t.getId() == null || loadedItem == null));
        boolean eligibleForUpdate = (t.getId() != null && loadedItem != null);
        return (eligibleForCreateCrud || eligibleForCreate || eligibleForUpdate);
    }









    public Enrollment findByReferenceEntity(Enrollment t) {
        return t == null || t.getId() == null ? null : findById(t.getId());
    }
    public void findOrSaveAssociatedObject(Enrollment t){
        if( t != null) {
        }
    }



    public List<Enrollment> findAllOptimized() {
        return dao.findAll();
    }

    @Override
    public List<List<Enrollment>> getToBeSavedAndToBeDeleted(List<Enrollment> oldList, List<Enrollment> newList) {
        List<List<Enrollment>> result = new ArrayList<>();
        List<Enrollment> resultDelete = new ArrayList<>();
        List<Enrollment> resultUpdateOrSave = new ArrayList<>();
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

    private void extractToBeSaveOrDelete(List<Enrollment> oldList, List<Enrollment> newList, List<Enrollment> resultUpdateOrSave, List<Enrollment> resultDelete) {
		for (int i = 0; i < oldList.size(); i++) {
                Enrollment myOld = oldList.get(i);
                Enrollment t = newList.stream().filter(e -> myOld.equals(e)).findFirst().orElse(null);
                if (t != null) {
                    resultUpdateOrSave.add(t); // update
                } else {
                    resultDelete.add(myOld);
                }
            }
            for (int i = 0; i < newList.size(); i++) {
                Enrollment myNew = newList.get(i);
                Enrollment t = oldList.stream().filter(e -> myNew.equals(e)).findFirst().orElse(null);
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
    private EnrollmentStateInstructorService enrollmentStateService ;
    @Autowired
    private StudentInstructorService studentService ;
    @Autowired
    private CourseInstructorService courseService ;

    public EnrollmentInstructorServiceImpl(EnrollmentDao dao) {
        this.dao = dao;
    }

    private EnrollmentDao dao;
}
