package  ma.zyn.app.ws.dto.student;

import ma.zyn.app.zynerator.dto.AuditBaseDto;
import com.fasterxml.jackson.annotation.JsonInclude;

import java.util.Date;
import jakarta.persistence.Temporal;
import jakarta.persistence.TemporalType;
import com.fasterxml.jackson.annotation.JsonFormat;
import java.math.BigDecimal;


import ma.zyn.app.ws.dto.course.CourseDto;


@JsonInclude(JsonInclude.Include.NON_NULL)
public class ReviewDto  extends AuditBaseDto {

    private BigDecimal rating  ;
    private String comment  ;
    private String reviewDate ;

    private StudentDto student ;
    private CourseDto course ;



    public ReviewDto(){
        super();
    }




    public BigDecimal getRating(){
        return this.rating;
    }
    public void setRating(BigDecimal rating){
        this.rating = rating;
    }


    public String getComment(){
        return this.comment;
    }
    public void setComment(String comment){
        this.comment = comment;
    }


    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "dd/MM/yyyy HH:mm")
    public String getReviewDate(){
        return this.reviewDate;
    }
    public void setReviewDate(String reviewDate){
        this.reviewDate = reviewDate;
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






}
