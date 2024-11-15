package ma.zyn.app.service.impl.instructor.student;


import ma.zyn.app.zynerator.exception.EntityNotFoundException;
import ma.zyn.app.bean.core.student.Review;
import ma.zyn.app.dao.criteria.core.student.ReviewCriteria;
import ma.zyn.app.dao.facade.core.student.ReviewDao;
import ma.zyn.app.dao.specification.core.student.ReviewSpecification;
import ma.zyn.app.service.facade.instructor.student.ReviewInstructorService;
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

import ma.zyn.app.service.facade.instructor.student.StudentInstructorService ;
import ma.zyn.app.bean.core.student.Student ;
import ma.zyn.app.service.facade.instructor.course.CourseInstructorService ;
import ma.zyn.app.bean.core.course.Course ;

import java.util.List;
@Service
public class ReviewInstructorServiceImpl implements ReviewInstructorService {

    @Transactional(propagation = Propagation.REQUIRED, rollbackFor = Exception.class, readOnly = false)
    public Review update(Review t) {
        Review loadedItem = dao.findById(t.getId()).orElse(null);
        if (loadedItem == null) {
            throw new EntityNotFoundException("errors.notFound", new String[]{Review.class.getSimpleName(), t.getId().toString()});
        } else {
            dao.save(t);
            return loadedItem;
        }
    }

    public Review findById(Long id) {
        return dao.findById(id).orElse(null);
    }


    public Review findOrSave(Review t) {
        if (t != null) {
            findOrSaveAssociatedObject(t);
            Review result = findByReferenceEntity(t);
            if (result == null) {
                return dao.save(t);
            } else {
                return result;
            }
        }
        return null;
    }

    public List<Review> findAll() {
        return dao.findAll();
    }

    public List<Review> findByCriteria(ReviewCriteria criteria) {
        List<Review> content = null;
        if (criteria != null) {
            ReviewSpecification mySpecification = constructSpecification(criteria);
            content = dao.findAll(mySpecification);
        } else {
            content = dao.findAll();
        }
        return content;

    }


    private ReviewSpecification constructSpecification(ReviewCriteria criteria) {
        ReviewSpecification mySpecification =  (ReviewSpecification) RefelexivityUtil.constructObjectUsingOneParam(ReviewSpecification.class, criteria);
        return mySpecification;
    }

    public List<Review> findPaginatedByCriteria(ReviewCriteria criteria, int page, int pageSize, String order, String sortField) {
        ReviewSpecification mySpecification = constructSpecification(criteria);
        order = (order != null && !order.isEmpty()) ? order : "desc";
        sortField = (sortField != null && !sortField.isEmpty()) ? sortField : "id";
        Pageable pageable = PageRequest.of(page, pageSize, Sort.Direction.fromString(order), sortField);
        return dao.findAll(mySpecification, pageable).getContent();
    }

    public int getDataSize(ReviewCriteria criteria) {
        ReviewSpecification mySpecification = constructSpecification(criteria);
        mySpecification.setDistinct(true);
        return ((Long) dao.count(mySpecification)).intValue();
    }

    public List<Review> findByStudentId(Long id){
        return dao.findByStudentId(id);
    }
    public int deleteByStudentId(Long id){
        return dao.deleteByStudentId(id);
    }
    public long countByStudentEmail(String email){
        return dao.countByStudentEmail(email);
    }
    public List<Review> findByCourseId(Long id){
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
            dao.deleteById(id);
        }
        return condition;
    }




    @Transactional(propagation = Propagation.REQUIRED, rollbackFor = Exception.class, readOnly = false)
    public List<Review> delete(List<Review> list) {
		List<Review> result = new ArrayList();
        if (list != null) {
            for (Review t : list) {
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
    public Review create(Review t) {
        Review loaded = findByReferenceEntity(t);
        Review saved;
        if (loaded == null) {
            saved = dao.save(t);
        }else {
            saved = null;
        }
        return saved;
    }

    public Review findWithAssociatedLists(Long id){
        Review result = dao.findById(id).orElse(null);
        return result;
    }

	@Transactional(propagation = Propagation.REQUIRED, rollbackFor = Exception.class, readOnly = false)
    public List<Review> update(List<Review> ts, boolean createIfNotExist) {
        List<Review> result = new ArrayList<>();
        if (ts != null) {
            for (Review t : ts) {
                if (t.getId() == null) {
                    dao.save(t);
                } else {
                    Review loadedItem = dao.findById(t.getId()).orElse(null);
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


    private boolean isEligibleForCreateOrUpdate(boolean createIfNotExist, Review t, Review loadedItem) {
        boolean eligibleForCreateCrud = t.getId() == null;
        boolean eligibleForCreate = (createIfNotExist && (t.getId() == null || loadedItem == null));
        boolean eligibleForUpdate = (t.getId() != null && loadedItem != null);
        return (eligibleForCreateCrud || eligibleForCreate || eligibleForUpdate);
    }









    public Review findByReferenceEntity(Review t) {
        return t == null || t.getId() == null ? null : findById(t.getId());
    }
    public void findOrSaveAssociatedObject(Review t){
        if( t != null) {
        }
    }



    public List<Review> findAllOptimized() {
        return dao.findAll();
    }

    @Override
    public List<List<Review>> getToBeSavedAndToBeDeleted(List<Review> oldList, List<Review> newList) {
        List<List<Review>> result = new ArrayList<>();
        List<Review> resultDelete = new ArrayList<>();
        List<Review> resultUpdateOrSave = new ArrayList<>();
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

    private void extractToBeSaveOrDelete(List<Review> oldList, List<Review> newList, List<Review> resultUpdateOrSave, List<Review> resultDelete) {
		for (int i = 0; i < oldList.size(); i++) {
                Review myOld = oldList.get(i);
                Review t = newList.stream().filter(e -> myOld.equals(e)).findFirst().orElse(null);
                if (t != null) {
                    resultUpdateOrSave.add(t); // update
                } else {
                    resultDelete.add(myOld);
                }
            }
            for (int i = 0; i < newList.size(); i++) {
                Review myNew = newList.get(i);
                Review t = oldList.stream().filter(e -> myNew.equals(e)).findFirst().orElse(null);
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
    private StudentInstructorService studentService ;
    @Autowired
    private CourseInstructorService courseService ;

    public ReviewInstructorServiceImpl(ReviewDao dao) {
        this.dao = dao;
    }

    private ReviewDao dao;
}
