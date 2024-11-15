package  ma.zyn.app.ws.converter.student;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.beans.BeanUtils;
import ma.zyn.app.zynerator.converter.AbstractConverterHelper;

import java.util.ArrayList;
import java.util.List;

import ma.zyn.app.ws.converter.student.StudentConverter;
import ma.zyn.app.bean.core.student.Student;
import ma.zyn.app.ws.converter.course.CourseConverter;
import ma.zyn.app.bean.core.course.Course;



import ma.zyn.app.zynerator.util.StringUtil;
import ma.zyn.app.zynerator.converter.AbstractConverter;
import ma.zyn.app.zynerator.util.DateUtil;
import ma.zyn.app.bean.core.student.Review;
import ma.zyn.app.ws.dto.student.ReviewDto;

@Component
public class ReviewConverter {

    @Autowired
    private StudentConverter studentConverter ;
    @Autowired
    private CourseConverter courseConverter ;
    private boolean student;
    private boolean course;

    public  ReviewConverter() {
        initObject(true);
    }

    public Review toItem(ReviewDto dto) {
        if (dto == null) {
            return null;
        } else {
        Review item = new Review();
            if(StringUtil.isNotEmpty(dto.getId()))
                item.setId(dto.getId());
            if(StringUtil.isNotEmpty(dto.getRating()))
                item.setRating(dto.getRating());
            if(StringUtil.isNotEmpty(dto.getComment()))
                item.setComment(dto.getComment());
            if(StringUtil.isNotEmpty(dto.getReviewDate()))
                item.setReviewDate(DateUtil.stringEnToDate(dto.getReviewDate()));
            if(this.student && dto.getStudent()!=null)
                item.setStudent(studentConverter.toItem(dto.getStudent())) ;

            if(this.course && dto.getCourse()!=null)
                item.setCourse(courseConverter.toItem(dto.getCourse())) ;




        return item;
        }
    }


    public ReviewDto toDto(Review item) {
        if (item == null) {
            return null;
        } else {
            ReviewDto dto = new ReviewDto();
            if(StringUtil.isNotEmpty(item.getId()))
                dto.setId(item.getId());
            if(StringUtil.isNotEmpty(item.getRating()))
                dto.setRating(item.getRating());
            if(StringUtil.isNotEmpty(item.getComment()))
                dto.setComment(item.getComment());
            if(item.getReviewDate()!=null)
                dto.setReviewDate(DateUtil.dateTimeToString(item.getReviewDate()));
            if(this.student && item.getStudent()!=null) {
                dto.setStudent(studentConverter.toDto(item.getStudent())) ;

            }
            if(this.course && item.getCourse()!=null) {
                dto.setCourse(courseConverter.toDto(item.getCourse())) ;

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
    }
	
    public List<Review> toItem(List<ReviewDto> dtos) {
        List<Review> items = new ArrayList<>();
        if (dtos != null && !dtos.isEmpty()) {
            for (ReviewDto dto : dtos) {
                items.add(toItem(dto));
            }
        }
        return items;
    }


    public List<ReviewDto> toDto(List<Review> items) {
        List<ReviewDto> dtos = new ArrayList<>();
        if (items != null && !items.isEmpty()) {
            for (Review item : items) {
                dtos.add(toDto(item));
            }
        }
        return dtos;
    }


    public void copy(ReviewDto dto, Review t) {
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
        if (dto.getStudent() != null)
        studentConverter.copy(dto.getStudent(), t.getStudent());
        if (dto.getCourse() != null)
        courseConverter.copy(dto.getCourse(), t.getCourse());
    }

    public List<Review> copy(List<ReviewDto> dtos) {
        List<Review> result = new ArrayList<>();
        if (dtos != null) {
            for (ReviewDto dto : dtos) {
                Review instance = new Review();
                copy(dto, instance);
                result.add(instance);
            }
        }
        return result.isEmpty() ? null : result;
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
}
