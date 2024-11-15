package  ma.zyn.app.ws.dto.course;

import ma.zyn.app.zynerator.dto.AuditBaseDto;
import com.fasterxml.jackson.annotation.JsonInclude;

import java.util.Date;
import jakarta.persistence.Temporal;
import jakarta.persistence.TemporalType;
import com.fasterxml.jackson.annotation.JsonFormat;
import java.math.BigDecimal;


import ma.zyn.app.ws.dto.instructor.InstructorDto;


@JsonInclude(JsonInclude.Include.NON_NULL)
public class CourseDto  extends AuditBaseDto {

    private String code  ;
    private String label  ;
    private String description  ;
    private String startDate ;
    private String endDate ;
    private BigDecimal duration  ;
    private BigDecimal price  ;
    private String requirements  ;
    private String learningOutcomes  ;

    private InstructorDto instructor ;
    private CategoryDto category ;
    private LevelDto level ;
    private LanguageDto language ;
    private CurriculumDto curriculum ;



    public CourseDto(){
        super();
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


    public String getDescription(){
        return this.description;
    }
    public void setDescription(String description){
        this.description = description;
    }


    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "dd/MM/yyyy HH:mm")
    public String getStartDate(){
        return this.startDate;
    }
    public void setStartDate(String startDate){
        this.startDate = startDate;
    }


    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "dd/MM/yyyy HH:mm")
    public String getEndDate(){
        return this.endDate;
    }
    public void setEndDate(String endDate){
        this.endDate = endDate;
    }


    public BigDecimal getDuration(){
        return this.duration;
    }
    public void setDuration(BigDecimal duration){
        this.duration = duration;
    }


    public BigDecimal getPrice(){
        return this.price;
    }
    public void setPrice(BigDecimal price){
        this.price = price;
    }


    public String getRequirements(){
        return this.requirements;
    }
    public void setRequirements(String requirements){
        this.requirements = requirements;
    }


    public String getLearningOutcomes(){
        return this.learningOutcomes;
    }
    public void setLearningOutcomes(String learningOutcomes){
        this.learningOutcomes = learningOutcomes;
    }


    public InstructorDto getInstructor(){
        return this.instructor;
    }

    public void setInstructor(InstructorDto instructor){
        this.instructor = instructor;
    }
    public CategoryDto getCategory(){
        return this.category;
    }

    public void setCategory(CategoryDto category){
        this.category = category;
    }
    public LevelDto getLevel(){
        return this.level;
    }

    public void setLevel(LevelDto level){
        this.level = level;
    }
    public LanguageDto getLanguage(){
        return this.language;
    }

    public void setLanguage(LanguageDto language){
        this.language = language;
    }
    public CurriculumDto getCurriculum(){
        return this.curriculum;
    }

    public void setCurriculum(CurriculumDto curriculum){
        this.curriculum = curriculum;
    }






}
