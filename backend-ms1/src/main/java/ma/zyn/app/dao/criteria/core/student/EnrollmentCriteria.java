package  ma.zyn.app.dao.criteria.core.student;


import ma.zyn.app.dao.criteria.core.course.CourseCriteria;

import ma.zyn.app.zynerator.criteria.BaseCriteria;

import java.util.List;
import java.time.LocalDateTime;
import java.time.LocalDate;

public class EnrollmentCriteria extends  BaseCriteria  {

    private LocalDateTime enrollmentDate;
    private LocalDateTime enrollmentDateFrom;
    private LocalDateTime enrollmentDateTo;

    private StudentCriteria student ;
    private List<StudentCriteria> students ;
    private CourseCriteria course ;
    private List<CourseCriteria> courses ;
    private EnrollmentStateCriteria enrollmentState ;
    private List<EnrollmentStateCriteria> enrollmentStates ;


    public LocalDateTime getEnrollmentDate(){
        return this.enrollmentDate;
    }
    public void setEnrollmentDate(LocalDateTime enrollmentDate){
        this.enrollmentDate = enrollmentDate;
    }
    public LocalDateTime getEnrollmentDateFrom(){
        return this.enrollmentDateFrom;
    }
    public void setEnrollmentDateFrom(LocalDateTime enrollmentDateFrom){
        this.enrollmentDateFrom = enrollmentDateFrom;
    }
    public LocalDateTime getEnrollmentDateTo(){
        return this.enrollmentDateTo;
    }
    public void setEnrollmentDateTo(LocalDateTime enrollmentDateTo){
        this.enrollmentDateTo = enrollmentDateTo;
    }

    public StudentCriteria getStudent(){
        return this.student;
    }

    public void setStudent(StudentCriteria student){
        this.student = student;
    }
    public List<StudentCriteria> getStudents(){
        return this.students;
    }

    public void setStudents(List<StudentCriteria> students){
        this.students = students;
    }
    public CourseCriteria getCourse(){
        return this.course;
    }

    public void setCourse(CourseCriteria course){
        this.course = course;
    }
    public List<CourseCriteria> getCourses(){
        return this.courses;
    }

    public void setCourses(List<CourseCriteria> courses){
        this.courses = courses;
    }
    public EnrollmentStateCriteria getEnrollmentState(){
        return this.enrollmentState;
    }

    public void setEnrollmentState(EnrollmentStateCriteria enrollmentState){
        this.enrollmentState = enrollmentState;
    }
    public List<EnrollmentStateCriteria> getEnrollmentStates(){
        return this.enrollmentStates;
    }

    public void setEnrollmentStates(List<EnrollmentStateCriteria> enrollmentStates){
        this.enrollmentStates = enrollmentStates;
    }
}
