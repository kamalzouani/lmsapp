package ma.zyn.app.bean.core.instructor;








import com.fasterxml.jackson.annotation.JsonInclude;
import ma.zyn.app.zynerator.bean.BaseEntity;
import jakarta.persistence.*;
import java.util.Objects;
import ma.zyn.app.zynerator.security.bean.User;

@Entity
@Table(name = "instructor")
@JsonInclude(JsonInclude.Include.NON_NULL)
@SequenceGenerator(name="instructor_seq",sequenceName="instructor_seq",allocationSize=1, initialValue = 1)
public class Instructor  extends User    {


    public Instructor(String username) {
        super(username);
    }


    private String bio;











    public Instructor(){
        super();
    }

    public Instructor(Long id){
        this.id = id;
    }

    public Instructor(Long id,String email){
        this.id = id;
        this.email = email ;
    }




    @Id
    @Column(name = "id")
    @GeneratedValue(strategy =  GenerationType.SEQUENCE,generator="instructor_seq")
      @Override
    public Long getId(){
        return this.id;
    }
        @Override
    public void setId(Long id){
        this.id = id;
    }
      @Column(columnDefinition="TEXT")
    public String getBio(){
        return this.bio;
    }
    public void setBio(String bio){
        this.bio = bio;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Instructor instructor = (Instructor) o;
        return id != null && id.equals(instructor.id);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id);
    }

}

