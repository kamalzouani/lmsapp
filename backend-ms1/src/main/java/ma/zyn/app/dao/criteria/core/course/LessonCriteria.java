package  ma.zyn.app.dao.criteria.core.course;



import ma.zyn.app.zynerator.criteria.BaseCriteria;

import java.util.List;

public class LessonCriteria extends  BaseCriteria  {

    private String label;
    private String labelLike;
    private String description;
    private String descriptionLike;
    private String content;
    private String contentLike;

    private ModuleCriteria module ;
    private List<ModuleCriteria> modules ;


    public String getLabel(){
        return this.label;
    }
    public void setLabel(String label){
        this.label = label;
    }
    public String getLabelLike(){
        return this.labelLike;
    }
    public void setLabelLike(String labelLike){
        this.labelLike = labelLike;
    }

    public String getDescription(){
        return this.description;
    }
    public void setDescription(String description){
        this.description = description;
    }
    public String getDescriptionLike(){
        return this.descriptionLike;
    }
    public void setDescriptionLike(String descriptionLike){
        this.descriptionLike = descriptionLike;
    }

    public String getContent(){
        return this.content;
    }
    public void setContent(String content){
        this.content = content;
    }
    public String getContentLike(){
        return this.contentLike;
    }
    public void setContentLike(String contentLike){
        this.contentLike = contentLike;
    }


    public ModuleCriteria getModule(){
        return this.module;
    }

    public void setModule(ModuleCriteria module){
        this.module = module;
    }
    public List<ModuleCriteria> getModules(){
        return this.modules;
    }

    public void setModules(List<ModuleCriteria> modules){
        this.modules = modules;
    }
}
