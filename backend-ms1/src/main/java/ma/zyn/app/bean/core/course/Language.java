package ma.zyn.app.bean.core.course;








import com.fasterxml.jackson.annotation.JsonInclude;
import ma.zyn.app.zynerator.bean.BaseEntity;
import jakarta.persistence.*;
import java.util.Objects;

@Entity
@Table(name = "language")
@JsonInclude(JsonInclude.Include.NON_NULL)
@SequenceGenerator(name="language_seq",sequenceName="language_seq",allocationSize=1, initialValue = 1)
public class Language  extends BaseEntity     {




    @Column(length = 500)
    private String code;

    @Column(length = 500)
    private String label;

    @Column(length = 500)
    private String style;



    public Language(){
        super();
    }

    public Language(Long id){
        this.id = id;
    }

    public Language(Long id,String label){
        this.id = id;
        this.label = label ;
    }
    public Language(String label){
        this.label = label ;
    }
    public Language(String label,String code){
        this.label=label;
        this.code=code;
    }




    @Id
    @Column(name = "id")
    @GeneratedValue(strategy =  GenerationType.SEQUENCE,generator="language_seq")
      @Override
    public Long getId(){
        return this.id;
    }
        @Override
    public void setId(Long id){
        this.id = id;
    }
    public String getCode(){
        return this.code;
    }
    public void setCode(String code){
        this.code = code;
    }
    public String getLabel(){
        return this.label;
    }
    public void setLabel(String label){
        this.label = label;
    }
    public String getStyle(){
        return this.style;
    }
    public void setStyle(String style){
        this.style = style;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Language language = (Language) o;
        return id != null && id.equals(language.id);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id);
    }

}

