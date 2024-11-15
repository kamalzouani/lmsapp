package ma.zyn.app.bean.core.course;

import java.util.List;







import com.fasterxml.jackson.annotation.JsonInclude;
import ma.zyn.app.zynerator.bean.BaseEntity;
import jakarta.persistence.*;
import java.util.Objects;

@Entity
@Table(name = "module")
@JsonInclude(JsonInclude.Include.NON_NULL)
@SequenceGenerator(name="module_seq",sequenceName="module_seq",allocationSize=1, initialValue = 1)
public class Module  extends BaseEntity     {




    @Column(length = 500)
    private String label;

    @Column(length = 500)
    private String description;

    private Curriculum curriculum ;

    private List<Lesson> lessons ;

    public Module(){
        super();
    }

    public Module(Long id){
        this.id = id;
    }

    public Module(Long id,String label){
        this.id = id;
        this.label = label ;
    }
    public Module(String label){
        this.label = label ;
    }




    @Id
    @Column(name = "id")
    @GeneratedValue(strategy =  GenerationType.SEQUENCE,generator="module_seq")
      @Override
    public Long getId(){
        return this.id;
    }
        @Override
    public void setId(Long id){
        this.id = id;
    }
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "curriculum")
    public Curriculum getCurriculum(){
        return this.curriculum;
    }
    public void setCurriculum(Curriculum curriculum){
        this.curriculum = curriculum;
    }
    public String getLabel(){
        return this.label;
    }
    public void setLabel(String label){
        this.label = label;
    }
    public String getDescription(){
        return this.description;
    }
    public void setDescription(String description){
        this.description = description;
    }
    @OneToMany(mappedBy = "module")
    public List<Lesson> getLessons(){
        return this.lessons;
    }

    public void setLessons(List<Lesson> lessons){
        this.lessons = lessons;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Module module = (Module) o;
        return id != null && id.equals(module.id);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id);
    }

}

