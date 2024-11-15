package  ma.zyn.app.ws.converter.student;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.beans.BeanUtils;
import ma.zyn.app.zynerator.converter.AbstractConverterHelper;

import java.util.ArrayList;
import java.util.List;

import ma.zyn.app.ws.converter.student.PaymentStateConverter;
import ma.zyn.app.bean.core.student.PaymentState;
import ma.zyn.app.ws.converter.student.StudentConverter;
import ma.zyn.app.bean.core.student.Student;
import ma.zyn.app.ws.converter.course.CourseConverter;
import ma.zyn.app.bean.core.course.Course;



import ma.zyn.app.zynerator.util.StringUtil;
import ma.zyn.app.zynerator.converter.AbstractConverter;
import ma.zyn.app.zynerator.util.DateUtil;
import ma.zyn.app.bean.core.student.Payment;
import ma.zyn.app.ws.dto.student.PaymentDto;

@Component
public class PaymentConverter {

    @Autowired
    private PaymentStateConverter paymentStateConverter ;
    @Autowired
    private StudentConverter studentConverter ;
    @Autowired
    private CourseConverter courseConverter ;
    private boolean student;
    private boolean course;
    private boolean paymentState;

    public  PaymentConverter() {
        initObject(true);
    }

    public Payment toItem(PaymentDto dto) {
        if (dto == null) {
            return null;
        } else {
        Payment item = new Payment();
            if(StringUtil.isNotEmpty(dto.getId()))
                item.setId(dto.getId());
            if(StringUtil.isNotEmpty(dto.getAmount()))
                item.setAmount(dto.getAmount());
            if(StringUtil.isNotEmpty(dto.getPaymentDate()))
                item.setPaymentDate(DateUtil.stringEnToDate(dto.getPaymentDate()));
            if(this.student && dto.getStudent()!=null)
                item.setStudent(studentConverter.toItem(dto.getStudent())) ;

            if(this.course && dto.getCourse()!=null)
                item.setCourse(courseConverter.toItem(dto.getCourse())) ;

            if(this.paymentState && dto.getPaymentState()!=null)
                item.setPaymentState(paymentStateConverter.toItem(dto.getPaymentState())) ;




        return item;
        }
    }


    public PaymentDto toDto(Payment item) {
        if (item == null) {
            return null;
        } else {
            PaymentDto dto = new PaymentDto();
            if(StringUtil.isNotEmpty(item.getId()))
                dto.setId(item.getId());
            if(StringUtil.isNotEmpty(item.getAmount()))
                dto.setAmount(item.getAmount());
            if(item.getPaymentDate()!=null)
                dto.setPaymentDate(DateUtil.dateTimeToString(item.getPaymentDate()));
            if(this.student && item.getStudent()!=null) {
                dto.setStudent(studentConverter.toDto(item.getStudent())) ;

            }
            if(this.course && item.getCourse()!=null) {
                dto.setCourse(courseConverter.toDto(item.getCourse())) ;

            }
            if(this.paymentState && item.getPaymentState()!=null) {
                dto.setPaymentState(paymentStateConverter.toDto(item.getPaymentState())) ;

            }


        return dto;
        }
    }

    public void init(boolean value) {
        initObject(value);
    }

    public void initObject(boolean value) {
        this.student = value;
        this.course = value;
        this.paymentState = value;
    }
	
    public List<Payment> toItem(List<PaymentDto> dtos) {
        List<Payment> items = new ArrayList<>();
        if (dtos != null && !dtos.isEmpty()) {
            for (PaymentDto dto : dtos) {
                items.add(toItem(dto));
            }
        }
        return items;
    }


    public List<PaymentDto> toDto(List<Payment> items) {
        List<PaymentDto> dtos = new ArrayList<>();
        if (items != null && !items.isEmpty()) {
            for (Payment item : items) {
                dtos.add(toDto(item));
            }
        }
        return dtos;
    }


    public void copy(PaymentDto dto, Payment t) {
		BeanUtils.copyProperties(dto, t, AbstractConverterHelper.getNullPropertyNames(dto));
        if(t.getStudent() == null  && dto.getStudent() != null){
            t.setStudent(new Student());
        }else if (t.getStudent() != null  && dto.getStudent() != null){
            t.setStudent(null);
            t.setStudent(new Student());
        }
        if(t.getCourse() == null  && dto.getCourse() != null){
            t.setCourse(new Course());
        }else if (t.getCourse() != null  && dto.getCourse() != null){
            t.setCourse(null);
            t.setCourse(new Course());
        }
        if(t.getPaymentState() == null  && dto.getPaymentState() != null){
            t.setPaymentState(new PaymentState());
        }else if (t.getPaymentState() != null  && dto.getPaymentState() != null){
            t.setPaymentState(null);
            t.setPaymentState(new PaymentState());
        }
        if (dto.getStudent() != null)
        studentConverter.copy(dto.getStudent(), t.getStudent());
        if (dto.getCourse() != null)
        courseConverter.copy(dto.getCourse(), t.getCourse());
        if (dto.getPaymentState() != null)
        paymentStateConverter.copy(dto.getPaymentState(), t.getPaymentState());
    }

    public List<Payment> copy(List<PaymentDto> dtos) {
        List<Payment> result = new ArrayList<>();
        if (dtos != null) {
            for (PaymentDto dto : dtos) {
                Payment instance = new Payment();
                copy(dto, instance);
                result.add(instance);
            }
        }
        return result.isEmpty() ? null : result;
    }


    public PaymentStateConverter getPaymentStateConverter(){
        return this.paymentStateConverter;
    }
    public void setPaymentStateConverter(PaymentStateConverter paymentStateConverter ){
        this.paymentStateConverter = paymentStateConverter;
    }
    public StudentConverter getStudentConverter(){
        return this.studentConverter;
    }
    public void setStudentConverter(StudentConverter studentConverter ){
        this.studentConverter = studentConverter;
    }
    public CourseConverter getCourseConverter(){
        return this.courseConverter;
    }
    public void setCourseConverter(CourseConverter courseConverter ){
        this.courseConverter = courseConverter;
    }
    public boolean  isStudent(){
        return this.student;
    }
    public void  setStudent(boolean student){
        this.student = student;
    }
    public boolean  isCourse(){
        return this.course;
    }
    public void  setCourse(boolean course){
        this.course = course;
    }
    public boolean  isPaymentState(){
        return this.paymentState;
    }
    public void  setPaymentState(boolean paymentState){
        this.paymentState = paymentState;
    }
}
