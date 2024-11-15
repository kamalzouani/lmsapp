package ma.zyn.app.bean.core.student;


import java.time.LocalDateTime;


import java.util.Date;
import com.fasterxml.jackson.annotation.JsonFormat;


import ma.zyn.app.bean.core.course.Course;


import com.fasterxml.jackson.annotation.JsonInclude;
import ma.zyn.app.zynerator.bean.BaseEntity;
import jakarta.persistence.*;
import java.util.Objects;
import java.math.BigDecimal;

@Entity
@Table(name = "review")
@JsonInclude(JsonInclude.Include.NON_NULL)
@SequenceGenerator(name="review_seq",sequenceName="review_seq",allocationSize=1, initialValue = 1)
public class Review  extends BaseEntity     {




    private BigDecimal rating = BigDecimal.ZERO;

    private String comment;

    private LocalDateTime reviewDate ;

    private Student student ;
    private Course course ;


    public Review(){
        super();
    }

    public Review(Long id){
        this.id = id;
    }





    @Id
    @Column(name = "id")
    @GeneratedValue(strategy =  GenerationType.SEQUENCE,generator="review_seq")
      @Override
    public Long getId(){
        return this.id;
    }
        @Override
    public void setId(Long id){
        this.id = id;
    }
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "student")
    public Student getStudent(){
        return this.student;
    }
    public void setStudent(Student student){
        this.student = student;
    }
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "course")
    public Course getCourse(){
        return this.course;
    }
    public void setCourse(Course course){
        this.course = course;
    }
    public BigDecimal getRating(){
        return this.rating;
    }
    public void setRating(BigDecimal rating){
        this.rating = rating;
    }
      @Column(columnDefinition="TEXT")
    public String getComment(){
        return this.comment;
    }
    public void setComment(String comment){
        this.comment = comment;
    }
    public LocalDateTime getReviewDate(){
        return this.reviewDate;
    }
    public void setReviewDate(LocalDateTime reviewDate){
        this.reviewDate = reviewDate;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Review review = (Review) o;
        return id != null && id.equals(review.id);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id);
    }

}

