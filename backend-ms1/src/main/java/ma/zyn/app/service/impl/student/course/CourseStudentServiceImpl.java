package ma.zyn.app.service.impl.student.course;


import ma.zyn.app.zynerator.exception.EntityNotFoundException;
import ma.zyn.app.bean.core.course.Course;
import ma.zyn.app.dao.criteria.core.course.CourseCriteria;
import ma.zyn.app.dao.facade.core.course.CourseDao;
import ma.zyn.app.dao.specification.core.course.CourseSpecification;
import ma.zyn.app.service.facade.student.course.CourseStudentService;
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

import ma.zyn.app.service.facade.student.course.CategoryStudentService ;
import ma.zyn.app.bean.core.course.Category ;
import ma.zyn.app.service.facade.student.instructor.InstructorStudentService ;
import ma.zyn.app.bean.core.instructor.Instructor ;
import ma.zyn.app.service.facade.student.course.CurriculumStudentService ;
import ma.zyn.app.bean.core.course.Curriculum ;
import ma.zyn.app.service.facade.student.course.LanguageStudentService ;
import ma.zyn.app.bean.core.course.Language ;
import ma.zyn.app.service.facade.student.course.LevelStudentService ;
import ma.zyn.app.bean.core.course.Level ;

import java.util.List;
@Service
public class CourseStudentServiceImpl implements CourseStudentService {

    @Transactional(propagation = Propagation.REQUIRED, rollbackFor = Exception.class, readOnly = false)
    public Course update(Course t) {
        Course loadedItem = dao.findById(t.getId()).orElse(null);
        if (loadedItem == null) {
            throw new EntityNotFoundException("errors.notFound", new String[]{Course.class.getSimpleName(), t.getId().toString()});
        } else {
            dao.save(t);
            return loadedItem;
        }
    }

    public Course findById(Long id) {
        return dao.findById(id).orElse(null);
    }


    public Course findOrSave(Course t) {
        if (t != null) {
            findOrSaveAssociatedObject(t);
            Course result = findByReferenceEntity(t);
            if (result == null) {
                return dao.save(t);
            } else {
                return result;
            }
        }
        return null;
    }

    public List<Course> findAll() {
        return dao.findAll();
    }

    public List<Course> findByCriteria(CourseCriteria criteria) {
        List<Course> content = null;
        if (criteria != null) {
            CourseSpecification mySpecification = constructSpecification(criteria);
            content = dao.findAll(mySpecification);
        } else {
            content = dao.findAll();
        }
        return content;

    }


    private CourseSpecification constructSpecification(CourseCriteria criteria) {
        CourseSpecification mySpecification =  (CourseSpecification) RefelexivityUtil.constructObjectUsingOneParam(CourseSpecification.class, criteria);
        return mySpecification;
    }

    public List<Course> findPaginatedByCriteria(CourseCriteria criteria, int page, int pageSize, String order, String sortField) {
        CourseSpecification mySpecification = constructSpecification(criteria);
        order = (order != null && !order.isEmpty()) ? order : "desc";
        sortField = (sortField != null && !sortField.isEmpty()) ? sortField : "id";
        Pageable pageable = PageRequest.of(page, pageSize, Sort.Direction.fromString(order), sortField);
        return dao.findAll(mySpecification, pageable).getContent();
    }

    public int getDataSize(CourseCriteria criteria) {
        CourseSpecification mySpecification = constructSpecification(criteria);
        mySpecification.setDistinct(true);
        return ((Long) dao.count(mySpecification)).intValue();
    }

