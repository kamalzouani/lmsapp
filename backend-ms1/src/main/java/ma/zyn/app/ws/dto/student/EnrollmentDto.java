package  ma.zyn.app.ws.dto.student;

import ma.zyn.app.zynerator.dto.AuditBaseDto;
import com.fasterxml.jackson.annotation.JsonInclude;

import java.util.Date;
import jakarta.persistence.Temporal;
import jakarta.persistence.TemporalType;
import com.fasterxml.jackson.annotation.JsonFormat;


import ma.zyn.app.ws.dto.course.CourseDto;


@JsonInclude(JsonInclude.Include.NON_NULL)
public class EnrollmentDto  extends AuditBaseDto {

    private String enrollmentDate ;

    private StudentDto student ;
    private CourseDto course ;
    private EnrollmentStateDto enrollmentState ;



    public EnrollmentDto(){
        super();
    }




    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "dd/MM/yyyy HH:mm")
    public String getEnrollmentDate(){
        return this.enrollmentDate;
    }
    public void setEnrollmentDate(String enrollmentDate){
        this.enrollmentDate = enrollmentDate;
    }


    public StudentDto getStudent(){
        return this.student;
    }

    public void setStudent(StudentDto student){
        this.student = student;
    }
    public CourseDto getCourse(){
        return this.course;
    }

    public void setCourse(CourseDto course){
        this.course = course;
    }
    public EnrollmentStateDto getEnrollmentState(){
        return this.enrollmentState;
    }

    public void setEnrollmentState(EnrollmentStateDto enrollmentState){
        this.enrollmentState = enrollmentState;
    }






}
