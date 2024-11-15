package  ma.zyn.app.dao.criteria.core.student;


import ma.zyn.app.dao.criteria.core.course.CourseCriteria;

import ma.zyn.app.zynerator.criteria.BaseCriteria;

import java.util.List;
import java.time.LocalDateTime;
import java.time.LocalDate;

public class ReviewCriteria extends  BaseCriteria  {

    private String rating;
    private String ratingMin;
    private String ratingMax;
    private String comment;
    private String commentLike;
    private LocalDateTime reviewDate;
    private LocalDateTime reviewDateFrom;
    private LocalDateTime reviewDateTo;

    private StudentCriteria student ;
    private List<StudentCriteria> students ;
    private CourseCriteria course ;
    private List<CourseCriteria> courses ;


    public String getRating(){
        return this.rating;
    }
    public void setRating(String rating){
        this.rating = rating;
    }   
    public String getRatingMin(){
        return this.ratingMin;
    }
    public void setRatingMin(String ratingMin){
        this.ratingMin = ratingMin;
    }
    public String getRatingMax(){
        return this.ratingMax;
    }
    public void setRatingMax(String ratingMax){
        this.ratingMax = ratingMax;
    }
      
    public String getComment(){
        return this.comment;
    }
    public void setComment(String comment){
        this.comment = comment;
    }
    public String getCommentLike(){
        return this.commentLike;
    }
    public void setCommentLike(String commentLike){
        this.commentLike = commentLike;
    }

    public LocalDateTime getReviewDate(){
        return this.reviewDate;
    }
    public void setReviewDate(LocalDateTime reviewDate){
        this.reviewDate = reviewDate;
    }
    public LocalDateTime getReviewDateFrom(){
        return this.reviewDateFrom;
    }
    public void setReviewDateFrom(LocalDateTime reviewDateFrom){
        this.reviewDateFrom = reviewDateFrom;
    }
    public LocalDateTime getReviewDateTo(){
        return this.reviewDateTo;
    }
    public void setReviewDateTo(LocalDateTime reviewDateTo){
        this.reviewDateTo = reviewDateTo;
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
}