    public List<Course> findByInstructorId(Long id){
        return dao.findByInstructorId(id);
    }
    public int deleteByInstructorId(Long id){
        return dao.deleteByInstructorId(id);
    }
    public long countByInstructorEmail(String email){
        return dao.countByInstructorEmail(email);
    }
    public List<Course> findByCategoryCode(String code){
        return dao.findByCategoryCode(code);
    }
    public List<Course> findByCategoryId(Long id){
        return dao.findByCategoryId(id);
    }
    public int deleteByCategoryCode(String code){
        return dao.deleteByCategoryCode(code);
    }
    public int deleteByCategoryId(Long id){
        return dao.deleteByCategoryId(id);
    }
    public long countByCategoryCode(String code){
        return dao.countByCategoryCode(code);
    }
    public List<Course> findByLevelCode(String code){
        return dao.findByLevelCode(code);
    }
    public List<Course> findByLevelId(Long id){
        return dao.findByLevelId(id);
    }
    public int deleteByLevelCode(String code){
        return dao.deleteByLevelCode(code);
    }
    public int deleteByLevelId(Long id){
        return dao.deleteByLevelId(id);
    }
    public long countByLevelCode(String code){
        return dao.countByLevelCode(code);
    }
    public List<Course> findByLanguageCode(String code){
        return dao.findByLanguageCode(code);
    }
    public List<Course> findByLanguageId(Long id){
        return dao.findByLanguageId(id);
    }
    public int deleteByLanguageCode(String code){
        return dao.deleteByLanguageCode(code);
    }
    public int deleteByLanguageId(Long id){
        return dao.deleteByLanguageId(id);
    }
    public long countByLanguageCode(String code){
        return dao.countByLanguageCode(code);
    }
    public List<Course> findByCurriculumId(Long id){
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
            dao.deleteById(id);
        }
        return condition;
    }




    @Transactional(propagation = Propagation.REQUIRED, rollbackFor = Exception.class, readOnly = false)
    public List<Course> delete(List<Course> list) {
		List<Course> result = new ArrayList();
        if (list != null) {
            for (Course t : list) {
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
    public Course create(Course t) {
        Course loaded = findByReferenceEntity(t);
        Course saved;
        if (loaded == null) {
            saved = dao.save(t);
        }else {
            saved = null;
        }
        return saved;
    }

    public Course findWithAssociatedLists(Long id){
        Course result = dao.findById(id).orElse(null);
        return result;
    }

	@Transactional(propagation = Propagation.REQUIRED, rollbackFor = Exception.class, readOnly = false)
    public List<Course> update(List<Course> ts, boolean createIfNotExist) {
        List<Course> result = new ArrayList<>();
        if (ts != null) {
            for (Course t : ts) {
                if (t.getId() == null) {
                    dao.save(t);
                } else {
                    Course loadedItem = dao.findById(t.getId()).orElse(null);
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


    private boolean isEligibleForCreateOrUpdate(boolean createIfNotExist, Course t, Course loadedItem) {
        boolean eligibleForCreateCrud = t.getId() == null;
        boolean eligibleForCreate = (createIfNotExist && (t.getId() == null || loadedItem == null));
        boolean eligibleForUpdate = (t.getId() != null && loadedItem != null);
        return (eligibleForCreateCrud || eligibleForCreate || eligibleForUpdate);
    }









    public Course findByReferenceEntity(Course t){
        return t==null? null : dao.findByCode(t.getCode());
    }
    public void findOrSaveAssociatedObject(Course t){
        if( t != null) {
        }
    }



    public List<Course> findAllOptimized() {
        return dao.findAllOptimized();
    }

    @Override
    public List<List<Course>> getToBeSavedAndToBeDeleted(List<Course> oldList, List<Course> newList) {
        List<List<Course>> result = new ArrayList<>();
        List<Course> resultDelete = new ArrayList<>();
        List<Course> resultUpdateOrSave = new ArrayList<>();
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

    private void extractToBeSaveOrDelete(List<Course> oldList, List<Course> newList, List<Course> resultUpdateOrSave, List<Course> resultDelete) {
		for (int i = 0; i < oldList.size(); i++) {
                Course myOld = oldList.get(i);
                Course t = newList.stream().filter(e -> myOld.equals(e)).findFirst().orElse(null);
                if (t != null) {
                    resultUpdateOrSave.add(t); // update
                } else {
                    resultDelete.add(myOld);
                }
            }
            for (int i = 0; i < newList.size(); i++) {
                Course myNew = newList.get(i);
                Course t = oldList.stream().filter(e -> myNew.equals(e)).findFirst().orElse(null);
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
    private CategoryStudentService categoryService ;
    @Autowired
    private InstructorStudentService instructorService ;
    @Autowired
    private CurriculumStudentService curriculumService ;
    @Autowired
    private LanguageStudentService languageService ;
    @Autowired
    private LevelStudentService levelService ;

    public CourseStudentServiceImpl(CourseDao dao) {
        this.dao = dao;
    }

    private CourseDao dao;
}
