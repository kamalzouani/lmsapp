package  ma.zyn.app.ws.dto.course;

import ma.zyn.app.zynerator.dto.AuditBaseDto;
import com.fasterxml.jackson.annotation.JsonInclude;

import java.util.List;




@JsonInclude(JsonInclude.Include.NON_NULL)
public class ModuleDto  extends AuditBaseDto {

    private String label  ;
    private String description  ;

    private CurriculumDto curriculum ;

    private List<LessonDto> lessons ;


    public ModuleDto(){
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


    public CurriculumDto getCurriculum(){
        return this.curriculum;
    }

    public void setCurriculum(CurriculumDto curriculum){
        this.curriculum = curriculum;
    }



    public List<LessonDto> getLessons(){
        return this.lessons;
    }

    public void setLessons(List<LessonDto> lessons){
        this.lessons = lessons;
    }



}
