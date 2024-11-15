package ma.zyn.app.service.impl.admin.student;


import ma.zyn.app.zynerator.exception.EntityNotFoundException;
import ma.zyn.app.bean.core.student.Payment;
import ma.zyn.app.dao.criteria.core.student.PaymentCriteria;
import ma.zyn.app.dao.facade.core.student.PaymentDao;
import ma.zyn.app.dao.specification.core.student.PaymentSpecification;
import ma.zyn.app.service.facade.admin.student.PaymentAdminService;
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

import ma.zyn.app.service.facade.admin.student.PaymentStateAdminService ;
import ma.zyn.app.bean.core.student.PaymentState ;
import ma.zyn.app.service.facade.admin.student.StudentAdminService ;
import ma.zyn.app.bean.core.student.Student ;
import ma.zyn.app.service.facade.admin.course.CourseAdminService ;
import ma.zyn.app.bean.core.course.Course ;

import java.util.List;
@Service
public class PaymentAdminServiceImpl implements PaymentAdminService {

    @Transactional(propagation = Propagation.REQUIRED, rollbackFor = Exception.class, readOnly = false)
    public Payment update(Payment t) {
        Payment loadedItem = dao.findById(t.getId()).orElse(null);
        if (loadedItem == null) {
            throw new EntityNotFoundException("errors.notFound", new String[]{Payment.class.getSimpleName(), t.getId().toString()});
        } else {
            dao.save(t);
            return loadedItem;
        }
    }

    public Payment findById(Long id) {
        return dao.findById(id).orElse(null);
    }


    public Payment findOrSave(Payment t) {
        if (t != null) {
            findOrSaveAssociatedObject(t);
            Payment result = findByReferenceEntity(t);
            if (result == null) {
                return dao.save(t);
            } else {
                return result;
            }
        }
        return null;
    }

    public List<Payment> findAll() {
        return dao.findAll();
    }

    public List<Payment> findByCriteria(PaymentCriteria criteria) {
        List<Payment> content = null;
        if (criteria != null) {
            PaymentSpecification mySpecification = constructSpecification(criteria);
            content = dao.findAll(mySpecification);
        } else {
            content = dao.findAll();
        }
        return content;

    }


    private PaymentSpecification constructSpecification(PaymentCriteria criteria) {
        PaymentSpecification mySpecification =  (PaymentSpecification) RefelexivityUtil.constructObjectUsingOneParam(PaymentSpecification.class, criteria);
        return mySpecification;
    }

    public List<Payment> findPaginatedByCriteria(PaymentCriteria criteria, int page, int pageSize, String order, String sortField) {
        PaymentSpecification mySpecification = constructSpecification(criteria);
        order = (order != null && !order.isEmpty()) ? order : "desc";
        sortField = (sortField != null && !sortField.isEmpty()) ? sortField : "id";
        Pageable pageable = PageRequest.of(page, pageSize, Sort.Direction.fromString(order), sortField);
        return dao.findAll(mySpecification, pageable).getContent();
    }

    public int getDataSize(PaymentCriteria criteria) {
        PaymentSpecification mySpecification = constructSpecification(criteria);
        mySpecification.setDistinct(true);
        return ((Long) dao.count(mySpecification)).intValue();
    }

    public List<Payment> findByStudentId(Long id){
        return dao.findByStudentId(id);
    }
    public int deleteByStudentId(Long id){
        return dao.deleteByStudentId(id);
    }
    public long countByStudentEmail(String email){
        return dao.countByStudentEmail(email);
    }
    public List<Payment> findByCourseId(Long id){
        return dao.findByCourseId(id);
    }
    public int deleteByCourseId(Long id){
        return dao.deleteByCourseId(id);
    }
    public long countByCourseCode(String code){
        return dao.countByCourseCode(code);
    }
    public List<Payment> findByPaymentStateCode(String code){
        return dao.findByPaymentStateCode(code);
    }
    public List<Payment> findByPaymentStateId(Long id){
        return dao.findByPaymentStateId(id);
    }
    public int deleteByPaymentStateCode(String code){
        return dao.deleteByPaymentStateCode(code);
    }
    public int deleteByPaymentStateId(Long id){
        return dao.deleteByPaymentStateId(id);
    }
    public long countByPaymentStateCode(String code){
        return dao.countByPaymentStateCode(code);
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
    public List<Payment> delete(List<Payment> list) {
		List<Payment> result = new ArrayList();
        if (list != null) {
            for (Payment t : list) {
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
    public Payment create(Payment t) {
        Payment loaded = findByReferenceEntity(t);
        Payment saved;
        if (loaded == null) {
            saved = dao.save(t);
        }else {
            saved = null;
        }
        return saved;
    }

    public Payment findWithAssociatedLists(Long id){
        Payment result = dao.findById(id).orElse(null);
        return result;
    }

	@Transactional(propagation = Propagation.REQUIRED, rollbackFor = Exception.class, readOnly = false)
    public List<Payment> update(List<Payment> ts, boolean createIfNotExist) {
        List<Payment> result = new ArrayList<>();
        if (ts != null) {
            for (Payment t : ts) {
                if (t.getId() == null) {
                    dao.save(t);
                } else {
                    Payment loadedItem = dao.findById(t.getId()).orElse(null);
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


    private boolean isEligibleForCreateOrUpdate(boolean createIfNotExist, Payment t, Payment loadedItem) {
        boolean eligibleForCreateCrud = t.getId() == null;
        boolean eligibleForCreate = (createIfNotExist && (t.getId() == null || loadedItem == null));
        boolean eligibleForUpdate = (t.getId() != null && loadedItem != null);
        return (eligibleForCreateCrud || eligibleForCreate || eligibleForUpdate);
    }









    public Payment findByReferenceEntity(Payment t) {
        return t == null || t.getId() == null ? null : findById(t.getId());
    }
    public void findOrSaveAssociatedObject(Payment t){
        if( t != null) {
            t.setStudent(studentService.findOrSave(t.getStudent()));
            t.setCourse(courseService.findOrSave(t.getCourse()));
            t.setPaymentState(paymentStateService.findOrSave(t.getPaymentState()));
        }
    }



    public List<Payment> findAllOptimized() {
        return dao.findAll();
    }

    @Override
    public List<List<Payment>> getToBeSavedAndToBeDeleted(List<Payment> oldList, List<Payment> newList) {
        List<List<Payment>> result = new ArrayList<>();
        List<Payment> resultDelete = new ArrayList<>();
        List<Payment> resultUpdateOrSave = new ArrayList<>();
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

    private void extractToBeSaveOrDelete(List<Payment> oldList, List<Payment> newList, List<Payment> resultUpdateOrSave, List<Payment> resultDelete) {
		for (int i = 0; i < oldList.size(); i++) {
                Payment myOld = oldList.get(i);
                Payment t = newList.stream().filter(e -> myOld.equals(e)).findFirst().orElse(null);
                if (t != null) {
                    resultUpdateOrSave.add(t); // update
                } else {
                    resultDelete.add(myOld);
                }
            }
            for (int i = 0; i < newList.size(); i++) {
                Payment myNew = newList.get(i);
                Payment t = oldList.stream().filter(e -> myNew.equals(e)).findFirst().orElse(null);
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
    private PaymentStateAdminService paymentStateService ;
    @Autowired
    private StudentAdminService studentService ;
    @Autowired
    private CourseAdminService courseService ;

    public PaymentAdminServiceImpl(PaymentDao dao) {
        this.dao = dao;
    }

    private PaymentDao dao;
}
