package  ma.zyn.app.ws.dto.course;

import ma.zyn.app.zynerator.dto.AuditBaseDto;
import com.fasterxml.jackson.annotation.JsonInclude;





@JsonInclude(JsonInclude.Include.NON_NULL)
public class ResourceDto  extends AuditBaseDto {

    private String type  ;
    private String url  ;
    private String file  ;

    private LessonDto lesson ;



    public ResourceDto(){
        super();
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


    public LessonDto getLesson(){
        return this.lesson;
    }

    public void setLesson(LessonDto lesson){
        this.lesson = lesson;
    }






}
