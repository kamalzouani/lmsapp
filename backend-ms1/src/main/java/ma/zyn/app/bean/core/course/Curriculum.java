package ma.zyn.app.bean.core.course;

import java.util.List;







import com.fasterxml.jackson.annotation.JsonInclude;
import ma.zyn.app.zynerator.bean.BaseEntity;
import jakarta.persistence.*;
import java.util.Objects;

@Entity
@Table(name = "curriculum")
@JsonInclude(JsonInclude.Include.NON_NULL)
@SequenceGenerator(name="curriculum_seq",sequenceName="curriculum_seq",allocationSize=1, initialValue = 1)
public class Curriculum  extends BaseEntity     {




    private Course course ;

    private List<Module> modules ;

    public Curriculum(){
        super();
    }

    public Curriculum(Long id){
        this.id = id;
    }





    @Id
    @Column(name = "id")
    @GeneratedValue(strategy =  GenerationType.SEQUENCE,generator="curriculum_seq")
      @Override
    public Long getId(){
        return this.id;
    }
        @Override
    public void setId(Long id){
        this.id = id;
    }
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "course")
    public Course getCourse(){
        return this.course;
    }
    public void setCourse(Course course){
        this.course = course;
    }
    @OneToMany(mappedBy = "curriculum")
    public List<Module> getModules(){
        return this.modules;
    }

    public void setModules(List<Module> modules){
        this.modules = modules;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Curriculum curriculum = (Curriculum) o;
        return id != null && id.equals(curriculum.id);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id);
    }

}

