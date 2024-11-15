package  ma.zyn.app.ws.dto.course;

import ma.zyn.app.zynerator.dto.AuditBaseDto;
import com.fasterxml.jackson.annotation.JsonInclude;

import java.util.List;




@JsonInclude(JsonInclude.Include.NON_NULL)
public class LessonDto  extends AuditBaseDto {

    private String label  ;
    private String description  ;
    private String content  ;

    private ModuleDto module ;

    private List<ResourceDto> resources ;


    public LessonDto(){
        super();
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


    public String getContent(){
        return this.content;
    }
    public void setContent(String content){
        this.content = content;
    }


    public ModuleDto getModule(){
        return this.module;
    }

    public void setModule(ModuleDto module){
        this.module = module;
    }



    public List<ResourceDto> getResources(){
        return this.resources;
    }

    public void setResources(List<ResourceDto> resources){
        this.resources = resources;
    }



}
