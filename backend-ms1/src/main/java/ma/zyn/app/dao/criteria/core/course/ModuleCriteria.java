package  ma.zyn.app.dao.criteria.core.course;



import ma.zyn.app.zynerator.criteria.BaseCriteria;

import java.util.List;

public class ModuleCriteria extends  BaseCriteria  {

    private String label;
    private String labelLike;
    private String description;
    private String descriptionLike;

    private CurriculumCriteria curriculum ;
    private List<CurriculumCriteria> curriculums ;


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


    public CurriculumCriteria getCurriculum(){
        return this.curriculum;
    }

    public void setCurriculum(CurriculumCriteria curriculum){
        this.curriculum = curriculum;
    }
    public List<CurriculumCriteria> getCurriculums(){
        return this.curriculums;
    }

    public void setCurriculums(List<CurriculumCriteria> curriculums){
        this.curriculums = curriculums;
    }
}
