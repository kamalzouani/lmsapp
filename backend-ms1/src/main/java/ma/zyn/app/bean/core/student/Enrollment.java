package ma.zyn.app.bean.core.student;


import java.time.LocalDateTime;


import java.util.Date;
import com.fasterxml.jackson.annotation.JsonFormat;


import ma.zyn.app.bean.core.course.Course;


import com.fasterxml.jackson.annotation.JsonInclude;
import ma.zyn.app.zynerator.bean.BaseEntity;
import jakarta.persistence.*;
import java.util.Objects;

@Entity
@Table(name = "enrollment")
@JsonInclude(JsonInclude.Include.NON_NULL)
@SequenceGenerator(name="enrollment_seq",sequenceName="enrollment_seq",allocationSize=1, initialValue = 1)
public class Enrollment  extends BaseEntity     {




    private LocalDateTime enrollmentDate ;

    private Student student ;
    private Course course ;
    private EnrollmentState enrollmentState ;


    public Enrollment(){
        super();
    }

    public Enrollment(Long id){
        this.id = id;
    }





    @Id
    @Column(name = "id")
    @GeneratedValue(strategy =  GenerationType.SEQUENCE,generator="enrollment_seq")
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
    public LocalDateTime getEnrollmentDate(){
        return this.enrollmentDate;
    }
    public void setEnrollmentDate(LocalDateTime enrollmentDate){
        this.enrollmentDate = enrollmentDate;
    }
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "enrollment_state")
    public EnrollmentState getEnrollmentState(){
        return this.enrollmentState;
    }
    public void setEnrollmentState(EnrollmentState enrollmentState){
        this.enrollmentState = enrollmentState;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Enrollment enrollment = (Enrollment) o;
        return id != null && id.equals(enrollment.id);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id);
    }

}

