package ma.zyn.app.bean.core.course;








import com.fasterxml.jackson.annotation.JsonInclude;
import ma.zyn.app.zynerator.bean.BaseEntity;
import jakarta.persistence.*;
import java.util.Objects;

@Entity
@Table(name = "resource")
@JsonInclude(JsonInclude.Include.NON_NULL)
@SequenceGenerator(name="resource_seq",sequenceName="resource_seq",allocationSize=1, initialValue = 1)
public class Resource  extends BaseEntity     {




    @Column(length = 500)
    private String type;

    @Column(length = 500)
    private String url;

    @Column(length = 500)
    private String file;

    private Lesson lesson ;


    public Resource(){
        super();
    }

    public Resource(Long id){
        this.id = id;
    }





    @Id
    @Column(name = "id")
    @GeneratedValue(strategy =  GenerationType.SEQUENCE,generator="resource_seq")
      @Override
    public Long getId(){
        return this.id;
    }
        @Override
    public void setId(Long id){
        this.id = id;
    }
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "lesson")
    public Lesson getLesson(){
        return this.lesson;
    }
    public void setLesson(Lesson lesson){
        this.lesson = lesson;
    }
    public String getType(){
        return this.type;
    }
    public void setType(String type){
        this.type = type;
    }
    public String getUrl(){
        return this.url;
    }
    public void setUrl(String url){
        this.url = url;
    }
    public String getFile(){
        return this.file;
    }
    public void setFile(String file){
        this.file = file;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Resource resource = (Resource) o;
        return id != null && id.equals(resource.id);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id);
    }

}

