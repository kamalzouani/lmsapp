package  ma.zyn.app.ws.dto.course;

import ma.zyn.app.zynerator.dto.AuditBaseDto;
import com.fasterxml.jackson.annotation.JsonInclude;

import java.util.List;




@JsonInclude(JsonInclude.Include.NON_NULL)
public class CurriculumDto  extends AuditBaseDto {


    private CourseDto course ;

    private List<ModuleDto> modules ;


    public CurriculumDto(){
        super();
    }




    public CourseDto getCourse(){
        return this.course;
    }

    public void setCourse(CourseDto course){
        this.course = course;
    }



    public List<ModuleDto> getModules(){
        return this.modules;
    }

    public void setModules(List<ModuleDto> modules){
        this.modules = modules;
    }



}
