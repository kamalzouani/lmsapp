package  ma.zyn.app.ws.converter.student;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.beans.BeanUtils;
import ma.zyn.app.zynerator.converter.AbstractConverterHelper;

import java.util.ArrayList;
import java.util.List;

import ma.zyn.app.ws.converter.student.EnrollmentStateConverter;
import ma.zyn.app.bean.core.student.EnrollmentState;
import ma.zyn.app.ws.converter.student.StudentConverter;
import ma.zyn.app.bean.core.student.Student;
import ma.zyn.app.ws.converter.course.CourseConverter;
import ma.zyn.app.bean.core.course.Course;



import ma.zyn.app.zynerator.util.StringUtil;
import ma.zyn.app.zynerator.converter.AbstractConverter;
import ma.zyn.app.zynerator.util.DateUtil;
import ma.zyn.app.bean.core.student.Enrollment;
import ma.zyn.app.ws.dto.student.EnrollmentDto;

@Component
public class EnrollmentConverter {

    @Autowired
    private EnrollmentStateConverter enrollmentStateConverter ;
    @Autowired
    private StudentConverter studentConverter ;
    @Autowired
    private CourseConverter courseConverter ;
    private boolean student;
    private boolean course;
    private boolean enrollmentState;

    public  EnrollmentConverter() {
        initObject(true);
    }

    public Enrollment toItem(EnrollmentDto dto) {
        if (dto == null) {
            return null;
        } else {
        Enrollment item = new Enrollment();
            if(StringUtil.isNotEmpty(dto.getId()))
                item.setId(dto.getId());
            if(StringUtil.isNotEmpty(dto.getEnrollmentDate()))
                item.setEnrollmentDate(DateUtil.stringEnToDate(dto.getEnrollmentDate()));
            if(this.student && dto.getStudent()!=null)
                item.setStudent(studentConverter.toItem(dto.getStudent())) ;

            if(this.course && dto.getCourse()!=null)
                item.setCourse(courseConverter.toItem(dto.getCourse())) ;

            if(this.enrollmentState && dto.getEnrollmentState()!=null)
                item.setEnrollmentState(enrollmentStateConverter.toItem(dto.getEnrollmentState())) ;




        return item;
        }
    }


    public EnrollmentDto toDto(Enrollment item) {
        if (item == null) {
            return null;
        } else {
            EnrollmentDto dto = new EnrollmentDto();
            if(StringUtil.isNotEmpty(item.getId()))
                dto.setId(item.getId());
            if(item.getEnrollmentDate()!=null)
                dto.setEnrollmentDate(DateUtil.dateTimeToString(item.getEnrollmentDate()));
            if(this.student && item.getStudent()!=null) {
                dto.setStudent(studentConverter.toDto(item.getStudent())) ;

            }
            if(this.course && item.getCourse()!=null) {
                dto.setCourse(courseConverter.toDto(item.getCourse())) ;

            }
            if(this.enrollmentState && item.getEnrollmentState()!=null) {
                dto.setEnrollmentState(enrollmentStateConverter.toDto(item.getEnrollmentState())) ;

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
        this.enrollmentState = value;
    }
	
    public List<Enrollment> toItem(List<EnrollmentDto> dtos) {
        List<Enrollment> items = new ArrayList<>();
        if (dtos != null && !dtos.isEmpty()) {
            for (EnrollmentDto dto : dtos) {
                items.add(toItem(dto));
            }
        }
        return items;
    }


    public List<EnrollmentDto> toDto(List<Enrollment> items) {
        List<EnrollmentDto> dtos = new ArrayList<>();
        if (items != null && !items.isEmpty()) {
            for (Enrollment item : items) {
                dtos.add(toDto(item));
            }
        }
        return dtos;
    }


    public void copy(EnrollmentDto dto, Enrollment t) {
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
        if(t.getEnrollmentState() == null  && dto.getEnrollmentState() != null){
            t.setEnrollmentState(new EnrollmentState());
        }else if (t.getEnrollmentState() != null  && dto.getEnrollmentState() != null){
            t.setEnrollmentState(null);
            t.setEnrollmentState(new EnrollmentState());
        }
        if (dto.getStudent() != null)
        studentConverter.copy(dto.getStudent(), t.getStudent());
        if (dto.getCourse() != null)
        courseConverter.copy(dto.getCourse(), t.getCourse());
        if (dto.getEnrollmentState() != null)
        enrollmentStateConverter.copy(dto.getEnrollmentState(), t.getEnrollmentState());
    }

    public List<Enrollment> copy(List<EnrollmentDto> dtos) {
        List<Enrollment> result = new ArrayList<>();
        if (dtos != null) {
            for (EnrollmentDto dto : dtos) {
                Enrollment instance = new Enrollment();
                copy(dto, instance);
                result.add(instance);
            }
        }
        return result.isEmpty() ? null : result;
    }


    public EnrollmentStateConverter getEnrollmentStateConverter(){
        return this.enrollmentStateConverter;
    }
    public void setEnrollmentStateConverter(EnrollmentStateConverter enrollmentStateConverter ){
        this.enrollmentStateConverter = enrollmentStateConverter;
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
    public boolean  isEnrollmentState(){
        return this.enrollmentState;
    }
    public void  setEnrollmentState(boolean enrollmentState){
        this.enrollmentState = enrollmentState;
    }
}
