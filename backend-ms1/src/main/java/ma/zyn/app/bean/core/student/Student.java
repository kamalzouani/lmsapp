package ma.zyn.app.bean.core.student;








import com.fasterxml.jackson.annotation.JsonInclude;
import ma.zyn.app.zynerator.bean.BaseEntity;
import jakarta.persistence.*;
import java.util.Objects;
import ma.zyn.app.zynerator.security.bean.User;

@Entity
@Table(name = "student")
@JsonInclude(JsonInclude.Include.NON_NULL)
@SequenceGenerator(name="student_seq",sequenceName="student_seq",allocationSize=1, initialValue = 1)
public class Student  extends User    {


    public Student(String username) {
        super(username);
    }












    public Student(){
        super();
    }

    public Student(Long id){
        this.id = id;
    }

    public Student(Long id,String email){
        this.id = id;
        this.email = email ;
    }




    @Id
    @Column(name = "id")
    @GeneratedValue(strategy =  GenerationType.SEQUENCE,generator="student_seq")
      @Override
    public Long getId(){
        return this.id;
    }
        @Override
    public void setId(Long id){
        this.id = id;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Student student = (Student) o;
        return id != null && id.equals(student.id);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id);
    }

}

