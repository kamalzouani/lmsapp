package ma.zyn.app.bean.core.course;

import java.util.List;







import com.fasterxml.jackson.annotation.JsonInclude;
import ma.zyn.app.zynerator.bean.BaseEntity;
import jakarta.persistence.*;
import java.util.Objects;

@Entity
@Table(name = "lesson")
@JsonInclude(JsonInclude.Include.NON_NULL)
@SequenceGenerator(name="lesson_seq",sequenceName="lesson_seq",allocationSize=1, initialValue = 1)
public class Lesson  extends BaseEntity     {




    @Column(length = 500)
    private String label;

    @Column(length = 500)
    private String description;

    private String content;

    private Module module ;

    private List<Resource> resources ;

    public Lesson(){
        super();
    }

    public Lesson(Long id){
        this.id = id;
    }

    public Lesson(Long id,String label){
        this.id = id;
        this.label = label ;
    }
    public Lesson(String label){
        this.label = label ;
    }




    @Id
    @Column(name = "id")
    @GeneratedValue(strategy =  GenerationType.SEQUENCE,generator="lesson_seq")
      @Override
    public Long getId(){
        return this.id;
    }
        @Override
    public void setId(Long id){
        this.id = id;
    }
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "module")
    public Module getModule(){
        return this.module;
    }
    public void setModule(Module module){
        this.module = module;
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
      @Column(columnDefinition="TEXT")
    public String getContent(){
        return this.content;
    }
    public void setContent(String content){
        this.content = content;
    }
    @OneToMany(mappedBy = "lesson")
    public List<Resource> getResources(){
        return this.resources;
    }

    public void setResources(List<Resource> resources){
        this.resources = resources;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Lesson lesson = (Lesson) o;
        return id != null && id.equals(lesson.id);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id);
    }

}

